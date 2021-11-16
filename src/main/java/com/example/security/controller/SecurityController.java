package com.example.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

  @GetMapping
  public String top() {
    // top.htmlを表示
    return "top";
  }

  @GetMapping("/admin")
  public String admin() {
    // admin.htmlを表示
    return "admin";
  }

  @GetMapping("/sample")
  public String sample() {
    // sample.htmlを表示
    return "sample";
  }
}
