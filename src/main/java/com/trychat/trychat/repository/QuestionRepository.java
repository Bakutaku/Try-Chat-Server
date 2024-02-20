package com.trychat.trychat.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trychat.trychat.bean.table.Question;

public interface QuestionRepository extends JpaRepository<Question,UUID>{
  
}
