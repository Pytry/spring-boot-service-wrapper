package org.xitikit.cloud.wijee.test;

import org.junit.Test;
import org.xitikit.cloud.wijee.ClasspathResources;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

/**
 * Copyright Xitikit.org ${year}
 *
 * @author J. Keith "Pixy Dust" Hoopes
 */
public class ClasspathResourcesTest{

    @Test
    public void load() throws IOException{

        assertNotNull(ClasspathResources.COMMONS_DAEMON_JAR);
        assertNotNull(ClasspathResources.COMMONS_DAEMON_NATIVE_SOURCES);
        assertNotNull(ClasspathResources.COMMONS_SERVICE_32);
        assertNotNull(ClasspathResources.COMMONS_SERVICE_64);
        assertNotNull(ClasspathResources.COMMONS_SERVICE_MANAGER);
        assertNotNull(ClasspathResources.TOMCAT_NATIVE_DLL_32);
        assertNotNull(ClasspathResources.TOMCAT_NATIVE_DLL_64);
    }
}