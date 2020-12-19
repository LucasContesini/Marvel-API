package com.contesini.marvel.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class RequestParameterException extends Exception {

    private HttpStatus status;

    public RequestParameterException(String msg) {
        super(msg);
    }

    public RequestParameterException(String msg, HttpStatus status) {
        super(msg);
        this.status = status;
    }
}
