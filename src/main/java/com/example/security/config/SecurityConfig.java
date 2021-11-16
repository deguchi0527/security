package com.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    // パスワードの暗号化用に、BCrypt(ビー・クリプト)を使用
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // アクセス権限の設定
    http.authorizeRequests()
        // 制限なし
        .antMatchers("/").permitAll()
        // '/admin'は、'ADMIN'ロールのみアクセス可
        .antMatchers("/admin").hasRole("ADMIN")
        // 他は制限あり
        .anyRequest().authenticated();
    // ログイン処理の設定
    http.formLogin();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // インメモリでユーザ認証処理
    auth.inMemoryAuthentication()
        // ユーザ名'user',パスワード'user',ロール'USER'のユーザを追加
        .withUser("user").password(passwordEncoder().encode("user")).roles("USER")
        // 接続詞
        .and()
        // ユーザ名'admin',パスワード'admin',ロール'ADMIN'のユーザを追加
        .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
  }
}