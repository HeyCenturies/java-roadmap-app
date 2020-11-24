package com.example.algorithm.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class ExceptionExample extends RuntimeException {

	public ExceptionExample(String msg){
        super(msg);
    }

}
