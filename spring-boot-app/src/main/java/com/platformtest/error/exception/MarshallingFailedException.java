package com.platformtest.error.exception;

import com.platformtest.error.ErrorType;

public class MarshallingFailedException extends BaseBusinessException {

  public MarshallingFailedException() {
    super(ErrorType.BPT01, ErrorType.BPT01.getMessage());
  }
}
