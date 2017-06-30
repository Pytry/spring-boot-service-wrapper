package org.xitikit.cloud.wijee;

/**
 * Copyright Xitikit.org ${year}
 *
 * Constants used for retrieving resources needed to run
 * Java Applications as a service in windows. 
 *
 * @author J. Keith "Stop giving me nicknames" Hoopes
 */
public final class ClasspathResources{

    private static final String
        tomcatResourcesHome = "tomcat",
        apacheHome = tomcatResourcesHome + "/native-libraries/apache",
        scriptsHome = tomcatResourcesHome + "/scripts",
        commonsDaemonVersion = "1.0.15",
        commonsDaemonHome = apacheHome + "/commons-daemon-" + commonsDaemonVersion;


    public static final String
        COMMONS_DAEMON_JAR = commonsDaemonHome + "/commons-daemon-" + commonsDaemonVersion + ".jar",
        COMMONS_DAEMON_NATIVE_SOURCES = commonsDaemonHome + "/commons-daemon-" + commonsDaemonVersion + "-native-src.tar.gz",
        COMMONS_SERVICE_32 = commonsDaemonHome + "/windows/prunsrv.exe",
        COMMONS_SERVICE_64 = commonsDaemonHome + "/windows/amd64/prunsrv.exe",
        COMMONS_SERVICE_MANAGER = commonsDaemonHome + "/windows/prunmgr.exe",
        INSTALL_CMD = "install.cmd",
        INSTALL_CMD_PATH = scriptsHome + "/" + INSTALL_CMD,
        UNINSTALL_CMD = "uninstall.cmd",
        UNINSTALL_CMD_PATH = scriptsHome + "/" + UNINSTALL_CMD;
}
