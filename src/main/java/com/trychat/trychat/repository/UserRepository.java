package com.trychat.trychat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trychat.trychat.bean.table.User;

public interface UserRepository extends JpaRepository<User,String>{
  
}
