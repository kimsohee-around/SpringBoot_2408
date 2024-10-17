package org.iclass.board.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class LoginController {
    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // 로그인 PostMapping 은 시큐리티가 처리합니다.

}
