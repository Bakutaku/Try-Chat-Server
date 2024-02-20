package com.trychat.trychat.bean.table;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 質問の投稿内容
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posts")
public class Question {
  @Id
  private UUID id;  // 質問ID

  @ManyToOne
  @JoinColumn
  private User user;  // 投稿者
  private String title; // タイトル
  private String body;  // 投稿内容
  // private List<Images> images; // 投稿画像
  private Boolean notDispName;  // 匿名
}
