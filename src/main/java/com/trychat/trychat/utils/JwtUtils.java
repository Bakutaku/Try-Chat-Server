package com.trychat.trychat.utils;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import jakarta.servlet.http.HttpServletRequest;

/**
 * JWT証明書を扱うクラス
 */
@Component
public class JwtUtils {

  @Value("${jwt.security.key}")
  private String securityKey; // セキュリティキー保存用

  @Value("${jwt.security.access.expiration}")
  private Integer expiration;  // 有効期限

  @Value("${jwt.security.tolerance.expiration}")
  private int TOLERANCE_EXPIRATION_TIME;  // 許容範囲

  @Value("${jwt.security.storage.key}")
  private String STORAGE_KEY; // 格納場所

  @Value("${jwt.security.issue.name}")
  private String ISSUE; // JWT発行者

  @Value("${jwt.security.start.text}")
  private String TOKEN_START; // Tokenの始まりの文字

  private final Logger logger = LoggerFactory.getLogger(getClass());  // ログ用



  /**
   * JWTトークン生成
   * @param userID ユーザーID
   * @return JWT Token
   */
  public String generateToken(String userID){
    // アルゴリズム設定
    Algorithm alg = Algorithm.HMAC256(this.securityKey);

    // 現在時刻を取得
    Date now = new Date();

    // 有効期限取得
    Calendar cal = Calendar.getInstance();
    cal.setTime(now);
    cal.add(Calendar.MINUTE, expiration);
    Date exp = cal.getTime();

    // トークン生成
    String token = JWT.create()
      .withIssuer(ISSUE)  // 発行者
      .withSubject(userID)  // ユーザ
      .withIssuedAt(now)  // 発行日時
      .withExpiresAt(exp) // 有効期限
      .sign(alg)
    ;
      
    // 結果を返す
    return token;
  }

  /**
   * トークンの復号
   * @param token
   * @return {@link DecodedJWT}
   * <p>(取得できなかった場合はnullが返ってきます)</p>
   */
  public DecodedJWT verifyToken(String token){

    // nullが入力された場合はnullを返す
    if(token == null) return null;

    // アルゴリズム設定
    Algorithm alg = Algorithm.HMAC256(this.securityKey);

    // その他の設定
    JWTVerifier verifier = JWT.require(alg) // アルゴリズム
      .withIssuer(ISSUE)  // 発行者
      .acceptExpiresAt(TOLERANCE_EXPIRATION_TIME) // 有効期限の許容範囲
      .build();

    try{
      // 復号
      return verifier.verify(token);
    }catch(JWTVerificationException e){
      logger.debug("Tokenの複合に失敗しました。Token:{}",token);
      return null;
    }
  }


  /**
   * トークンからユーザIDを取得する
   * @param token
   * @return ユーザID
   * <p>(取得できなかった場合はnullが返ってきます)</p>
   */
  public String extractUsername(String token){

    // 復号
    DecodedJWT jwt = this.verifyToken(token);

    // 復号が失敗した場合
    if(jwt == null) return null;

    // ユーザIDを返す
    return jwt.getSubject();
  }


  /**
   * リクエストからトークンを取り出す
   * @param req {@link HttpServletRequest}
   * @return JWT Token
   */
  public String getToken(HttpServletRequest req){
    String token = req.getHeader(STORAGE_KEY);
    if(StringUtils.hasText(token) && token.startsWith(TOKEN_START)){
      // いらない文字を切り捨てて取得したトークンを返す
      return token.substring(TOKEN_START.length(),token.length());
    }
    return null;
  }

}
