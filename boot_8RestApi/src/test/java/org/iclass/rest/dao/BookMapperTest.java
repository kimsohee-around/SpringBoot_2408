package org.iclass.rest.dao;

import lombok.extern.slf4j.Slf4j;
import org.iclass.rest.dto.BookDTO;
import org.iclass.rest.dto.PageReqDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class BookMapperTest {
    @Autowired BookMapper bookMapper;

    @Test
    void books(){
        //페이지번호, 페이지크기,검색컬럼, 검색어=> 기본값으로 실행하기
        // 기본값 => page=1 ,size=10, types=null , keyword=null
        PageReqDTO dto = new PageReqDTO();
        List<BookDTO> list =bookMapper.searchList(dto);
        assertNotNull(list);
    }
}