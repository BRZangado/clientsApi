package com.platformtest.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import com.platformtest.error.Error;

import com.platformtest.error.ErrorField;
import com.platformtest.error.ErrorType;
import com.platformtest.error.StandardError;
import com.platformtest.error.exception.BaseBusinessException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ClientControllerExceptionHandler {

  private static final String INTERNAL_SERVER_ERROR_MESSAGE = "Internal Server Error";

  @ExceptionHandler(NullPointerException.class)
  public ResponseEntity<Void> handleNullPointerException(final NullPointerException ex) {
    log.error("Null pointer exception", ex);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Void> handleException(final Exception ex) {
    log.error(INTERNAL_SERVER_ERROR_MESSAGE, ex);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
  }

  @ExceptionHandler(BaseBusinessException.class)
  public ResponseEntity<StandardError> handleBaseBusinessException(final BaseBusinessException ex) {
    log.error("BaseBusinessException", ex);
    return ResponseEntity.status(UNPROCESSABLE_ENTITY).body(StandardError.builder()
        .error(Error.builder()
            .code(ex.getCode().name())
            .message(ex.getMessage())
            .build())
        .build());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<StandardError> handleValidationExceptions(
      MethodArgumentNotValidException ex) {

    List<ErrorField> errors = new ArrayList<>();
    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      ErrorField errorField = new ErrorField(error.getField(), error.getDefaultMessage());
      errors.add(errorField);
    }

    return ResponseEntity.status(BAD_REQUEST).body(StandardError.builder()
        .error(Error.builder()
            .code(ErrorType.BPT01.name())
            .message(ErrorType.BPT01.getMessage())
            .fields(errors)
            .build())
        .build());
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<StandardError> handleEmptyPayload(
      HttpMessageNotReadableException ex) {
    return ResponseEntity.status(BAD_REQUEST).body(StandardError.builder()
        .error(Error.builder()
            .code(ErrorType.BPT01.name())
            .message(ErrorType.BPT01.getMessage())
            .build())
        .build());
  }
}
