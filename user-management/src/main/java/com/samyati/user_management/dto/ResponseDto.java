package com.samyati.user_management.dto;

import com.samyati.user_management.model.ErrorParameter;

public class ResponseDto {

    private String userName;
    private String token;
    private String status;
    private ErrorParameter errorParam;

    public ResponseDto() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ErrorParameter getErrorParam() {
        return errorParam;
    }

    public void setErrorParam(ErrorParameter errorParam) {
        this.errorParam = errorParam;
    }

}
