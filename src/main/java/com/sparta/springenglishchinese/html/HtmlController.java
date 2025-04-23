package com.sparta.springenglishchinese.html;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController {

  @GetMapping("/static-hello")
  public String hello() {
    return "hello.html";
  }

  @GetMapping("/html/redirect")
  public String htmlStatic() {
    return "redirect:/hello.html";

  }

}
