package com.example.security.controller;

import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;

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

  @GetMapping("/login")
  public String login() {
    // login.htmlを表示
    return "login";
  }

  @PostMapping("/login*")
  public String login(@ModelAttribute("user") String user,
      @RequestAttribute(name = WebAttributes.AUTHENTICATION_EXCEPTION, required = false) Exception exception,
      Model model) {
    model.addAttribute("message", user);
    if (exception != null) {
      model.addAttribute("message", exception.getMessage());
    }
    // login.htmlを表示
    return "login";
  }
}
