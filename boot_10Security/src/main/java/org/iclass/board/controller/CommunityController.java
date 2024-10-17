package org.iclass.board.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/community")
public class CommunityController {

    @GetMapping("/list")
    public String list() {
        return "community/list";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/write")
    public String write() {
        return "community/write";
    }

    @GetMapping("/modify")
    public String modify() {
        return "community/modify";
    }
}
