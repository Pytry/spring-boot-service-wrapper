package org.xitikit.windows.wrapper.maven.test;

import org.junit.Test;
import org.xitikit.windows.wrapper.maven.WrapMojo;

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

        assertEquals(wrapMojo.getName(), "test");
        assertEquals(wrapMojo.getJarPath(), "test");

        wrapMojo.setName("diff");
        wrapMojo.setJarPath("diff");

        assertEquals(wrapMojo.getName(), "diff");
        assertEquals(wrapMojo.getJarPath(), "diff");
    }
}