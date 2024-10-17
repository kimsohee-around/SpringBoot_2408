package org.iclass.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iclass.board.dto.TodoDTO;
import org.iclass.board.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api")
public class ApiTodoController {

    private final TodoService todoService;

    @GetMapping("/todo")
    public ResponseEntity<?> findAll(){

        List<TodoDTO> list = todoService.list();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/todo/{username}")
    public ResponseEntity<?> findByUser( @PathVariable String username){

        List<TodoDTO> list = todoService.userList(username);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/todo")
    public ResponseEntity<?> save(@RequestBody TodoDTO todoDTO){
                        //json 문자열 요청 값을 TodoDTO타입으로 변환
        TodoDTO result = todoService.write(todoDTO);
        return ResponseEntity.ok(result);
    }



}
