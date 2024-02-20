package com.trychat.trychat.bean.table;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 画像テーブル
 * アップロードされた画像のファイルパスを管理するテーブル
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "images")
public class Images {
  @Id
  private UUID id;  // 画像ID(ファイルパスも兼ねている)
  private Date created_at;  // アップロード日
}
