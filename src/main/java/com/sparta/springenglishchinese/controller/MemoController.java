package com.sparta.springenglishchinese.controller;


import com.sparta.springenglishchinese.dto.MemoRequestDto;
import com.sparta.springenglishchinese.dto.MemoResponseDto;
import com.sparta.springenglishchinese.entity.Memo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MemoController {

  @PostMapping("/memos")
  public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto){
    // RequestDto -> Entity
    Memo memo = new Memo( requestDto);
  }

}
