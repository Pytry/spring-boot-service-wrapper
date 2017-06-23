package org.xitikit.windows.wrapper.maven.test;

import org.junit.Test;
import org.xitikit.windows.wrapper.maven.WindowsServiceWrapper;
import org.xitikit.windows.wrapper.maven.WindowsServiceWrapperMojoException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Copyright Xitikit.org ${year}
 *
 * @author J. Keith "I can fly!" Hoopes
 */
public class WindowsServiceWrapperTest{

    /**
     * Results should be the same if built is called with parameters.
     */
    @Test
    public void build(){

        validate(WindowsServiceWrapper.build("test", "test"));
    }

    /**
     * jarPath cannot be null
     */
    @Test(expected = WindowsServiceWrapperMojoException.class)
    public void buildNullJarPath(){

        validate(WindowsServiceWrapper.build(null, "test"));
    }

    /**
     * jarPath cannot be empty
     */
    @Test(expected = WindowsServiceWrapperMojoException.class)
    public void buildEmptyJarPath(){

        validate(WindowsServiceWrapper.build("", "test"));
    }

    /**
     * jarPath cannot be all whitespace
     */
    @Test(expected = WindowsServiceWrapperMojoException.class)
    public void buildWhitespaceJarPath(){

        validate(WindowsServiceWrapper.build(" \t\n\r", "test"));
    }

    /**
     * jarPath cannot be null
     */
    @Test(expected = WindowsServiceWrapperMojoException.class)
    public void buildNullName(){

        validate(WindowsServiceWrapper.build("test", null));
    }

    /**
     * jarPath cannot be empty
     */
    @Test(expected = WindowsServiceWrapperMojoException.class)
    public void buildEmptyName(){

        validate(WindowsServiceWrapper.build("test", ""));
    }

    /**
     * jarPath cannot be all whitespace
     */
    @Test(expected = WindowsServiceWrapperMojoException.class)
    public void buildWhitespaceName(){

        validate(WindowsServiceWrapper.build("test", " \t\n\r"));
    }

    /**
     * Results should be the same regardless of which wither is called first.
     */
    @Test
    public void withJarPath(){

        validate(WindowsServiceWrapper
            .withJarPath("test")
            .withName("test")
            .build());
    }

    /**
     * Results should be the same regardless of which wither is called first.
     */
    @Test
    public void withName(){

        validate(WindowsServiceWrapper
            .withName("test")
            .withJarPath("test")
            .build());
    }

    /**
     * jarPath cannot be null
     */
    @Test(expected = WindowsServiceWrapperMojoException.class)
    public void withNullJarPath(){

        validate(WindowsServiceWrapper
            .withJarPath(null)
            .withName("test")
            .build());
    }

    /**
     * jarPath cannot be empty
     */
    @Test(expected = WindowsServiceWrapperMojoException.class)
    public void withEmptyJarPath(){

        validate(WindowsServiceWrapper
            .withJarPath("")
            .withName("test")
            .build());
    }

    /**
     * jarPath cannot be all whitespace
     */
    @Test(expected = WindowsServiceWrapperMojoException.class)
    public void withWhitespaceJarPath(){

        validate(WindowsServiceWrapper
            .withJarPath(" \t\n\r")
            .withName("test")
            .build());
    }

    /**
     * jarPath cannot be null
     */
    @Test(expected = WindowsServiceWrapperMojoException.class)
    public void withNullName(){

        validate(WindowsServiceWrapper
            .withName(null)
            .withJarPath("test")
            .build());
    }

    /**
     * jarPath cannot be empty
     */
    @Test(expected = WindowsServiceWrapperMojoException.class)
    public void withEmptyName(){

        validate(WindowsServiceWrapper
            .withName("")
            .withJarPath("test")
            .build());
    }

    /**
     * jarPath cannot be all whitespace
     */
    @Test(expected = WindowsServiceWrapperMojoException.class)
    public void withWhitespaceName(){

        validate(WindowsServiceWrapper
            .withName(" \t\n\r")
            .withJarPath("test")
            .build());
    }

    @Test(expected = WindowsServiceWrapperMojoException.class)
    public void allNull(){

        validate(WindowsServiceWrapper
            .withName(null)
            .withJarPath(null)
            .build());
    }

    @Test
    public void canChangeBeforeBuild(){

        validate(WindowsServiceWrapper
            .withName("diff")
            .withJarPath("diff")
            .withName("test")
            .withJarPath("test")
            .build());
    }

    @Test
    public void copyCommonsDaemon() throws Exception{

    }

    @Test
    public void exportResource() throws Exception{

    }

    @Test
    public void wrap() throws Exception{

        WindowsService
    }

    private void validate(WindowsServiceWrapper wrapper){

        assertNotNull(wrapper);
        assertEquals(wrapper.getJarPath(), "test");
        assertEquals(wrapper.getName(), "test");
    }
}