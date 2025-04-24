package com.sparta.springenglishchinese.request;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello/request")
public class RequestController {

    @GetMapping("/form/html")
    public String helloForm(){
      return "hello-request-form";
    }


    // [Request sample]
    // GET http://localhost:8080/hello/request/star/yeonbi/age/30

  @GetMapping("/star/{name}/age/{age}")
  @ResponseBody
  public String helloRequestPath(@PathVariable String name, @PathVariable int age){
      return String.format("Hello, @PathVariable, <br> name = %s, age = %d,", name,age);
  }



  // [Request sample]
  // Get http://localhost:8080/hello/request/form/param?name=yeonbi&age=30

  @GetMapping("/form/param")
  @ResponseBody
  public String helloGetRequestParam(@RequestParam String name, @RequestParam int age){
      return String.format("Hello, @PathVariable, <br> name = %s, age = %d,", name,age);
  }

}
