package org.laba.exception;

import java.util.Objects;
import static org.laba.exception.Error.MAPPER_ERROR;

/**
 * The MapperException wraps all checked standard Java exception and enriches them with a custom error code.
 * You can use this code to retrieve localized error messages and to link to our documentation.
 *
 *
 */
public class MapperException extends Exception {

    private static final long serialVersionUID = 11L;
    private final long errorCode;

    public static MapperException wrap(Throwable exception, long errorCode) {
        if (exception instanceof MapperException) {
            MapperException se = (MapperException) exception;
            if (Objects.isNull(errorCode) != true && errorCode != se.getErrorCode()) {
                return new MapperException(exception.getMessage(), exception, errorCode);
            }
            return se;
        } else {
            return new MapperException(exception.getMessage(), exception, errorCode);
        }
    }

    public MapperException(long errorCode) {
        this.errorCode = errorCode;
    }

    public MapperException(String message, long errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public MapperException(Throwable cause, long errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public MapperException(String message, Throwable cause, long errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public long getErrorCode() {
        return errorCode;
    }

    @Override
    public String toString() {
        return "Exception message: " + getMessage() + "\n Error code: " + errorCode;
    }
}
