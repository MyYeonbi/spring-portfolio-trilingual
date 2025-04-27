package com.sparta.springenglishchinese.entity;


import com.sparta.springenglishchinese.dto.MemoRequestDto;
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


  public Memo(MemoRequestDto requestDto) {
    this.username = requestDto.getUsername();
    this.contents = requestDto.getContents();
  }
}
