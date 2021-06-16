package com.platformtest.error.exception;

import com.platformtest.error.ErrorType;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public abstract class BaseBusinessException extends RuntimeException {

  private final ErrorType code;
  private final String message;

  protected BaseBusinessException(
      ErrorType code, String message) {
    this.code = code;
    this.message = message;
    log.error("Code: {}; Message: {}", code, message);
  }
}
