package com.sparta.springenglishchinese.service;

import com.sparta.springenglishchinese.dto.MemoRequestDto;
import com.sparta.springenglishchinese.dto.MemoResponseDto;
import com.sparta.springenglishchinese.entity.Memo;
import java.sql.PreparedStatement;
import java.sql.Statement;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class MemoService {

  private final JdbcTemplate jdbcTemplate;

  public MemoService(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public MemoResponseDto createMemo(MemoRequestDto requestDto) {
  // 이 메서드의 리턴값이 바로 컨트롤러의 createMemo 리턴값이 되어버리므로 이 메서드도 createMemo 메서드의 반환유형과 같게 MemoResponseDto가 되어야 함.

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
}
