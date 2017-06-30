package org.xitikit.cloud.wijee;

/**
 * Copyright Xitikit.org ${year}
 *
 * @author J. Keith Hoopes
 */
public class WijeeConfiguration{

    private String
        artifactPath,
        appName,
        startClass,
        classpath;

    public WijeeConfiguration(){

        setArtifactPath(null);
        setAppName(null);
        setStartClass(null);
        setClasspath(null);
    }

    public WijeeConfiguration(final String artifactPath, final String appName, final String startClass, final String classpath){

        setArtifactPath(artifactPath);
        setAppName(appName);
        setStartClass(startClass);
        setClasspath(classpath);
    }

    public boolean isValid(){

        return !"".equals(artifactPath) && (
            artifactPath.endsWith(".war") ||
                artifactPath.endsWith(".jar"));
    }

    public String getAppName(){

        final String answer;
        if(appName.length() <= 0 && artifactPath.length() > 0){
            answer = artifactPath.substring(0, artifactPath.lastIndexOf("."));
        }
        else{
            answer = appName;
        }
        return answer;
    }

    public void setAppName(final String appName){

        this.appName = appName == null ? "" : appName.trim();
    }

    public String getClasspath(){

        return classpath;
    }

    public void setClasspath(final String classpath){

        this.classpath = classpath == null ? "" : classpath.trim();
    }

    public String getArtifactPath(){

        return artifactPath;
    }

    public void setArtifactPath(final String artifactPath){

        this.artifactPath = artifactPath == null ? "" : artifactPath.trim();
    }

    public void setJarName(final String jarName){

        this.artifactPath = jarName == null ? "" : jarName.trim();
    }

    public void setWarName(final String warName){

        this.artifactPath = warName == null ? "" : warName.trim();
    }

    public String getArtifactExtension(){

        return "".equals(artifactPath) ? "" : artifactPath
            .substring(
                artifactPath.lastIndexOf("."),
                artifactPath.length()
            );
    }

    public String getStartClass(){

        if("".equals(startClass)){
            if(artifactPath.endsWith(".jar")){
                return "org.springframework.boot.loader.JarLauncher";
            }
            else if(artifactPath.endsWith(".war")){
                return "org.springframework.boot.loader.WarLauncher";
            }
        }
        return startClass;
    }

    public void setStartClass(final String startClass){

        this.startClass = startClass == null ? "" : startClass.trim();
    }
}
