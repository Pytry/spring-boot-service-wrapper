package org.xitikit.cloud.wijee;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Copyright Xitikit.org ${year}
 *
 * @author J. Keith "Beirdo" Hoopes
 */
public class WijeeWrapper{

    private final String
        jarPath,
        name;

    private WijeeWrapper(final String jarPath, final String name){

        assert jarPath != null && !"".equals(jarPath.trim());
        assert name != null && !"".equals(name.trim());

        this.jarPath = jarPath;
        this.name = name;
    }

    /**
     * Export a packaged resource to the "target" build directory.
     *
     * @param resourceName ie.: "/SmartLibrary.dll"
     */
    public void exportResource(String resourceName, Path target){

        try(InputStream stream = WijeeWrapper.class.getResourceAsStream(resourceName)){

            if(stream == null){
                throw new WijeeException("Cannot get resource \"" + resourceName + "\" from Jar file because the input stream was null.");
            }
            Files.copy(stream, target);
        }
        catch(IOException e){

            e.printStackTrace();
            throw new WijeeException("Cannot get resource \"" + resourceName + "\" from Jar file. Reason: " + e.getMessage(), e);
        }
    }

    public void wrap(){

        exportResource(ClasspathResources.COMMONS_DAEMON_JAR, Paths.get("target/"+name));
    }

    public String getJarPath(){

        return jarPath;
    }

    public String getName(){

        return name;
    }

    public static final class Builder{

        private String jarPath = "";
        private String name = "";

        private Builder(){

        }

        public Builder withJarPath(final String jarPath){

            this.jarPath = jarPath == null ? "" : jarPath.trim();
            return this;
        }

        public Builder withName(final String name){

            this.name = name == null ? "" : name.trim();
            return this;
        }

        public WijeeWrapper build(){

            return new WijeeWrapper(this.jarPath, this.name);
        }
    }

    public static WijeeWrapper build(String jarPath, String name){

        require(jarPath, "Missing Required Parameter 'jar-path");
        require(name, "Missing Required Parameter 'name'");

        return new WijeeWrapper(jarPath, name);
    }

    public static Builder withJarPath(final String jarPath){

        require(jarPath, "Missing Required Parameter 'jar-path");

        return new Builder().withJarPath(jarPath);
    }

    public static Builder withName(final String name){

        require(name, "Missing Required Parameter 'name'");
        return new Builder().withName(name);
    }

    private static void require(String in, String message){

        if(in == null || "".equalsIgnoreCase(in.trim())){
            throw new WijeeException(message);
        }
    }
}
