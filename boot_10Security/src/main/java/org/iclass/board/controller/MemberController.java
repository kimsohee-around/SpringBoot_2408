package org.iclass.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iclass.board.dto.BookMemberDTO;
import org.iclass.board.service.MemberService;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
@Slf4j
public class MemberController {

    private final MemberService service;

//    @PostAuthorize("")   // 핸들러 메소드 실행 후에 권한 검사
    @PreAuthorize("isAnonymous()")
    @GetMapping("/signup")
    public String signup() {
        return "signup";   // signup.html 웹페이지를 만들어서 응답
    }

    @PostMapping("/signup")
    public String save(BookMemberDTO dto){
        log.info(">>>>> signup dto :{}",dto);
        service.register(dto);
        return "redirect:/";
    }
}
