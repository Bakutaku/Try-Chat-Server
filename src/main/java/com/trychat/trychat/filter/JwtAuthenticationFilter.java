package com.trychat.trychat.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.trychat.trychat.service.security.UserDetailsService;
import com.trychat.trychat.utils.JwtUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/**
 * JWT認証を行うFilter
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{

  private final JwtUtils jwt; // jwt操作用
  private final UserDetailsService userDetailsService;  // 認証確認用

  private final Logger logger = LoggerFactory.getLogger(getClass());  // ログ用

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    
    // トークン取得
    String token = jwt.getToken(request);

    // ユーザIDを取得
    String userid = jwt.extractUsername(token);

    // 取得できたか調べる
    if(token != null && userid != null){
      
      // ユーザ情報を取得
      UserDetails user = userDetailsService.loadUserByUsername(userid);

      // 取得できたか
      if(user != null){
        // 認証情報作成
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.getUsername(),null,user.getAuthorities());

        // 認証情報を設定(ログイン済みにする)
        SecurityContextHolder.getContext().setAuthentication(authentication);

        logger.info("Login with token:{}",user.getUsername());
      }
    }

    // 次のフィルターに移動する
    filterChain.doFilter(request, response);
  }
  
}
