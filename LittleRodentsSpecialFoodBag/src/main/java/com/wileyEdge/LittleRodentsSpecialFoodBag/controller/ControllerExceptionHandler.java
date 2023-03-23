package com.wileyEdge.LittleRodentsSpecialFoodBag.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String SQL_MESSAGE = "Invalid restaurant ID. Make sure that the restaurant ID is a positive integer " +
            " and corresponds to an existing and available restaurant. Go back to try again, or " +
            " go to the main page to see the list of available restaurants.";

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public final ResponseEntity<Error> handleSqlException( // Handles invalid game IDs when posting a guess
                                                           SQLIntegrityConstraintViolationException ex,
                                                           WebRequest request) {

        Error err = new Error();
        err.setMessage(SQL_MESSAGE);
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
