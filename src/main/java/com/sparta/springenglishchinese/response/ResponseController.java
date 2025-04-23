package com.sparta.springenglishchinese.response;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/response")
public class ResponseController {

  // Content-Type: text/html
  // Response Body
  // {"name":"yeonbi","age":30}
  @GetMapping("/json/string")
  @ResponseBody
  public String helloStringJson() {
    return "{\"name\":\"yeonbi\",\"age\":30}";
  }
}
