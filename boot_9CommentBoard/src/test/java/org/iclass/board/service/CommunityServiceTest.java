package org.iclass.board.service;

import lombok.extern.slf4j.Slf4j;
import org.iclass.board.dto.PageRequestDTO;
import org.iclass.board.dto.PageResponseDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class CommunityServiceTest {

//  private static final Logger log = LoggerFactory.getLogger(CommunityServiceTest.class);
    @Autowired
    private CommunityService communityService;

    @Test
    @DisplayName("service 자동주입 성공")
    void bean(){
        assertNotNull(communityService);
    }

    @Test
    @DisplayName("글 목록 가져오기-검색안함")
    @Disabled
    void list(){
        PageRequestDTO requestDTO
                    = new PageRequestDTO();
        requestDTO.setPage(2);
        requestDTO.setSize(5);
        PageResponseDTO responseDTO
                    = communityService.getList(requestDTO);
        log.info("responseDTO : {}",responseDTO.toString());
    }

    @Test
    @DisplayName("글 목록 가져오기-검색")
    void search(){
        PageRequestDTO requestDTO
                = new PageRequestDTO();
        requestDTO.setPage(2);
        requestDTO.setSize(5);
        requestDTO.setType("tc");
        requestDTO.setKeyword("우리");
        PageResponseDTO responseDTO
                = communityService.getList(requestDTO);
        log.info("Search responseDTO : {}",responseDTO.toString());
    }


}