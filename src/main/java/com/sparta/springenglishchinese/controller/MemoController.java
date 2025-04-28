package com.sparta.springenglishchinese.controller;


import com.sparta.springenglishchinese.dto.MemoRequestDto;
import com.sparta.springenglishchinese.dto.MemoResponseDto;
import com.sparta.springenglishchinese.entity.Memo;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MemoController {


  private final Map<Long, Memo> memoList = new HashMap<>();
  @PostMapping("/memos")
  public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto){
    // RequestDto -> Entity
    Memo memo = new Memo( requestDto);


     // Memo Max ID Check
    Long maxId = memoList.size() > 0 ? Collections.max(memoList.keySet()) + 1 : 1;
    memo.setId(maxId);

     // DB 저장
    memoList.put(memo.getId(), memo);

    // Entity - > Response Dto
    MemoResponseDto responseDto = new MemoResponseDto(memo);

    return responseDto;
  }

  @GetMapping("/memos")
  public List<MemoResponseDto> getMemos(){
    // Map To List
    List<MemoResponseDto> responseList = memoList.values().stream().map(MemoResponseDto::new).toList();
  }


}
