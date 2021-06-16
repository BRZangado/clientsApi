package com.platformtest.error;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StandardError {
  private final Error error;
}
