package com.cdac.custom_exceptions;

import com.cdac.utils.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // This handler will handle ResourceNotFoundException only and send the error message in the response-
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException e) {
        String errMessage = e.getMessage();
        log.error(errMessage);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.builder().error(errMessage).build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        Map<String, String> map = new HashMap<String, String>();    // map because while validation more than one validation can fail and all the messages should be passed
        e.getBindingResult().getAllErrors().forEach((objectError -> {
            String field = ((FieldError)objectError).getField();
            String msg = objectError.getDefaultMessage();
            map.put(field, msg);
        }));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.builder().error(map.toString()).build());
    }

    // This handler will handle all the Exceptions other than the custom exceptions and send the error message in the response-
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> GenericExceptionHandler(Exception e) {
        String errMessage = e.getMessage();
        log.error(errMessage);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.builder().error(errMessage).build());
    }

}
