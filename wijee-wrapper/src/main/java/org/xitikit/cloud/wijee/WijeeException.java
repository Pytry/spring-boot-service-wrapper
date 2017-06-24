package org.xitikit.cloud.wijee;

/**
 * Copyright Xitikit.org ${year}
 *
 * @author J. Keith "Where are my keys?" Hoopes
 */
public class WijeeException extends RuntimeException{

    public WijeeException(){

    }

    public WijeeException(final String message){

        super(message);
    }

    public WijeeException(final String message, final Throwable cause){

        super(message, cause);
    }

    public WijeeException(final Throwable cause){

        super(cause);
    }

    public WijeeException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace){

        super(message, cause, enableSuppression, writableStackTrace);
    }
}
