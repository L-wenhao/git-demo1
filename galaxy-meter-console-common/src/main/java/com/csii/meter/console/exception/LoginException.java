package com.csii.meter.console.exception;

import com.csii.meter.console.enums.ResultCode;

/**
 * LoginException.
 */
public class LoginException extends RuntimeException {

    private static final long serialVersionUID = 8068509879445395353L;

    private String errorCode = ResultCode.LOGIN_FAIL.getCode();

    /**
     * Instantiates a new gateway exception.
     *
     * @param e the e
     */
    public LoginException(final Throwable e) {
        super(e);
    }

    /**
     * Instantiates a new gateway exception.
     *
     * @param message the message
     */
    public LoginException(final String message) {
        super(message);
    }



    /**
     * Instantiates a new gateway exception.
     *
     * @param message   the message
     * @param throwable the throwable
     */
    public LoginException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
