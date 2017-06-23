package org.xitikit.windows.wrapper.maven;

/**
 * Copyright Xitikit.org ${year}
 *
 * Constants used for retrieving resources needed to run
 * Java Applications as a service in windows.
 *
 * @author J. Keith "I can fly!" Hoopes
 */
public final class ClasspathResources{

    private static final String
        apacheHome = "apache",
        commonsDaemonVersion = "1.0.15",
        commonsDaemonHome = apacheHome + "\\commons-daemon-" + commonsDaemonVersion,
        tomcatNativeVersion = "1.2.12",
        tomcatNativeHome = apacheHome + "\\tomcat-native-" + tomcatNativeVersion;

    public static final String
        COMMONS_DAEMON_JAR = commonsDaemonHome + "\\commons-daemon-" + commonsDaemonVersion + ".jar",
        COMMONS_DAEMON_NATIVE_SOURCES = commonsDaemonHome + "\\commons-daemon-" + commonsDaemonVersion + "-native-src.tar.gz",
        COMMONS_SERVICE_32 = commonsDaemonHome + "\\windows\\prunsrv.exe",
        COMMONS_SERVICE_64 = commonsDaemonHome + "\\windows\\amd64\\prunsrv.exe",
        COMMONS_SERVICE_MANAGER = commonsDaemonHome + "\\windows\\prunmgr.exe",
        TOMCAT_NATIVE_DLL_32 = tomcatNativeHome + "\\bin\\tcnative-1.dll",
        TOMCAT_NATIVE_DLL_64 = tomcatNativeHome + "\\bin\\x64\\tcnative-1.dll";
}
