package org.iclass.board.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iclass.board.dto.CommunityDTO;
import org.iclass.board.dto.PageRequestDTO;
import org.iclass.board.dto.PageResponseDTO;
import org.iclass.board.service.CommunityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

        @GetMapping("/write")
        public String write(){
            return "community/write";
        }

    @PostMapping("/write")  //글 저장 후 글 목록으로 url 이동
    public String write(CommunityDTO dto, RedirectAttributes reAttr,
                        HttpServletRequest request) {
            //http 요청 객체를 직접 사용하기
        log.info("글쓰기 입력 dto : {} {}", dto,request.getRemoteAddr());
        dto.setIp(request.getRemoteAddr());  //요청한 클라이언트 ip 가져오기
        communityService.write(dto);
        reAttr.addFlashAttribute("message", "글이 등록되었습니다.!");
        return "redirect:list";
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

    @GetMapping("/modify")  //글수정 화면
    public String modify(int idx,
             @ModelAttribute("requestDTO") PageRequestDTO requestDTO,
              Model model) {
        CommunityDTO dto = communityService.selectByIdx(idx);
        model.addAttribute("dto", dto);
        return "community/modify";
    }

    @PostMapping("/modify")  //글 수정 저장 후 글 목록(또는 수정)으로 url 이동
    public String modify(PageRequestDTO requestDTO,
                        CommunityDTO dto,
                        RedirectAttributes reAttr) {
        log.info("modify : {}",requestDTO);
        communityService.modify(dto);
        reAttr.addAttribute("idx", dto.getIdx());
        reAttr.addFlashAttribute("message", "글이 수정되었습니다.");
        return "redirect:read?"+requestDTO.getLink();
    }

    @PostMapping("/remove")    //글 삭제 후 글 목록으로 url 이동
    public String remove(int idx, String link
            ,RedirectAttributes reAttr) {
        log.info("remove : {}",link);
        communityService.remove(idx);
//        reAttr.addAttribute("link", link);
        reAttr.addFlashAttribute("message", "글이 삭제되었습니다.!");
        return "redirect:list?"+link;
    }



}
