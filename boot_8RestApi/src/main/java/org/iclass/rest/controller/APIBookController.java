package org.iclass.rest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iclass.rest.dao.BookMapper;
import org.iclass.rest.dto.BookDTO;
import org.iclass.rest.dto.PageReqDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class APIBookController {

    private final BookMapper bookMapper;

    @PostMapping("/books")
    public ResponseEntity<Object>
        createBook(@RequestBody BookDTO bookDTO) {
        try{
            int i = bookMapper.insert(bookDTO);
            return ResponseEntity
                    .ok()
                    .body(bookMapper.selectByBcode(bookDTO.getBcode()));
        }catch (Exception e){
            //실제 구현된 UI 에서는 사용자가 입력한 값을 검증(unique)하는
            // 비동기 요청을 보내고 사용 가능한지 응답을 받도록 합니다.
            Map<String,String> map = new HashMap<>();
            map.put("error",e.getMessage());
            return ResponseEntity
                    .badRequest().body(map);
        }
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookDTO>> getAllBooks() {

        PageReqDTO pageDTO =new PageReqDTO();
        List<BookDTO> list = bookMapper.searchList(pageDTO);
//        return ResponseEntity.ok().body(list);
        return ResponseEntity.ok(list);
    }
    //검색기능이 있다면 /books 에서 구현하도록 합니다.
    //단, 학습하면서 별도로 테스트 합니다.
    @GetMapping("/books/search")
    public ResponseEntity<List<BookDTO>>
        search(PageReqDTO pageDTO) {
        //web mvc 의 모델 기능은 그대로 사용할 수 있습니다.
        //사용자가 보낸 파라미터를 pageDTO 가 저장합니다.
        log.info("검색 파라미터 page dto : {}", pageDTO);
        pageDTO.setTypes();
        List<BookDTO> list = bookMapper.searchList(pageDTO);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/books/{bcode}")
    public ResponseEntity<BookDTO>
            getBook(@PathVariable String bcode) {
   //{bcode} 와 변수이름 bcode 같으므로 @PathVariable 안의 속성생략
        BookDTO bookDTO = bookMapper.selectByBcode(bcode);
        if (bookDTO == null) {
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok().body(bookDTO);
        }
    }


}
