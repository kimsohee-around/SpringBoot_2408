package org.iclass.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iclass.board.dto.BookMemberDTO;
import org.iclass.board.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
@Slf4j
public class MemberController {

    private final MemberService service;

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String save(BookMemberDTO dto){
        log.info(">>>>> signup dto :{}",dto);
        service.register(dto);
        return "redirect:/";
    }
}
