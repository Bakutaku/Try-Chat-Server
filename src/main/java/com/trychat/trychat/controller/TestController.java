package com.trychat.trychat.controller;

import org.springframework.web.bind.annotation.RestController;

import com.trychat.trychat.utils.Utils;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequiredArgsConstructor
public class TestController {

  private final Utils utils;  // よく使うものをまとめたもの
  
  @GetMapping("test")
  public String getMethodName() {
      return "Hello World";
  }

  @GetMapping("login")
  public String login() {
      return "成功";
  }

  @GetMapping("login_user")
  public String login_user() {
      return utils.getUserName();
  }

}
