package com.samyati.user_management.model;

public class ErrorParameter {

    private String errorCode;
    private String errprDescription;

    public ErrorParameter() {
    }

    public ErrorParameter(String errorCode, String errprDescription) {
        this.errorCode = errorCode;
        this.errprDescription = errprDescription;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrprDescription() {
        return errprDescription;
    }

    public void setErrprDescription(String errprDescription) {
        this.errprDescription = errprDescription;
    }

}
