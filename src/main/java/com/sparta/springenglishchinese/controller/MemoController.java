package com.sparta.springenglishchinese.controller;


import com.sparta.springenglishchinese.dto.MemoRequestDto;
import com.sparta.springenglishchinese.dto.MemoResponseDto;
import com.sparta.springenglishchinese.entity.Memo;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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

  private final JdbcTemplate jdbcTemplate;

  public MemoController(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }




  @PostMapping("/memos")
  public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto) {
    // RequestDto -> Entity
    Memo memo = new Memo(requestDto);

    // DB 저장
    KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체

    String sql = "INSERT INTO memo (username, contents) VALUES (?, ?)";
    jdbcTemplate.update(con -> {
      PreparedStatement preparedStatement = con.prepareStatement(sql,
          Statement.RETURN_GENERATED_KEYS);

      preparedStatement.setString(1, memo.getUsername());
      preparedStatement.setString(2, memo.getContents());
      return preparedStatement;

    },
        keyHolder);

    // DB Insert 후 받아온 기본키 확인
    Long id = keyHolder.getKey().longValue();
    memo.setId(id);

    // Entity -> ResponseDTo
    MemoResponseDto memoResponseDto = new MemoResponseDto(memo);

    return memoResponseDto;
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
