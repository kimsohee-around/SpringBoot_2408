package org.iclass.board.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iclass.board.dao.BookMemberMapper;
import org.iclass.board.dto.BookMemberDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Slf4j
@Controller
@SessionAttributes({"username","referer"})
public class LoginController {
    private final BookMemberMapper memberMapper;

    @GetMapping("/login")
    public String login(HttpServletRequest request, Model model) {
        // 요청 헤더에 저장된 "Referer" 값을 가져오기 :/login 을 요청한 url 이 저장됨.
        String referer = request.getHeader("Referer");
        if(referer != null) {
            model.addAttribute("referer", referer);
            log.info("======> referer: {}" , referer);
            //referer 는 어디에서 쓰면 될까요?
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(BookMemberDTO dto
                    ,@SessionAttribute("referer") String referer
                    , RedirectAttributes reAttributes
                    , Model model  //*
                    ) {
        //dto 에 form 입력 email,password 가 자동으로 저장
        BookMemberDTO resultDTO =
                memberMapper.selectByUsername(dto.getEmail());
        if(resultDTO.getPassword().equals(dto.getPassword())) {
            //*
            model.addAttribute("username", dto.getEmail());
            return "redirect:"+referer;
        }else {
            reAttributes.addFlashAttribute("incorrect", "y");
            return "redirect:/login";
        }
    }
    
    @GetMapping("/logout")    //@SessionAttributes 어노테이션 사용할 때
    public String logout(SessionStatus sessionStatus) {
        //JSESSIONID 값이 그대로 유지. 로그아웃에서는 모든 세션 애트리뷰트값을 삭제해야 합니다.
        // HttpSession 객체로 저장한 데이터와는 저장소가 다릅니다.(주의)
        sessionStatus.setComplete();
        return "redirect:/";
    }

}
