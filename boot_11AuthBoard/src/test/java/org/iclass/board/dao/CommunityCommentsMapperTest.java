package org.iclass.board.dao;

import lombok.extern.slf4j.Slf4j;
import org.iclass.board.dto.CommentDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class CommunityCommentsMapperTest {

    @Autowired
    private CommunityCommentsMapper commentsMapper;

     @Test
    void plusCommentCount() {
        int result
        = commentsMapper.plusCommentCount(107);
        assertEquals(1, result);
    }

    @Test
    void insert() {
        CommentDTO dto = new CommentDTO(0,200,
                "ksh@gmail.com","댓글 테스트",null,"127.0.0.1",0);
        int result = commentsMapper.insert(dto);
        log.info("result: {}", dto);
        assertEquals(1, result);
        assertNotEquals(0,dto.getIdx());   //selectKey 로 현재 시퀀스 값을 가져옴.
    }

    @Test
    void getCommentList() {
        List<CommentDTO> comments = commentsMapper.getCommentList(184);
        log.info("comments: {}", comments);
        assertNotNull(comments);
     }

     @Test
    void selectMref(){
         int mref = commentsMapper.selectMrefByIdx(22);
         log.info("mref: {}", mref);
         assertNotEquals(0,mref);
     }
}