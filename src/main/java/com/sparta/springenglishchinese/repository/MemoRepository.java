package com.sparta.springenglishchinese.repository;

import com.sparta.springenglishchinese.entity.Memo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;



public interface MemoRepository extends JpaRepository<Memo, Long> {
  List<Memo> findAllByOrderByModifiedAtDesc();
  

}
