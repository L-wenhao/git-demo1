package com.csii.meter.console.exception;

/**
 * MeterConsoleException.
 */
public class MeterConsoleException extends RuntimeException {

    private static final long serialVersionUID = 8068509879445395353L;

    private String errorCode;

    /**
     * Instantiates a new gateway exception.
     *
     * @param e the e
     */
    public MeterConsoleException(final Throwable e) {
        super(e);
    }

    /**
     * Instantiates a new gateway exception.
     *
     * @param message the message
     */
    public MeterConsoleException(final String message) {
        super(message);
    }


    public MeterConsoleException(final String code, final String message) {
        super(message);
        this.errorCode = code;
    }

    /**
     * Instantiates a new gateway exception.
     *
     * @param message   the message
     * @param throwable the throwable
     */
    public MeterConsoleException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
