package org.xitikit.cloud.wijee.maven.test;

import org.junit.Test;
import org.xitikit.cloud.wijee.maven.WrapMojo;

import static junit.framework.TestCase.assertEquals;

/**
 * Copyright Xitikit.org ${year}
 *
 * @author J. Keith "I can fly!" Hoopes
 */
public class WrapMojoTest{

    @Test
    public void test(){

        WrapMojo wrapMojo = new WrapMojo();
        wrapMojo.setName("test");
        wrapMojo.setJarPath("test");
        wrapMojo.setStartClass("com.start.Me");
        wrapMojo.setClasspath("lib.jar");
        assertEquals(wrapMojo.getName(), "test");
        assertEquals(wrapMojo.getJarPath(), "test");
        assertEquals(wrapMojo.getStartClass(),"com.start.Me");
        assertEquals(wrapMojo.getClasspath(),"lib.jar");

        wrapMojo.setName("diff");
        wrapMojo.setJarPath("diff");
        wrapMojo.setStartClass("diff");
        wrapMojo.setClasspath("diff");

        assertEquals(wrapMojo.getName(), "diff");
        assertEquals(wrapMojo.getJarPath(), "diff");
        assertEquals(wrapMojo.getStartClass(), "diff");
        assertEquals(wrapMojo.getClasspath(), "diff");
    }
}