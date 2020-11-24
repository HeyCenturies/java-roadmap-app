package com.example.algorithm.demo.Controller;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerAdviceExample {
    
    private static final String MESSAGE = "Something Unexpected Happened";


    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(RuntimeException e, WebRequest request){
        
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {throw e;}
        return new ResponseEntity<>(MESSAGE+"\r\n"+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
