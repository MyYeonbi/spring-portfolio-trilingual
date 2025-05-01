package com.sparta.springenglishchinese.repository;

import com.sparta.springenglishchinese.entity.Memo;
import java.sql.PreparedStatement;
import java.sql.Statement;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class MemoRepository {

  private final JdbcTemplate jdbcTemplate;

  public MemoRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public Memo save(Memo memo) {
    // DB저장
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

    return memo;
  }
}
