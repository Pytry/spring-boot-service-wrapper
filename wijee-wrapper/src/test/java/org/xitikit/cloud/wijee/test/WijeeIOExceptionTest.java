package org.xitikit.cloud.wijee.test;

import org.junit.Test;
import org.xitikit.cloud.wijee.WijeeIOException;

/**
 * Copyright Xitikit.org ${year}
 *
 * OCD tests to ensures all constructors are present.
 *
 * @author J. Keith "That Yoga Creep" Hoopes
 */
public class WijeeIOExceptionTest{

    @Test(expected = WijeeIOException.class)
    public void windowsServiceWrapperMojoExceptionEmpty() throws WijeeIOException{

        throw new WijeeIOException();
    }

    @Test(expected = WijeeIOException.class)
    public void windowsServiceWrapperMojoExceptionMessage() throws WijeeIOException{

        throw new WijeeIOException("test");
    }

    @Test(expected = WijeeIOException.class)
    public void windowsServiceWrapperMojoExceptionMessageCause() throws WijeeIOException{

        throw new WijeeIOException("test", new Throwable());
    }

    @Test(expected = WijeeIOException.class)
    public void windowsServiceWrapperMojoExceptionCause() throws WijeeIOException{

        throw new WijeeIOException(new Throwable());
    }
}