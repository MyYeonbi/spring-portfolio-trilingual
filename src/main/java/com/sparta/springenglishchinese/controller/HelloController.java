package com.sparta.springenglishchinese.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class HelloController {
  @GetMapping("/hello")
  @ResponseBody
  public String hello() {
    return
        "Hello World";
  }


  @GetMapping("/get")
  @ResponseBody
  public String get() {
    return "Get Method Request";
  }

  @PostMapping("/post")
  @ResponseBody
  public String post() {
    return "Post Method Request";
  }


  @PutMapping("/put")
  @ResponseBody
  public String put() {
    return "Put Method Request";
  }

  @DeleteMapping("/delete")
  @ResponseBody
  public String delete() {
    return "Delete Method Request";
  }
}
