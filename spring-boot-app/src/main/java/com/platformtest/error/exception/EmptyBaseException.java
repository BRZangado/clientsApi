package com.platformtest.error.exception;

import com.platformtest.error.ErrorType;

public class EmptyBaseException extends BaseBusinessException{

  public EmptyBaseException() {
    super(ErrorType.BPT03, ErrorType.BPT03.getMessage());
  }
}
