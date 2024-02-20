package com.trychat.trychat.bean.table;

import java.util.Date;

import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ユーザテーブル
 * ユーザ情報を格納するテーブル
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
  @Id
  private String id;  // ユーザID
  private String mail;  // メールアドレス
  private String name;  // 表示名
  private Date created_at;  // 作成日
  
  @LastModifiedDate
  private Date updated_at;  // 更新日
}
