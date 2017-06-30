package org.xitikit.cloud.wijee;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static java.nio.file.Files.*;
import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.nio.file.attribute.FileTime.fromMillis;
import static org.xitikit.cloud.wijee.ClasspathResources.*;
import static org.xitikit.cloud.wijee.ReplacementPairBuilder.ReplacementPair;
import static org.xitikit.cloud.wijee.ReplacementPairBuilder.start;

/**
 * Copyright Xitikit.org ${year}
 *
 * @author J. Keith "Beirdo" Hoopes
 */
public class WijeeWrapper{

    private static final Logger log = Logger.getLogger(WijeeWrapper.class.getName());

    private static final String
        wijee_classpath_token = "${WIJEE_CLASSPATH}",
        wijee_main_class = "${WIJEE_MAIN_CLASS}",
        wijee_app_name = "${WIJEE_APP_NAME}";

    private final WijeeConfiguration config;
    private final ReplacementPair[] replacementPairs;

    public WijeeWrapper(final WijeeConfiguration config){

        Objects.requireNonNull(config);

        this.config = config;
        replacementPairs = start()
            .add(wijee_classpath_token, config.getClasspath())
            .add(wijee_main_class, config.getStartClass())
            .add(wijee_app_name, config.getAppName())
            .finish();
    }

    @SuppressWarnings("unused")
    public void wrap(){

        final String applicationName = config.getAppName();
        if("".equals(applicationName)){
            throw new WijeeException("Either the jar-name, war-name, or app-name must be set.");
        }
        exportClasspathResource(applicationName + "/x64", COMMONS_SERVICE_64);
        exportClasspathResources(applicationName,
            COMMONS_DAEMON_JAR,
            COMMONS_DAEMON_NATIVE_SOURCES,
            COMMONS_SERVICE_32,
            COMMONS_SERVICE_MANAGER);
        exportScriptResources(applicationName);
        copyArtifact(applicationName);
        packageApplication(applicationName);
    }

    private void exportClasspathResources(final String directory, final String... resources){

        exportClasspathResources(new ReplacementPair[0], directory, resources);
    }

    /**
     * Export the packaged resources to the "target" build directory.
     */
    private void exportClasspathResources(final ReplacementPair[] replacementPairs, final String directory, final String... resources){

        assert directory != null : "Missing 'final String directory' in 'private void exportClasspathResource(final String directory, final String resourceName)'. 'directory' can be an empty string to indicate a relative root path.";

        Arrays.stream(resources)
            .forEach(r -> exportClasspathResource(directory, r, replacementPairs));
    }

    /**
     * Export the packaged resource to the target application distribution directory.
     */
    private void exportClasspathResource(final String directory, final String resourceName, final ReplacementPair... replacementPairs){

        assert directory != null : "Missing 'final String directory' in 'private void exportClasspathResource(final String directory, final String resourceName)'. 'directory' can be an empty string to indicate a relative root path.";
        assert resourceName != null && !"".equals(resourceName.trim()) : "Missing 'String resource' in 'private void exportClasspathResource(String resourceName, String directory)'";

        try(final InputStream stream = this.getClass().getClassLoader().getResourceAsStream(resourceName)){

            if(stream == null){
                throw new WijeeException("Cannot get resource \"" + resourceName + "\" from Jar file because the input stream was null.");
            }

            final Path
                directoryName = Paths.get("target", directory),
                target = Paths.get("target\\" + directory + "\\" + resourceName);

            if(notExists(directoryName)){
                createDirectory(directoryName);
            }

            if(exists(target)){
                delete(target);
            }
            if(replacementPairs != null && replacementPairs.length > 0){
                String answer = IOUtils.toString(stream, StandardCharsets.UTF_8);
                for(ReplacementPair pair : replacementPairs){
                    answer = answer.replaceAll(pair.token, pair.replacement);
                }
                write(target, answer.getBytes());
            }
            else{
                copy(stream, target);
            }
        }
        catch(IOException e){

            e.printStackTrace();
            throw new WijeeIOException("Cannot get resource \"" + resourceName + "\" from Jar file. Reason: " + e.getMessage(), e);
        }
    }

    /**
     * Copy a file from the specified path to the target build directory.
     */
    private void copyFile(Path source, Path destination){

        assert source != null : "The 'source' parameter cannot be 'null' in method 'private void copyFile(File fromFile, File toDirectory)'";
        assert destination != null : "The 'destination' parameter cannot be 'null' in method 'private void copyFile(File fromFile, File toDirectory)'";

        try{

            final File from = source.toFile();
            if(!from.exists()){
                throw new FileNotFoundException("Could not find the file indicated by the path '" + source.toString() + "'.");
            }
            final File to = destination.toFile();
            if(!to.exists()){
                createFile(destination);
            }
            if(from.isDirectory()){
                walk(source, FileVisitOption.FOLLOW_LINKS).forEach(
                    p -> {
                        try{
                            copy(p,
                                to.isDirectory() ? destination.resolve(p) : destination,
                                REPLACE_EXISTING, COPY_ATTRIBUTES);
                        }
                        catch(IOException e){
                            e.printStackTrace();
                            throw new WijeeIOException(e.getMessage(), e);
                        }
                    }
                );
            }
            else{
                copy(source,
                    to.isDirectory() ? destination.resolve(source) : destination,
                    REPLACE_EXISTING, COPY_ATTRIBUTES);
            }
        }
        catch(IOException e){

            e.printStackTrace();
            throw new WijeeIOException("Error reading file '" + source.toString() + "'. " + e.getMessage(), e);
        }
    }

    /**
     * Copies the install and uninstall scripts into the distribution directory.
     */
    private void exportScriptResources(final String directory){

        assert directory != null : "'String directory' cannot be null. Empty is allowed, as it can represent a relative path (root).";

        exportClasspathResources(replacementPairs, directory, INSTALL_CMD_PATH, UNINSTALL_CMD_PATH);
    }

    /**
     * Copy the executable jar or war file into the target applications distribution directory.
     */
    private void copyArtifact(final String directory){

        assert directory != null : "'String directory' cannot be null. Empty is allowed, as it can represent a relative path (root).";

        copyFile(
            Paths.get(config.getArtifactPath()),
            Paths.get(directory, directory + config.getArtifactExtension()));
    }

    /**
     * Packages the contents of the directory matching the given
     * application name into a zip archive.     *
     */
    private void packageApplication(final String applicationName){

        assert applicationName != null && !"".equals(applicationName.trim()) : "'String applicationName' cannot be null or empty.";

        final Path directory = Paths.get(applicationName + ".zip");
        try(final FileOutputStream fileOutputStream = new FileOutputStream(directory.toFile());
            final ZipOutputStream zipStream = new ZipOutputStream(fileOutputStream)){

            newDirectoryStream(directory)
                .forEach(path -> addToZipFile(path, zipStream));
        }
        catch(IOException e){
            log.log(Level.SEVERE, "Error while zipping.", e);
            throw new WijeeIOException(e);
        }
    }

    /**
     * Adds the file referenced by the given path to the zip archive.
     */
    private void addToZipFile(final Path path, final ZipOutputStream zipStream){

        assert path != null : "'final Path path' cannot be null. Method: private void addToZipFile(final Path path, final ZipOutputStream zipStream)";
        assert zipStream != null : "'final ZipOutputStream zipStream' cannot be null. Method: private void addToZipFile(final Path path, final ZipOutputStream zipStream)";

        final File file = path.toFile();

        try(final FileInputStream inputStream = new FileInputStream(file)){

            zipStream.putNextEntry(
                new ZipEntry(file.getName())
                    .setCreationTime(
                        fromMillis(file.lastModified())
                    )
            );
            final byte[] readBuffer = new byte[2048];
            int amountRead = inputStream.read(readBuffer);
            while(amountRead > 0){
                zipStream.write(readBuffer, 0, amountRead);
                amountRead = inputStream.read(readBuffer);
            }
        }
        catch(IOException e){
            e.printStackTrace();
            throw new WijeeIOException("Unable to package '" + path.toString() + "'. " + e.getMessage(), e);
        }
    }
}
