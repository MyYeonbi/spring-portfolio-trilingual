package com.sparta.springenglishchinese.service;

import com.sparta.springenglishchinese.dto.MemoRequestDto;
import com.sparta.springenglishchinese.dto.MemoResponseDto;
import com.sparta.springenglishchinese.entity.Memo;
import com.sparta.springenglishchinese.repository.MemoRepository;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

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
    MemoRepository memoRepository = new MemoRepository(jdbcTemplate);
    Memo saveMemo = memoRepository.save(memo);



    // Entity -> ResponseDTo
    MemoResponseDto memoResponseDto = new MemoResponseDto(memo);

    return memoResponseDto;
  }

  public List<MemoResponseDto> getMemos() {
    // DB조회
    MemoRepository memoRepository = new MemoRepository(jdbcTemplate);
    return memoRepository.findAll();


  }

  public Long updateMemo(Long id, MemoRequestDto requestDto) {
    MemoRepository memoRepository = new MemoRepository(jdbcTemplate);

    // 해당 메모가 DB에 존재하는지 확인
    Memo memo = memoRepository.findById(id);
    if (memo != null) {
      // memo 내용 수정
      memoRepository.update(id, requestDto);



      return id;
    } else {
      throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다. ");
    }
  }




  public Long deleteMemo(Long id) {

    MemoRepository memoRepository = new MemoRepository(jdbcTemplate);

    // 해당 메모가 DB에 존재하는지 확인
    Memo memo = memoRepository.findById(id);
    if (memo != null) {
      // memo 내용 삭제
      memoRepository.delete(id);
      return id;
    } else {
      throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다. ");
    }


  }
}
