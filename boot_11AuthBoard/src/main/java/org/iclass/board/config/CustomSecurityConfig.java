package org.iclass.board.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Slf4j
@EnableMethodSecurity(prePostEnabled = true)
public class CustomSecurityConfig {

    // 스프링 시큐리티는 서블릿 필터 기반으로 동작하면서 DispatcherServlet 이
    //       (모든 요청을 먼저 접수하고 핸들러 메소드와 매핑) 동작하기 전에
    //       시큐리티 설정 내용이 실행됩니다.
    private static final String[] PERMIT_LIST =
                           {"/","/signup",
                            "/community/list",
                            "/community/read", "/api/list/*",
                            "/css/**",
                            "/js/**",
                            "/images/**",
                            "/assets/**"};
    // "/api/list/*" : 댓글 리스트는 경로 마지막에 메인글 idx가 포함. idx 자리에 * 꼭 필요!
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info(">>>>> Custom Security Filter Chain 동작 >>>>>>> ");

          http  .csrf(csrf-> csrf.disable())  //메모장 설명 참고
                .authorizeHttpRequests(
                       request -> request.requestMatchers(PERMIT_LIST).permitAll()
                                         .anyRequest().authenticated())
               .formLogin(form -> form.loginPage("/login")
                                  .defaultSuccessUrl("/").permitAll())
               .logout(logout -> logout.logoutUrl("/logout")
                                .logoutSuccessUrl("/?logout").permitAll());

        return http.build();
    }  // 첫번째 Bean
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();    //암호화 방식
    }

}
