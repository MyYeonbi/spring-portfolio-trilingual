package com.sparta.springenglishchinese.dto;


import com.sparta.springenglishchinese.entity.Memo;
import lombok.Getter;

@Getter
public class MemoResponseDto {
 private Long id;
 private String username;
 private String contents;

 public MemoResponseDto(Long id, String username, String contents) {
  this.id = id;
  this.username = username;
  this.contents = contents;
 }

 public MemoResponseDto(Memo memo) {
  this.id = memo.getId();
  this.username = memo.getUsername();
  this.contents = memo.getContents();
 }
}
