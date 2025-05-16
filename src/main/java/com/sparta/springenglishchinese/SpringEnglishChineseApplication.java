package com.sparta.springenglishchinese;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringEnglishChineseApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringEnglishChineseApplication.class, args);
  }

}
