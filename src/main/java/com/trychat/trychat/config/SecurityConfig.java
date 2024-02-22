package com.trychat.trychat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.trychat.trychat.filter.JwtAuthenticationFilter;
import com.trychat.trychat.service.security.UserDetailsService;
import com.trychat.trychat.service.table.AuthorizationService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final JwtAuthenticationFilter jwtFilter;  // JWT認証フィルター

  private final AuthorizationService authoData; // 認証テーブル操作用

  /**
   * パスワードのハッシュ化
   */
  @Bean
  protected PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  /**
   * ログイン設定
   */
  @Bean
  public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{

    http.csrf(csrf -> csrf
      .disable()
    ).authorizeHttpRequests(autho -> autho
      .requestMatchers("/login").permitAll()
      .requestMatchers("/public/**").permitAll()
      .anyRequest().authenticated()
    ).addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // JWT認証のフィルターを適応

    return http.build();
  }

  /**
   * 認証の設定
   */
  @Bean
  public UserDetailsManager userDetailsManager(){
    UserDetailsManager manager = new UserDetailsService(authoData);

    // manager.createUser(User.withUsername("user").password(passwordEncoder().encode("12345")).build());

    return manager;
  }


}
