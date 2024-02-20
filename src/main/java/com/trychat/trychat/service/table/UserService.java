package com.trychat.trychat.service.table;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.trychat.trychat.bean.table.Authorization;
import com.trychat.trychat.bean.table.User;
import com.trychat.trychat.repository.AuthorizationRepository;
import com.trychat.trychat.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepo;  // Userテーブル操作用

  private final AuthorizationRepository authoRepo; // authoテーブル操作用

  private final String DEFAULT_ROL = "USER";  // デフォルトユーザロール

  /**
   * データベースに保存する
   * @param user {@link User} 保存する内容
   * @return {@link User} 保存後
   */
  public User save(User user){

    // 保存&結果を返す
    return userRepo.save(user);
  }


  /**
   * ユーザを作成する
   * @param id ユーザID
   * @param name 表示名
   * @param mail メールアドレス
   * @param password パスワード
   * @param rol ロール
   * @return {@link User}
   */
  public User createUser(String id,String name,String mail,String password,String rol){

    // ユーザインスタンス生成
    User user = new User(id,mail,name,new Date(),null);

    // ユーザを保存
    user = this.save(user);

    // 認証情報作成
    Authorization auth = new Authorization(user.getId(),password,rol);

    // 認証情報を保存
    authoRepo.save(auth);

    // 結果を返す
    return user;
  }

    /**
   * ユーザを作成する
   * @param id ユーザID
   * @param name 表示名
   * @param mail メールアドレス
   * @param password パスワード
   * @return {@link User}
   */
  public User createUser(String id,String name,String mail,String password){
    return this.createUser(id, name, mail, password,DEFAULT_ROL);
  }


  /**
   * 検索する
   * @param id
   * @return
   */
  public Optional<User> findByID(String id){
    // データベールから検索し結果を返す
    return userRepo.findById(id);
  }
}
