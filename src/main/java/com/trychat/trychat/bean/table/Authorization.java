package com.trychat.trychat.bean.table;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 認証テーブル
 * ユーザ認証で使用するテーブル
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authorization")
public class Authorization {
  @Id
  private String id;  // ユーザID
  private String password;  // パスワード
  private String role; // 権限
}
