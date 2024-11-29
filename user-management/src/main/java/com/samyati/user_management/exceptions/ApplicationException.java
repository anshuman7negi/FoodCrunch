package com.samyati.user_management.exceptions;

public class ApplicationException extends RuntimeException {

    private String errorCode;

    // Constructor
    public ApplicationException(ErrorCode errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode.getCode();
    }

    // Getter for error code
    public String getErrorCode() {
        return errorCode;
    }
}
    
