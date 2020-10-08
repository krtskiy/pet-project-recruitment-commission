package com.epam.koretskyi.commission.exception;

/**
 * @author D.Koretskyi on 29.09.2020.
 */
public class DBException extends AppException {
    private static final long serialVersionUID = -3929497264675899975L;

    public DBException() {
        super();
    }

    public DBException(String message, Throwable cause) {
        super(message, cause);
    }
}
