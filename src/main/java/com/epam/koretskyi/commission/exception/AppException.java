package com.epam.koretskyi.commission.exception;

/**
 * @author D.Koretskyi on 29.09.2020.
 */
public class AppException extends Exception {
    private static final long serialVersionUID = 1886678389839430285L;

    public AppException() {
        super();
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(String message) {
        super(message);
    }
}
