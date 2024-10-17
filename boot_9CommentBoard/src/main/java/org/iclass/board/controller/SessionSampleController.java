package org.iclass.board.controller;

import lombok.extern.slf4j.Slf4j;
import org.iclass.board.dto.BookMemberDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

// @SessionAttributes, @SessionAttribute 동작을
// 확인하는 컨트롤러 클래스
// "url","userDTO" 이름의 애트리뷰트는 세션 스콥입니다.- 모든 클래스에서 사용.

@Controller
@Slf4j
@SessionAttributes({"url","userDTO"})
public class SessionSampleController {

        @GetMapping("/sample")
        public String sample(
          @SessionAttribute(name = "userDTO",required = false)
          @ModelAttribute("memberDTO")
          BookMemberDTO dto,
          Model model) {
            // SessionAttribute 와 ModelAttribute는 목적이 다르므로 애트리뷰트 이름을
            // 동일하게 하면 충돌 오류 생깁니다.
            log.info("============userDTO: {}",dto);
            // 지금까지는 model 에 요청 스콥으로 값을 저장했습니다.
            // userDTO 이름은 세션 애트리뷰트로 정해져 있습니다.
            // 아래 명령어는 세션 스콥에 값을 저장합니다.
            model.addAttribute("userDTO"
                    ,new BookMemberDTO(0,"kim","kim@naver.com",null,
                            null));
            return "sample";
        }

        @GetMapping("/sample2")
        public String sample2(
                @SessionAttribute(name = "url",required = false) String url,
                Model model
                ){
            log.info("============url: {}",url);
            model.addAttribute("url","https://www.google.com");
            return "sample";
        }




}
