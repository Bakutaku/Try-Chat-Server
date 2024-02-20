package com.trychat.trychat.service.table;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.trychat.trychat.bean.table.Authorization;
import com.trychat.trychat.bean.table.User;
import com.trychat.trychat.repository.AuthorizationRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorizationService {
  private final AuthorizationRepository authoRepo;  // Authorizationテーブル操作用

  private final UserService userData; // Userテーブル操作用


  /**
   * データベースに保存する
   * @param autho {@link Authorization}
   * @return {@link Authorization}
   */
  public Authorization save(Authorization autho){
    return authoRepo.save(autho);
  }

  /**
   * 検索する
   * @param id UserID
   * @return
   */
  public Optional<Authorization> findByID(String id){

    // Userテーブルを検索する
    Optional<User> user = userData.findByID(id);

    // 取得できたか確かめる
    if(user.isPresent()){
      // 出来ていたらauthoテーブルを検索し結果を返す
      return authoRepo.findById(user.get().getId());
    }
    
    // ユーザがいなかったため空のOptionalを返す
    return Optional.empty();
  }


}
