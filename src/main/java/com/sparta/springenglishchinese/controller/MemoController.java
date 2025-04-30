package com.sparta.springenglishchinese.controller;


import com.sparta.springenglishchinese.dto.MemoRequestDto;
import com.sparta.springenglishchinese.dto.MemoResponseDto;
import com.sparta.springenglishchinese.service.MemoService;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
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

    MemoService memoService = new MemoService(jdbcTemplate); //인스턴스화
    return memoService.createMemo(requestDto); //memoService에 있는 createMemo메서드에서 전부다 비지니스 로직이 수행되고 리턴이 되면 그값을 바로 리턴하여  클라이언트에게 전달.






  }

  @GetMapping("/memos")
  public List<MemoResponseDto> getMemos() {
    MemoService memoService = new MemoService(jdbcTemplate);
    return memoService.getMemos();

  }

  @PutMapping("/memos/{id}")
  public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
    MemoService memoService = new MemoService(jdbcTemplate);
    return memoService.updateMemo(id, requestDto);

  }

  @DeleteMapping("/memos/{id}")
  public Long deleteMemo(@PathVariable Long id) {
    MemoService memoService = new MemoService(jdbcTemplate);
    return memoService.deleteMemo(id);


  }
}
