package com.samyati.user_management.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.samyati.user_management.dto.ResponseDto;
import com.samyati.user_management.model.ErrorParameter;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ResponseDto> handleApplicationException(ApplicationException ex) {
        ResponseDto response = new ResponseDto();
        ErrorParameter error = new ErrorParameter();

        error.setErrorCode(ex.getErrorCode());
        error.setErrprDescription(ex.getMessage());
        response.setErrorParam(error);
        response.setStatus("failed");

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
