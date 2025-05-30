package com.sparta.springenglishchinese;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.springenglishchinese.response.Star;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JacksonTest {

  @Test
  @DisplayName("Object To JSON : get Method 필요")
  void test1() throws JsonProcessingException {
    Star star = new Star("yeonbi",30);

    ObjectMapper objectMapper = new ObjectMapper(); //Jackson 라이브러리의 ObjectMapper
    String json = objectMapper.writeValueAsString(star);

    System.out.println("json = " + json);

  }

  @Test
  @DisplayName("JSON To Object : 기본 생성자 & (get OR set) Method 필요")
  void test2() throws JsonProcessingException {
    String json = "{\"name\":\"yeonbi\",\"age\":30}"; // JSON 타입의 String

    ObjectMapper objectMapper = new ObjectMapper(); //Jackson 라이브러리의 ObjectMapper

    Star star = objectMapper.readValue(json, Star.class);
    System.out.println("star.getName = " + star.getName());
    System.out.println("star.getAge() = " + star.getAge());
  }

}
