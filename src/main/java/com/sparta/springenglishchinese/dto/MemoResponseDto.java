package com.sparta.springenglishchinese.dto;


import com.sparta.springenglishchinese.entity.Memo;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class MemoResponseDto {
 private Long id;
 private String username;
 private String contents;
 private LocalDateTime createdAt;
 private LocalDateTime modifiedAt;



 public MemoResponseDto(Memo memo) {
  this.id = memo.getId();
  this.username = memo.getUsername();
  this.contents = memo.getContents();
  this.createdAt = memo.getCreatedAt();
  this.modifiedAt = memo.getModifiedAt();
 }
}
