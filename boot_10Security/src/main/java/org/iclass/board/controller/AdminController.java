package org.iclass.board.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {
    //ROLE_ADMIN 역할 권한을 갖고 있는 사용자만 접근할 수 있다.
    // Config 에서도 가능합니다.
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/list")
    public String list() {
        return "admin/list";
    }
}
