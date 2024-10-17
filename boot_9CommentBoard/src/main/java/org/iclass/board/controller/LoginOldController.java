package org.iclass.board.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iclass.board.dao.BookMemberMapper;
import org.iclass.board.dto.BookMemberDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
// 전통적인 jsp 의 HttpSession 으로 구현한 컨트롤러
@RequiredArgsConstructor
@Slf4j
//@Controller
public class LoginOldController {
    private final BookMemberMapper memberMapper;

//    @GetMapping("/login")
    public String login() {
        return "login";
    }

//    @PostMapping("/login")
    public String loginPost(BookMemberDTO dto
                    , RedirectAttributes reAttributes
                    , HttpSession session) {
        //dto 에 form 입력 email,password 가 자동으로 저장
        BookMemberDTO resultDTO =
                memberMapper.selectByUsername(dto.getEmail());
        if(resultDTO.getPassword().equals(dto.getPassword())) {

            session.setAttribute("username", dto.getEmail());
            return "redirect:/";
        }else {
            reAttributes.addFlashAttribute("incorrect", "y");
            return "redirect:/login";
        }
    }
    
//    @GetMapping("/logout")
    public String logout(HttpSession session) {
        //JSESSIONID 값이 그대로 유지. 로그아웃에서는 모든 세션 애트리뷰트값을 삭제해야 합니다.
        //session.removeAttribute("username");

        session.invalidate();    //JSESSIONID 무효화(세션 만료)
        return "redirect:/";
    }
    
    
    
}
