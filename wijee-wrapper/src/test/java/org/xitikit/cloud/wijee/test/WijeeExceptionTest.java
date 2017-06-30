package org.xitikit.cloud.wijee.test;

import org.junit.Test;
import org.xitikit.cloud.wijee.WijeeException;

/**
 * Copyright Xitikit.org ${year}
 *
 * Ensures all constructors are present and accept correct exception types.
 *
 * @author J. Keith "That Yoga Creep" Hoopes
 */
public class WijeeExceptionTest{

    @Test(expected = WijeeException.class)
    public void windowsServiceWrapperMojoExceptionEmpty(){

        throw new WijeeException();
    }

    @Test(expected = WijeeException.class)
    public void windowsServiceWrapperMojoExceptionMessage(){

        throw new WijeeException("test");
    }

    @Test(expected = WijeeException.class)
    public void windowsServiceWrapperMojoExceptionMessageCause(){

        throw new WijeeException("test", new Exception());
    }

    @Test(expected = WijeeException.class)
    public void windowsServiceWrapperMojoExceptionCause(){

        throw new WijeeException(new Exception());
    }

    @Test(expected = WijeeException.class)
    public void windowsServiceWrapperMojoExceptionMessageCauseEnableSuppressionWritableStacktrace(){

        throw new WijeeException("", new Exception(), false, false);
    }
}