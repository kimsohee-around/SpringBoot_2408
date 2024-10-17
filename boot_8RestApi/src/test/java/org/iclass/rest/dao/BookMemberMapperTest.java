package org.iclass.rest.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookMemberMapperTest {

    @Autowired
    private BookMemberMapper bookMemberMapper;

    @DisplayName("회원기능의 dao 가 빈 생성되었습니다.")
    @Test
    void mapper(){
        assertNotNull(bookMemberMapper);
    }
}