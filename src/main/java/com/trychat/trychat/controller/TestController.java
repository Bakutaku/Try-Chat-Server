package com.trychat.trychat.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class TestController {
  
  @GetMapping("test")
  public String getMethodName() {
      return "Hello World";
  }

}
