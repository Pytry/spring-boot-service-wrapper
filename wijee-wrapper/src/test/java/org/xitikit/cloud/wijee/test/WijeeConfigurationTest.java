package org.xitikit.cloud.wijee.test;

import org.junit.Test;
import org.xitikit.cloud.wijee.WijeeConfiguration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Copyright Xitikit.org ${year}
 * Ensure nulls are not present and apName defaults properly to artifact name without suffix.
 *
 * @author J. Keith Hoopes
 */
public class WijeeConfigurationTest{

    @Test
    public void wijeeConfigurationTest(){

        WijeeConfiguration c = new WijeeConfiguration();
        c.setAppName("test");
        c.setArtifactPath("test.jar");
        c.setClasspath("lib.jar");
        c.setStartClass("com.start.Me");

        assertTrue(c.getArtifactPath().equals("test.jar"));
        assertTrue(c.getAppName().equals("test"));
        assertTrue(c.getClasspath().equals("lib.jar"));
        assertTrue(c.getStartClass().equals("com.start.Me"));
        assertTrue(c.getArtifactExtension().equals(".jar"));
        assertTrue(c.isValid());

        c.setWarName("test.war");
        assertTrue(c.getArtifactExtension().equals(".war"));

        c.setJarName("test.ear");
        assertTrue(c.getArtifactExtension().equals(".ear"));
        assertFalse(c.isValid());

        c = new WijeeConfiguration(null, null, null, null);

        assertTrue(c.getArtifactPath().equals(""));
        assertTrue(c.getAppName().equals(""));
        assertTrue(c.getClasspath().equals(""));
        assertTrue(c.getStartClass().equals(""));
        assertFalse(c.isValid());

        c = new WijeeConfiguration(" target/dumb.war ", "really-dumb", null, null);
        assertTrue(c.getAppName().equals("really-dumb"));

        c.setJarName("one.jar");
        assertTrue(c.getAppName().equals("really-dumb"));
        assertTrue(c.getArtifactExtension().equals(".jar"));

        c.setAppName("");
        assertTrue(c.getAppName().equals("one"));
        assertTrue(c.getArtifactExtension().equals(".jar"));

        c.setWarName("two.war");
        assertTrue(c.getAppName().equals("two"));
        assertTrue(c.getArtifactExtension().equals(".war"));

        assertTrue(c.isValid());

        c.setArtifactPath(null);
        assertFalse(c.isValid());

        c.setArtifactPath("test.bar");
        assertFalse(c.isValid());
        c.setArtifactPath("test");
        assertFalse(c.isValid());

        c = new WijeeConfiguration();
        assertFalse(c.isValid());
    }
}