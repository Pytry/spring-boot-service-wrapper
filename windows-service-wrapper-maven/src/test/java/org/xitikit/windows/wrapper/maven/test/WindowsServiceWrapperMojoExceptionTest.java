package org.xitikit.windows.wrapper.maven.test;

import org.junit.Test;
import org.xitikit.windows.wrapper.maven.WindowsServiceWrapperMojoException;

/**
 * Copyright Xitikit.org ${year}
 *
 * Ensures all constructors are present.
 *
 * @author J. Keith "I can fly!" Hoopes
 */
public class WindowsServiceWrapperMojoExceptionTest{

    @Test(expected = WindowsServiceWrapperMojoException.class)
    public void windowsServiceWrapperMojoExceptionEmpty(){

        throw new WindowsServiceWrapperMojoException();
    }

    @Test(expected = WindowsServiceWrapperMojoException.class)
    public void windowsServiceWrapperMojoExceptionMessage(){

        throw new WindowsServiceWrapperMojoException("test");
    }

    @Test(expected = WindowsServiceWrapperMojoException.class)
    public void windowsServiceWrapperMojoExceptionMessageCause(){

        throw new WindowsServiceWrapperMojoException("test", new Exception());
    }

    @Test(expected = WindowsServiceWrapperMojoException.class)
    public void windowsServiceWrapperMojoExceptionCause(){

        throw new WindowsServiceWrapperMojoException(new Exception());
    }

    @Test(expected = WindowsServiceWrapperMojoException.class)
    public void windowsServiceWrapperMojoExceptionMessageCauseEnableSuppressionWritableStacktrace(){

        throw new WindowsServiceWrapperMojoException("", new Exception(), false, false);
    }
}