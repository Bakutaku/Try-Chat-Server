package com.trychat.trychat.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trychat.trychat.bean.table.Images;

public interface ImageRepository extends JpaRepository<Images,UUID>{
  
}
