package com.sparta.springenglishchinese.controller;


import com.sparta.springenglishchinese.dto.MemoRequestDto;
import com.sparta.springenglishchinese.dto.MemoResponseDto;
import com.sparta.springenglishchinese.entity.Memo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
    // DB조회
    String sql = "SELECT * FROM memo";

    return jdbcTemplate.query(sql, new RowMapper<MemoResponseDto>() {

      @Override
      public MemoResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        // SQL의 결과로 받아온 Memo 데이터들을 MemoResponseDTO 타입으로 변환해줄 메서드
        Long id = rs.getLong("id");
        String username = rs.getString("username");
        String contents = rs.getString("contents");
        return new MemoResponseDto(id, username, contents);
      }
    });
  }

  @PutMapping("/memos/{id}")
  public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
    // 해당 메모가 DB에 존재하는지 확인
    Memo memo = findById(id);
    if (memo != null) {
      // memo 내용 수정
      String sql = "UPDATE memo SET username = ?, contents = ?  WHERE id = ?";
      jdbcTemplate.update(sql, requestDto.getUsername(), requestDto.getContents(), id);

      return id;
    } else {
      throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다. ");
    }
  }

  @DeleteMapping("/memos/{id}")
  public Long deleteMemo(@PathVariable Long id) {
    // 해당 메모가 DB에 존재하는지 확인
    Memo memo = findById(id);
    if (memo != null) {
      // 메모 삭제
      String sql = "DELETE FROM memo WHERE id = ?";
      jdbcTemplate.update(sql, id);

      return id;
    } else {
      throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다. ");
    }
  }



}
