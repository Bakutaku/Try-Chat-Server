package com.trychat.trychat.service.security;

import java.util.Optional;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import com.trychat.trychat.bean.table.Authorization;
import com.trychat.trychat.service.table.AuthorizationService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserDetailsService implements UserDetailsManager{

  private final AuthorizationService authoData; // 認証テーブル操作用

  /**
   * ユーザを検索する
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    
    // DBからユーザ名に一致するデータを取得する
    Optional<Authorization> autho = authoData.findByID(username);

    // 取得できたか調べる
    if(autho.isPresent()){
      // 見つかった場合
      // 中身を取り出す
      Authorization temp = autho.get();

      // 結果を返す
      return User.withUsername(temp.getId())
        .password(temp.getPassword())
        .roles(temp.getRole())
        .build();
    }

    // 見つからなかった場合例外を返す
    throw new UnsupportedOperationException("Not Found "+username);
  }

  @Override
  public void createUser(UserDetails user) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'createUser'");
  }

  @Override
  public void updateUser(UserDetails user) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
  }

  @Override
  public void deleteUser(String username) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
  }

  /**
   * パスワード変更
   */
  @Override
  public void changePassword(String oldPassword, String newPassword) {

    // TODO パスワードを変更する処理を書く
    

    throw new UnsupportedOperationException("Unimplemented method 'changePassword'");
  }

  /**
   * ユーザがいるかどうか
   */
  @Override
  public boolean userExists(String username) {
    return authoData.existsById(username);
  }
  
}
