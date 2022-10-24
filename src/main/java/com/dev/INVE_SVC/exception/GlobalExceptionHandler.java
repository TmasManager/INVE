package com.dev.INVE_SVC.exception;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
    /* Developer Custom Exception */
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<Map<String, Object>> handleCustomException(final CustomException e) {
    	Map<String, Object> returnMap = new HashMap<>();
    	returnMap.put("code", e.getCode());
    	returnMap.put("message", e.getMessage());
        return ResponseEntity.ok(returnMap);
    }
    
    /* HTTP 400 Exception */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<String> MethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        return ResponseEntity.status(ErrorCode.BAD_REQUEST.getStatus().value()).body(ErrorCode.BAD_REQUEST.getMessage());
    }

    /* HTTP 404 Exception */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<String> NoHandlerFoundException(final NoHandlerFoundException e) {
        return ResponseEntity.status(ErrorCode.POSTS_NOT_FOUND.getStatus().value()).body(ErrorCode.POSTS_NOT_FOUND.getMessage());
    }
    
    /* HTTP 405 Exception */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<String> handleHttpRequestMethodNotSupportedException(final HttpRequestMethodNotSupportedException e) {
        return ResponseEntity.status(ErrorCode.METHOD_NOT_ALLOWED.getStatus().value()).body(ErrorCode.METHOD_NOT_ALLOWED.getMessage());
    }

    /* HTTP 500 Exception */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<String> handleException(final Exception e) {
    	e.printStackTrace();
        return ResponseEntity.status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus().value()).body(ErrorCode.INTERNAL_SERVER_ERROR.getMessage());
    }

}