package com.cstangga.ebookland.securityconfig;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {


    /**
     * 정적 파일에 대해서는 인증/인가 검사를 수행하지 않는다.
     * @return
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("css/**", "js/**",  "scss/**", "bookImage/**", "fonts/**", "json/**", "assets/**", "svg/**"); // 이 경로로 시작하는 것들은 보안 검사를 하지 말라는 의미
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /**
         * - permitAll() : 모두 허용
         * - authenticated() : 인증된 사용자만 허용
         * - anonymous() : 인증하지 않은 사용자만 허용
         * - hasRole(), hasAnyRole : 특정 권한이 있는 사용자만 허용
         */
        http
                .csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests((registry) -> {
                    registry

                            // 누구나 허용
                            .requestMatchers("/", "/index.html","/auth/login","/member/**","/noticeboard/**","/ws/chat/**","/chat/**","/member/api/member/roomId","/ws-stomp/**").permitAll()

                            // 로그인 안 한 사용자에게 허용되는 페이지
                            .requestMatchers( "/auth/login","/bookboard/","/noticeboard/","/ws/chat/**","/chat/**").anonymous()

                            // 인증된 사용자만 허용 - 로그인 한 사용자를 의미함
                            .requestMatchers("/noticeboard/**","/bookboard/**","/member/mypage","/noticeboard/detail/","/ws/chat/**","/chat/**").authenticated()

                            // ROLE_ADMIN 권한이 있는 사용자만 허용
                            .requestMatchers("/admin/**","/bookboard/**","/noticeboard/**","/ws/chat/**","/chat/**").hasRole("ADMIN")

                            // 나머지들은 이렇게 해주세요~
                            .anyRequest().authenticated();
                }).headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)); // 람다로 작성하게 되어있음

        /**
         * 폼 로그인 설정
         * -
         */
        http.formLogin(configurer -> {
            configurer
                    .loginPage("/auth/login") // GET 방식의 로그인 폼 요청
                    .loginProcessingUrl("/auth/login") // html에서 로그인 POST요청을 하면 이 기능이 실행된다
                    .successHandler(customAuthenticationSuccessHandler()) // 사용자 정의 인증 성공 핸들러 설정
                    .defaultSuccessUrl("/", true) // 로그인 성공 후 이동 페이지 customAuthenticationSuccessHandler 여기서 해주고 있음
                    .usernameParameter("email") // login.html의 input태그의 name 속성을 입력합니다.
                    .passwordParameter("password") // login.html의 input태그의 name 속성을 입력합니다.
                    .permitAll(); //로그인 페이지는 인증 없이 접근 가능
        });

        /**
         * 로그아웃 설정 - POST 요청만 가능하다.
         */
        http.logout(configurer -> {
            configurer.logoutUrl("/auth/logout") // POST 요청으로 보내야 함
                    .logoutSuccessUrl("/");// 로그아웃 후 리다리엑트 url(기본값은 로그인 페이지)
        });
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 암호화 처리
    }

    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }
}