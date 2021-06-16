package com.platformtest.dto.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.platformtest.error.exception.MarshallingFailedException;

public interface Common {

  default String toJson() {
    final ObjectMapper mapper = new ObjectMapper();
    try {
      mapper.registerModule(new JavaTimeModule());
      mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
      return mapper.writeValueAsString(this);
    } catch (JsonProcessingException e) {
      throw new MarshallingFailedException();
    }
  }
}
