package com.sparta.springenglishchinese.response;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/response/rest")
public class ResponseRestController {

  // [Response header]
  // Content-Type : text/html
  // [Response body]
  // {"name" : "yeonbi", "age" :"30" }

  @GetMapping("/json/string")
  public String helloStringJson(){
    return "{\"nama\":\"yeonbi\",\"age\":30}";
  }

  // [Response header]
  // Content-Type: application/json
  // [Response body]
  // {"name": "yeonbi", "age":30}

  @GetMapping("/json/class")
  public Star helloClassJson() {
    return new Star("yeonbi", 30);
  }
}
