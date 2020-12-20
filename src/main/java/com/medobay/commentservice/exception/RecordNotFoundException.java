package com.medobay.commentservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)//404
public class RecordNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;
    public RecordNotFoundException(String exception) {
        super(exception);
    }
}