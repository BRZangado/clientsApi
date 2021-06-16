package com.platformtest.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Ignore("Base entity for ControllerTest")
public class AbstractControllerTest {

  @Test
  public void init(){
    assertTrue(true);
  }

  protected MockMvc mockMvc;

  protected void setUp(Object controller) {
    mockMvc = MockMvcBuilders.standaloneSetup(controller)
        .setControllerAdvice(ClientControllerExceptionHandler.class)
        .setValidator(new LocalValidatorFactoryBean())
        .build();
  }
}
