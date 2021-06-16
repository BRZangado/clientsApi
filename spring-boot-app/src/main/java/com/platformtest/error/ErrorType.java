package com.platformtest.error;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorType {

  BPT01("Incorrect Data."),
  BPT02("Client Not Found."),
  BPT03("Empty Database.");

  private final String message;

  ErrorType(String message) {
    this.message = message;
  }

  @JsonValue
  public String getMessage() {
    return this.message;
  }
}
