package org.xitikit.cloud.wijee;

/**
 * Copyright Xitikit.org ${year}
 *
 * Indicate that an IO exception was thrown during the execution of the wijee API.
 *
 * @author J. Keith "Where are my keys?" Hoopes
 */
public class WijeeIOException extends WijeeException{

    public WijeeIOException(){

    }

    public WijeeIOException(final String message){

        super(message);
    }

    public WijeeIOException(final String message, final Throwable cause){

        super(message, cause);
    }

    public WijeeIOException(final Throwable cause){

        super(cause);
    }
}
