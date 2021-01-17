package com.xib.assessment.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.xib.assessment.response.ErrorResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value());
        if (ex.getCause() instanceof InvalidFormatException) {
            InvalidFormatException formatException = (InvalidFormatException) ex.getCause();
            List<String> errors = formatException.getPath().stream().map(error -> String.format("A valid %s is required ", error.getFieldName())).collect(Collectors.toList());
            errorResponse.setErrors(errors);
        }
        return buildResponse(errorResponse);
    }

    @ExceptionHandler({EntityNotFoundException.class, NotFoundException.class})
    protected ResponseEntity<Object> handleEntityNotFound(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(exception.getMessage());
        return buildResponse(errorResponse);
    }

    @ExceptionHandler({ValidationException.class})
    protected ResponseEntity<Object> handleBadRequest(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(exception.getMessage());
        return buildResponse(errorResponse);
    }

    @ExceptionHandler({ConflictException.class})
    protected ResponseEntity<Object> handleConflict(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.CONFLICT.value());
        errorResponse.setMessage(exception.getMessage());
        return buildResponse(errorResponse);
    }

    @ExceptionHandler({InternalServerException.class})
    protected ResponseEntity<Object> handleInternalServer(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setMessage(exception.getMessage());
        return buildResponse(errorResponse);
    }

    private ResponseEntity<Object> buildResponse(ErrorResponse errorResponse) {
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorResponse.getStatus()));
    }
}
