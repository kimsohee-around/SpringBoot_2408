package org.iclass.board.service;

import lombok.extern.slf4j.Slf4j;
import org.iclass.board.dto.CommentDTO;
import org.iclass.board.dto.CommunityDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommunityService communityService;


    @Test
    void write() {
        CommentDTO dto = new CommentDTO(0,169,
                "ksh@gmail.com","댓글 테스트",null,"127.0.0.1",0);
        //위 dto 의 mref 로 현재 댓글 갯수 구해보기
        CommunityDTO comdto
                = communityService.selectByIdx(169);
        int count = comdto.getCommentCount();
        //서비스 실행
        int result =commentService.write(dto);
        // 변경된 댓글 갯수
        comdto= communityService.selectByIdx(169);
        int count2 = comdto.getCommentCount();
        assertEquals(count+1,count2);
        assertEquals(1,result);

    }

    @Test
    void remove() {

    }
}