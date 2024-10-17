package org.iclass.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iclass.board.dao.BookMemberMapper;
import org.iclass.board.dto.BookMemberDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class MemberService {
    private final BookMemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    public void register(BookMemberDTO dto) {
        //기본 권한 설정하기
        //비밀번호 암호화
        dto.setRoles("ROLE_USER");
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        memberMapper.insert(dto);
        log.info("회원 가입 user idx: {}", dto.getMem_idx());
    }
}
