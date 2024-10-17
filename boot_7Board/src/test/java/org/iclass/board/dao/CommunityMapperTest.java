package org.iclass.board.dao;

import org.iclass.board.dto.PageRequestDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommunityMapperTest {

    @Autowired
    private CommunityMapper dao;

    @DisplayName("저장된 글의 갯수는 0이 아닙니다.")
    @Test
    void getCount() {
        PageRequestDTO requestDTO = new PageRequestDTO();
        Assertions.assertNotEquals(0, dao.getCount(requestDTO));
    }


}