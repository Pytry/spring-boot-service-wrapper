package org.xitikit.cloud.wijee.maven;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.xitikit.cloud.wijee.WijeeConfiguration;
import org.xitikit.cloud.wijee.WijeeException;
import org.xitikit.cloud.wijee.WijeeWrapper;

/**
 * Executor for creating an executable that can be executed
 * as a windows service.
 */
@Mojo(name = "wrap", defaultPhase = LifecyclePhase.PACKAGE)
public class WrapMojo extends AbstractMojo{

    @Parameter(property = "jar-path", required = true)
    private String jarPath;

    @Parameter(property = "name", required = true)
    private String name;

    @Parameter(property = "startClass")
    private String startClass;

    @Parameter(property = "classpath")
    private String classpath;

    public void execute() throws MojoExecutionException, MojoFailureException{

        final WijeeConfiguration config = new WijeeConfiguration(jarPath, name, startClass, classpath);
        if(config.isValid()){
            try{
                new WijeeWrapper(config).wrap();
            }
            catch(WijeeException e){
                throw new MojoExecutionException(e.getMessage(), e);
            }
        }
        else{
            throw new MojoFailureException("Invalid Configuration.");
        }
    }

    public void setJarPath(final String jarPath){

        this.jarPath = jarPath;
    }

    public void setName(final String name){

        this.name = name;
    }

    public String getJarPath(){

        return jarPath;
    }

    public String getName(){

        return name;
    }

    public String getStartClass(){

        return startClass;
    }

    public void setStartClass(final String startClass){

        this.startClass = startClass;
    }

    public String getClasspath(){

        return classpath;
    }

    public void setClasspath(final String classpath){

        this.classpath = classpath;
    }
}
