package com.airplane.booking.exception;

import lombok.Data;

/**
 * ustom exception class to handle validations scenarios
 * Created by Mahesh Maykarkar on 09/02/19.
 */

@Data
public class BookingValidationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String errorMessage;

    public BookingValidationException() {
        super();
    }

    public BookingValidationException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
}
