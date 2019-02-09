package com.airplane.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Generic exception class to handle different exceptions
 * Created by Mahesh Maykarkar on 09/02/19.
 */

@ControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(NoRecordFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoRecordFoundException(HttpServletRequest req, NoRecordFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(HttpStatus.NO_CONTENT.value());
        errorResponse.setMessage(ex.getErrorMessage());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookingValidationException.class)
    public ResponseEntity<ErrorResponse> handleBookingValidationException(HttpServletRequest req, BookingValidationException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
        errorResponse.setMessage(ex.getErrorMessage());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
