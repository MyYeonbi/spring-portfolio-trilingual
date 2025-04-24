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

}
