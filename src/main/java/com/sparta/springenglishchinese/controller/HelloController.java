package com.sparta.springenglishchinese.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
  @GetMapping("/api/hello")
  @ResponseBody
  public String hello() {
    return
        "Hello World";
  }


  @GetMapping("/api/get")
  @ResponseBody
  public String get() {
    return "Get Method Request";
  }

  @PostMapping("/api/hello")
  @ResponseBody
  public String post() {
    return "Post Method Request";
  }


  @PutMapping("/api/put")
  @ResponseBody
  public String put() {
    return "Put Method Request";
  }

  @DeleteMapping("/api/delete")
  @ResponseBody
  public String delete() {
    return "Delete Method Request";
  }
}
