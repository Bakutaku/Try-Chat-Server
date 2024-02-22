package com.trychat.trychat.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * よく使うメソッドを集めたクラス
 */
@Component
public class Utils {
  
  /**
   * ログインしているユーザー名を取得する
   * @return いない場合はnullが返ってくる。
   */
  public String getUserName(){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if(authentication != null){
      Object principal = authentication.getPrincipal();
      
      if(principal instanceof UserDetails){
        return ((UserDetails) principal).getUsername();
      }
    }

    return null;
  }

}
