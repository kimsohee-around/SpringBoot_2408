package org.iclass.board.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class LoginController {


    @GetMapping("/")
    public String index(Model model) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            model.addAttribute("username", authentication.getName());
        }

        return "index";
    }

    @GetMapping("/login")
    public String login(String error, Model model) {
//        log.info("error:{}",error);
        if(error != null   && error.isEmpty()) {
            model.addAttribute("error",
                    "계정정보가 일치하지 않습니다.");
            log.info("error:{}",error);
        }
        return "login";
    }

    // 로그인 PostMapping 은 시큐리티가 처리합니다.
    // 단, 처리결과로 잘못된 계정/비밀번호 에 대한 결과를
    //     http://localhost:8089/login?error   : 파라미터 존재. 값은 비어있음.
    // 정상 url : http://localhost:8089/login   : 파라미터 없음. null
    // 파라미터가 있으면 model 로 애트리뷰트값 전달.
    //                 ㄴ html 은 있을 때만 출력합니다.

}
