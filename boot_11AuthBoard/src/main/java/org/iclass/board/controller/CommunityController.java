package org.iclass.board.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iclass.board.dto.CommunityDTO;
import org.iclass.board.dto.PageRequestDTO;
import org.iclass.board.dto.PageResponseDTO;
import org.iclass.board.service.CommunityService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/community")
@Slf4j
@RequiredArgsConstructor
public class CommunityController {

        private final CommunityService communityService;
        // 검색기능에 필요한 type 과 keyword , page 파라미터 받는 DTO변경
        @GetMapping("/list")
        public String list(
                @ModelAttribute("requestDTO") PageRequestDTO requestDTO,
               Model model
        ){
            requestDTO.setSize(5);
            PageResponseDTO responseDTO
                    = communityService.getList(requestDTO);
            model.addAttribute("pageList",responseDTO);
//            Map<String, Object> map = communityService.pageSearchList(page);
//            model.addAttribute("list", map.get("list"));
//            model.addAttribute("pageDto", map.get("pageDto"));

            return "community/list";
        }
        //로그인을 하지 않으면 글쓰기 화면에 접근 못함.
        @PreAuthorize("isAuthenticated()")
        @GetMapping("/write")
        public String write(
                Model model,    // valid 오류 출력을 위해 선언
                RedirectAttributes reAttributes
        ){

            // 오류 메시지 출력할 객체를 model 에 저장해서 view 로 전달하기
            model.addAttribute("communityDTO",new CommunityDTO());
            return "community/write";
        }

    @PostMapping("/write")  //글 저장 후 글 목록으로 url 이동
    public String write(@Valid CommunityDTO dto,    //*
                        BindingResult bindingResult,   //*
                        RedirectAttributes reAttr,
                        HttpServletRequest request) {
            //http 요청 객체를 직접 사용하기
        log.info("글쓰기 입력 ip : {} ", dto.getIp());
        log.info("글쓰기 입력 dto : {} {}", dto,request.getRemoteAddr());
        dto.setIp(request.getRemoteAddr());  //요청한 클라이언트 ip 가져오기
        // Valid 검사를 한 결과 DTO의 제약조건 설정에 위배되는 오류가 발생하면
        // BindingResult 객체에 오류 정보를 저장합니다.
        if(bindingResult.hasErrors()) {
            // DTO 에 설정된 필드 검사 오류만 확인
            log.info("binding result : {}", bindingResult.getFieldErrors());
            //잘못된 입력이 있으면 다시 쓰기 화면으로 갑니다.
            return "community/write";
        }else {

            communityService.write(dto);
            reAttr.addFlashAttribute("message", "글이 등록되었습니다.!");
            return "redirect:list";
        }
        //list.html 에 전달되는 애트리뷰트 message
    }

    @GetMapping("/read")
    public String read(int idx,
           @ModelAttribute("requestDTO") PageRequestDTO requestDTO,
           Model model){

            CommunityDTO dto = communityService.read(idx);
            model.addAttribute("dto", dto);
//            model.addAttribute("page", page);
            return "community/read";
    }
//    dto 가 메소드 인자로 넘어온 객체라면 아래와 같이 권한 검사
//    @PreAuthorize("#dto.writer==authentication.name")
    @GetMapping("/modify")  //글수정 화면
    public String modify(int idx,
             @ModelAttribute("requestDTO") PageRequestDTO requestDTO,
             CommunityDTO dto,
             Model model) throws IllegalAccessException {

        dto = communityService.selectByIdx(idx);

        //파라미터로 전달되는 값 중에 글 작성가 없어서
        // idx로 조회한 결과의writer 와 비교합니다.(이 코드는 그대로 유지)
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null &&
                authentication.getPrincipal() instanceof UserDetails) {
            //* 글작성자와 사용자가 다르면 예외 발생
            if(!dto.getWriter().equals(authentication.getName())) {
                throw new IllegalAccessException("잘못된 접근입니다.");
            }
            model.addAttribute("username", authentication.getName());
        }
//        model.addAttribute("communityDTO",new CommunityDTO());
        model.addAttribute("dto", dto);
        return "community/modify";
    }

    @PostMapping("/modify")  //글 수정 저장 후 글 목록(또는 수정)으로 url 이동
    public String modify(@ModelAttribute("requestDTO") PageRequestDTO requestDTO,
                        @Valid @ModelAttribute("dto") CommunityDTO dto,
                        BindingResult bindingResult,
                        RedirectAttributes reAttr) {
        log.info("modify : {}",requestDTO);
        if(bindingResult.hasErrors()) {
            log.info("binding results : {}", bindingResult.getFieldErrors());
            return "community/modify";
            //수정 화면으로 갈 때 , 기존 값들 표시해야 합니다. dto 애트리뷰트 가져가기
        }else
        {
            communityService.modify(dto);
            reAttr.addAttribute("idx", dto.getIdx());
            reAttr.addFlashAttribute("message", "글이 수정되었습니다.");
            return "redirect:read?" + requestDTO.getLink();
        }
    }

// 타임리프의 #은 타임리프 객체, PreAuthorize 안에서 #은 메소드 인자를 지정
// authentication.name 또는 principal.username 모두 가능
//                  ㄴ 시큐리티가 저장한 인증 객체 사용가능
    @PreAuthorize("#writer == authentication.name")
    @PostMapping("/remove")    //글 삭제 후 글 목록으로 url 이동
    public String remove(int idx, String link
            ,String writer
            ,RedirectAttributes reAttr) throws IllegalAccessException {
        log.info("remove : {}",link);

        CommunityDTO dto = communityService.selectByIdx(idx);
//        if(!dto.getWriter().equals(username)) {
//                throw new IllegalAccessException("잘못된 접근입니다.");
//         }
        communityService.remove(idx);
        reAttr.addFlashAttribute("message", "글이 삭제되었습니다.!");

        return "redirect:list?"+link;
    }



}
