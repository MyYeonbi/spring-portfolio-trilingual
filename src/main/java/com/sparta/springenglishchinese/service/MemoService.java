package com.sparta.springenglishchinese.service;

import com.sparta.springenglishchinese.dto.MemoRequestDto;
import com.sparta.springenglishchinese.dto.MemoResponseDto;
import com.sparta.springenglishchinese.entity.Memo;
import com.sparta.springenglishchinese.repository.MemoRepository;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class MemoService {

private final MemoRepository memoRepository;

public MemoService(ApplicationContext context){
  // 1. 'Bean'이름으로 가져오기
 /* MemoRepository memoRepository = (MemoRepository) context.getBean("memoRepository");*/

  // 2. 'Bean' 클래스 형식으로 가져오기
  MemoRepository memoRepository = context.getBean(MemoRepository.class);
  this.memoRepository = memoRepository;

}



/*  public MemoService(MemoRepository memoRepository) {
    this.memoRepository = memoRepository;
  }*/

  public MemoResponseDto createMemo(MemoRequestDto requestDto) {
  // 이 메서드의 리턴값이 바로 컨트롤러의 createMemo 리턴값이 되어버리므로 이 메서드도 createMemo 메서드의 반환유형과 같게 MemoResponseDto가 되어야 함.

    // RequestDto -> Entity
    Memo memo = new Memo(requestDto);

    // DB 저장
    Memo saveMemo = memoRepository.save(memo);



    // Entity -> ResponseDTo
    MemoResponseDto memoResponseDto = new MemoResponseDto(memo);

    return memoResponseDto;
  }

  public List<MemoResponseDto> getMemos() {
    // DB조회
    return memoRepository.findAllByOrderByModifiedAtDesc().stream().map(MemoResponseDto::new).toList();


  }


  public List<MemoResponseDto> getMemosByKeyword(String keyword) {
    return memoRepository.findAll().stream().map(MemoResponseDto::new).toList()
  }

  @Transactional
  public Long updateMemo(Long id, MemoRequestDto requestDto) {

    // 해당 메모가 DB에 존재하는지 확인
    Memo memo = findMemo(id);

      // memo 내용 수정
      memo.update(requestDto);

      return id;

  }




  public Long deleteMemo(Long id) {

    // 해당 메모가 DB에 존재하는지 확인
  Memo memo = findMemo(id);

      // memo 내용 삭제
      memoRepository.delete(memo);
      return id;

  }

  private Memo findMemo(Long id) {
    return memoRepository.findById(id).orElseThrow(() ->
        new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
    );
  }
}
