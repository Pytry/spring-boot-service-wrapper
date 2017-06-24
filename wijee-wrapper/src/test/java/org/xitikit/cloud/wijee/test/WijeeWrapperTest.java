package org.xitikit.cloud.wijee.test;

import org.junit.Test;
import org.xitikit.cloud.wijee.WijeeException;
import org.xitikit.cloud.wijee.WijeeWrapper;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Copyright Xitikit.org ${year}
 *
 * @author J. Keith "I can fly!" Hoopes
 */
public class WijeeWrapperTest{

    /**
     * Results should be the same if built is called with parameters.
     */
    @Test
    public void build(){

        validate(WijeeWrapper.build("test", "test"));
    }

    /**
     * jarPath cannot be null
     */
    @Test(expected = WijeeException.class)
    public void buildNullJarPath(){

        validate(WijeeWrapper.build(null, "test"));
    }

    /**
     * jarPath cannot be empty
     */
    @Test(expected = WijeeException.class)
    public void buildEmptyJarPath(){

        validate(WijeeWrapper.build("", "test"));
    }

    /**
     * jarPath cannot be all whitespace
     */
    @Test(expected = WijeeException.class)
    public void buildWhitespaceJarPath(){

        validate(WijeeWrapper.build(" \t\n\r", "test"));
    }

    /**
     * jarPath cannot be null
     */
    @Test(expected = WijeeException.class)
    public void buildNullName(){

        validate(WijeeWrapper.build("test", null));
    }

    /**
     * jarPath cannot be empty
     */
    @Test(expected = WijeeException.class)
    public void buildEmptyName(){

        validate(WijeeWrapper.build("test", ""));
    }

    /**
     * jarPath cannot be all whitespace
     */
    @Test(expected = WijeeException.class)
    public void buildWhitespaceName(){

        validate(WijeeWrapper.build("test", " \t\n\r"));
    }

    /**
     * Results should be the same regardless of which wither is called first.
     */
    @Test
    public void withJarPath(){

        validate(WijeeWrapper
            .withJarPath("test")
            .withName("test")
            .build());
    }

    /**
     * Results should be the same regardless of which wither is called first.
     */
    @Test
    public void withName(){

        validate(WijeeWrapper
            .withName("test")
            .withJarPath("test")
            .build());
    }

    /**
     * jarPath cannot be null
     */
    @Test(expected = WijeeException.class)
    public void withNullJarPath(){

        validate(WijeeWrapper
            .withJarPath(null)
            .withName("test")
            .build());
    }

    /**
     * jarPath cannot be empty
     */
    @Test(expected = WijeeException.class)
    public void withEmptyJarPath(){

        validate(WijeeWrapper
            .withJarPath("")
            .withName("test")
            .build());
    }

    /**
     * jarPath cannot be all whitespace
     */
    @Test(expected = WijeeException.class)
    public void withWhitespaceJarPath(){

        validate(WijeeWrapper
            .withJarPath(" \t\n\r")
            .withName("test")
            .build());
    }

    /**
     * jarPath cannot be null
     */
    @Test(expected = WijeeException.class)
    public void withNullName(){

        validate(WijeeWrapper
            .withName(null)
            .withJarPath("test")
            .build());
    }

    /**
     * jarPath cannot be empty
     */
    @Test(expected = WijeeException.class)
    public void withEmptyName(){

        validate(WijeeWrapper
            .withName("")
            .withJarPath("test")
            .build());
    }

    /**
     * jarPath cannot be all whitespace
     */
    @Test(expected = WijeeException.class)
    public void withWhitespaceName(){

        validate(WijeeWrapper
            .withName(" \t\n\r")
            .withJarPath("test")
            .build());
    }

    @Test
    public void canChangeBeforeBuild(){

        validate(WijeeWrapper
            .withName("diff")
            .withJarPath("diff")
            .withName("test")
            .withJarPath("test")
            .build());
    }

    private void validate(WijeeWrapper wrapper){

        assertNotNull(wrapper);
        assertEquals(wrapper.getJarPath(), "test");
        assertEquals(wrapper.getName(), "test");
    }
}