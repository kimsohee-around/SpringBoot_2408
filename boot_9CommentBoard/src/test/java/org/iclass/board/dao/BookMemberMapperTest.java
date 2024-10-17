package org.iclass.board.dao;

import lombok.extern.slf4j.Slf4j;
import org.iclass.board.dto.BookMemberDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class BookMemberMapperTest {

    @Autowired
    private BookMemberMapper memberMapper;

    @Test
    void selectByUsername() {
        String username = "honey@naver.com";
        String password = "123456"; //사용자 입력
        BookMemberDTO member
                = memberMapper.selectByUsername(username);
        if(member != null) {   //1122
            if(member.getPassword().equals(password)) {
                log.info("로그인 성공 했습니다.");
            } else{
                log.info("로그인 실패 했습니다.");
            }
        }else{
            fail("사용자가 없습니다.");
        }
        assertNotNull(memberMapper);
    }

}