package org.iclass.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iclass.board.dao.BookMemberMapper;
import org.iclass.board.dto.BookMemberDTO;
import org.iclass.board.dto.UserAccountDTO;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomUserDetailsService  implements UserDetailsService {
    public final PasswordEncoder passwordEncoder;
    public final BookMemberMapper memberMapper;


    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        //db에서 조회한 결과 dto
        BookMemberDTO dto = memberMapper.selectByUsername(username);
        if (dto == null) {
            throw new UsernameNotFoundException("로그인 정보가 올바르지 않습니다.");
        }

        UserDetails userDetails = UserAccountDTO.builder()
                .username(dto.getEmail())
                .password(dto.getPassword())
                .authorities(new SimpleGrantedAuthority(dto.getRoles()))
                .build();

        log.info(">>>>>>> UserDetails: {}", userDetails);
        // userDetails 에 저장된 database 비밀번호 값과 사용자가 입력한 비밀번호가 같으면
        // 사용자 인증 정보 UserDetails 타입 객체를 리턴합니다.
        return userDetails;
    }
}
/*
  //db 에서 조회하기 전 테스트 코드
        // 임의의 User 를 리턴해 보겠습니다. 나중에 db에서 조회 합니다.
        // 실행 아무거나 입력해도 동일한 값을 리턴합니다.
        UserDetails userDetails = User.builder()
                .username("kim@iclass.org")
                .password(passwordEncoder.encode("1111"))
                .authorities("ROLE_USER")
                .authorities(new SimpleGrantedAuthority("ROLE_USER"))
                .build();
        //SimpleGrantedAuthority  : 권한 객체 기본 유형

 */