package org.iclass.board.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Slf4j
@Controller
public class HomeController {

    @GetMapping({"/","/home"})
    public String home(Model model) {
        //인증 정보를 저장하는 객체: 인증정보는 SecurityContextHolder 가 관리합니다.
        Authentication authentication=
                SecurityContextHolder.getContext().getAuthentication();

        // 로그인(인증) 이 안된 상태면
        // authentication.getPrincipal() 는 anonymousUser (String)
        // authentication 객체는 principal를 포함하며
        //                      요청 사용자 ip, 세션ID,... 정보 저장.
        log.info("사용자 인증 값 : {}", authentication);
        if(authentication!=null && authentication.getPrincipal() instanceof UserDetails) {
            model.addAttribute("username", authentication.getName());
        }

        return "index";
    }
}
