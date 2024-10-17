package org.iclass.rest.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.iclass.rest.dao.BookMemberMapper;
import org.iclass.rest.dto.BookMemberDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class APIMemberController {

    private final BookMemberMapper memberMapper;

    @PostMapping("/members")
    public ResponseEntity<?>
        create(@RequestBody BookMemberDTO dto) {

        try {
            memberMapper.insert(dto);
            log.info("새로 저장된 dto mem_idx : {}", dto.getMem_idx());
            return ResponseEntity.ok()
                    .body(memberMapper.selectByIdx(dto.getMem_idx()));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/members")
    public ResponseEntity<Map<String,Object>> update(@RequestBody BookMemberDTO dto) {
        int i = memberMapper.update(dto);
        Map<String, Object> map = new HashMap<>();
        map.put("rows",i);
        map.put("data", memberMapper.selectByIdx(dto.getMem_idx()));
        return ResponseEntity.ok(map);
    }

    @GetMapping("/members")
    public ResponseEntity<List<BookMemberDTO>> members(){
        //ResponseEntity<T> 에서 T는 body 에 저장되는 데이터 타입
        List<BookMemberDTO> list = memberMapper.selectAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/members/{memIdx}")
    public ResponseEntity<BookMemberDTO>
            member(@PathVariable int memIdx){
        BookMemberDTO dto = memberMapper.selectByIdx(memIdx);
        if (dto == null){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok().body(dto);
        }

    }

    @DeleteMapping("/members/{memIdx}")
    public ResponseEntity<BookMemberDTO>
    delete(@PathVariable int memIdx){
        int i = memberMapper.delete(memIdx);
        if (i==0){
            return ResponseEntity.badRequest().build();
        }else {
            return ResponseEntity.ok().build();
        }

    }
}
