package com.trychat.trychat.service.table;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.trychat.trychat.bean.table.Question;
import com.trychat.trychat.repository.QuestionRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionService {
  private final QuestionRepository questionRepo;  // Questionテーブル操作用


  /**
   * データベースに保存する
   * @param question {@link Question}
   * @return {@link Question}
   */
  public Question save(Question question){
    return questionRepo.save(question);
  }

  /**
   * 検索する
   * @param id
   * @return
   */
  public Optional<Question> findByID(UUID id){
    // データベースから検索し結果を返す
    return questionRepo.findById(id);
  }
}
