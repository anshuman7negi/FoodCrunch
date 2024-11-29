package com.samyati.user_management.exceptions;

public enum ErrorCode {
    USER_EXISTS("USER_EXISTS", "User already exists"),
    INVALID_PASSWORD("INVALID_PASSWORD", "Invalid password"),
    RESOURCE_NOT_FOUND("RESOURCE_NOT_FOUND", "Resource not found"),
    USER_NOT_EXISTS("USER_NOT_EXISTS", "user does not present"),
    GENERIC_ERROR("GENERIC_ERROR", "An unexpected error occurred");

    private String code;
    private String description;

    ErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
