package com.sparta.springenglishchinese.controller;


import com.sparta.springenglishchinese.dto.MemoRequestDto;
import com.sparta.springenglishchinese.dto.MemoResponseDto;
import com.sparta.springenglishchinese.entity.Memo;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MemoController {


  private final Map<Long, Memo> memoList = new HashMap<>();

  @PostMapping("/memos")
  public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto) {
    // RequestDto -> Entity
    Memo memo = new Memo(requestDto);

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
  public List<MemoResponseDto> getMemos() {
    // Map To List
    List<MemoResponseDto> responseList = memoList.values().stream().map(MemoResponseDto::new)
        .toList();

    return responseList;
  }

  @PutMapping("/memos/{id}")
  public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
    // 해당 메모가 DB에 존재하는지 확인
    if (memoList.containsKey(id)) {
      // 해당 메모 가져오기
      Memo memo = memoList.get(id);

      // memo 수정
      memo.update(requestDto);
      return memo.getId();
    } else {
      throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다. ");
    }
  }

  @DeleteMapping("/memos/{id}")
  public Long deleteMemo(@PathVariable Long id) {
    // 해당 메모가 DB에 존재하는지 확인
    if (memoList.containsKey(id)) {
      // 해당 메모를 삭제하기
      memoList.remove(id);
      return id;
    } else {
      throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다. ");
    }
  }


}
