package org.xitikit.windows.wrapper.maven;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Executor for creating an executable that can be run as a windows service.
 * Actual logic is in the WindowsServiceWrapper class.
 */
@Mojo(name = "wrap", defaultPhase = LifecyclePhase.PACKAGE)
public class WrapMojo extends AbstractMojo{

    @Parameter(property = "jar-path", required = true)
    private String jarPath;

    @Parameter(property = "name", required = true)
    private String name;

    public void execute() throws MojoExecutionException, MojoFailureException{

        WindowsServiceWrapper
            .build(jarPath, name)
            .wrap();
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
}
