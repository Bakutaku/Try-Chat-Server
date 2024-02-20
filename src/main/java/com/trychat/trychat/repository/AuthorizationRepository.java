package com.trychat.trychat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trychat.trychat.bean.table.Authorization;

public interface AuthorizationRepository extends JpaRepository<Authorization,String>{
  
}
