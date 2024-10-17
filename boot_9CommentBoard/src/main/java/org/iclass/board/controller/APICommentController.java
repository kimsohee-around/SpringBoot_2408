package org.iclass.board.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iclass.board.dto.CommentDTO;
import org.iclass.board.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class APICommentController {

    private final CommentService commentService;

    @GetMapping("/comments/{mref}")
    public ResponseEntity<?> getComments(
            @SessionAttribute(name = "username", required = false) String username,
            @PathVariable int mref) {
        List<CommentDTO> list = commentService.getComments(mref);
        log.info("=============> username : {}",username);
        return ResponseEntity.ok(list);
    }

    /*
    {
      "mref": 169,
      "writer": "sana@gmail.com",
      "content": "오늘도 화이팅!!"
    }  => json 값이 요청의 body => 자바 객체로 변환.(역직렬화)
       => RequestBody 어노테이션으로 매핑.
     */
    @PostMapping("/comments")
    public ResponseEntity<?> save(
            @Valid @RequestBody CommentDTO commentDTO
//            ,BindingResult bindingResult
    ){
        int idx = commentService.write(commentDTO);
        Map<String, Integer> map = Map.of("idx", idx);
        return ResponseEntity.ok(map);

        /*
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors()
                    .forEach(error -> log.error(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body("Invalid data");
        }else {

            int idx = commentService.write(commentDTO);
            Map<String, Integer> map = Map.of("idx", idx);
            return ResponseEntity.ok(map);
        }

        */
    }
    
    @DeleteMapping("/comments/{idx}")
    public ResponseEntity<?> delete(
            @SessionAttribute(name = "username") String username,
            @PathVariable int idx) {
        //idx 값으로 mref 를 알아내야 합니다.//selectByIdx 추가
        try{
            //로그인 사용자와 글작성자 일치하는지 확인함.
            // 이 코드 추가되면 talend 에서 테스트 할 수 없습니다.
            CommentDTO dto = commentService.selectByIdx(idx);
            if(!dto.getWriter().equals(username)) {
                throw new IllegalAccessException("잘못된 접근입니다.");
            }
            int mref = commentService.remove(idx);
            Map<String, Integer> map = Map.of("mref", mref);
            return ResponseEntity.ok(map);
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

}
