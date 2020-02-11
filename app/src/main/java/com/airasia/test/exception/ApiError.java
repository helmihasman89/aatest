package com.airasia.test.exception;

public class ApiError extends RuntimeException {
    // The unique code of the error.
    private int errorCode;

    /**
     *Prevent direct instantiation.
     * @param errorCode The unique code of the error.
     */
    public ApiError(int errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Get the unique code of the error.
     * @return The unique code of the error.
     */
    public int getErrorCode() {
        return errorCode;
    }
}
