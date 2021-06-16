package com.platformtest.error.exception;

import com.platformtest.error.ErrorType;

public class ClientNotFoundException extends BaseBusinessException{

  public ClientNotFoundException() {
    super(ErrorType.BPT02, ErrorType.BPT02.getMessage());
  }
}
