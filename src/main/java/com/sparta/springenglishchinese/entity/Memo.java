package com.sparta.springenglishchinese.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Memo {
  private Long id; //메모끼리 구분하기 위해.
  private String username;
  private String contents;


}
