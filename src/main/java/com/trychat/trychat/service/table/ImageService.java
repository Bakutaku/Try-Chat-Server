package com.trychat.trychat.service.table;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.trychat.trychat.bean.table.Images;
import com.trychat.trychat.repository.ImageRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageService {
  private final ImageRepository imageRepo;  // Imageテーブル操作用

  /**
   * データベースに保存する
   * @param image {@link Images}
   * @return {@link Images}
   */
  public Images save(Images image){

    // IDが設定されているか調べる
    if(image.getId() == null){
      image.setId(UUID.randomUUID());
    }

    // 保存 & 結果を返す
    return imageRepo.save(image);
  }

  /**
   * 検索する
   * @param id
   * @return
   */
  public Optional<Images> findByID(UUID id){
    // 検索し結果を返す
    return imageRepo.findById(id);
  }


}
