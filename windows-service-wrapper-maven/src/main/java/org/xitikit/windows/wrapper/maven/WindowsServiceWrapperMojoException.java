package org.xitikit.windows.wrapper.maven;

/**
 * Copyright Xitikit.org ${year}
 *
 * @author J. Keith "I can fly!" Hoopes
 */
public class WindowsServiceWrapperMojoException extends RuntimeException{

    public WindowsServiceWrapperMojoException(){

    }

    public WindowsServiceWrapperMojoException(final String message){

        super(message);
    }

    public WindowsServiceWrapperMojoException(final String message, final Throwable cause){

        super(message, cause);
    }

    public WindowsServiceWrapperMojoException(final Throwable cause){

        super(cause);
    }

    public WindowsServiceWrapperMojoException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace){

        super(message, cause, enableSuppression, writableStackTrace);
    }
}
