package com.example.demo.error;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice

public class ExceptionControllerAdvice {




    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> errorResponse(MethodArgumentNotValidException e){

        return ResponseEntity.badRequest().body(e.getFieldError().getField()+"   "+ e.getFieldError().getDefaultMessage());

    }



    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> errorResponse(NullPointerException e){

        return ResponseEntity.badRequest().body("incorrect input ");

    }

}
