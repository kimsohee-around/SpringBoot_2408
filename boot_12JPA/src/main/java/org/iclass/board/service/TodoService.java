package org.iclass.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iclass.board.dto.TodoDTO;
import org.iclass.board.entity.TodoEntity;
import org.iclass.board.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class TodoService {
    // db sql 실행하기
    private final TodoRepository todoRepository;

    public List<TodoDTO> list() {
        List<TodoEntity> list =todoRepository.findAll();
        // list를 TodoDTO 타입 List 로 변환하기
//        List<TodoDTO> resultDTO = new ArrayList<>();
//        for(TodoEntity entity : list){
//            resultDTO.add(TodoDTO.toDTO(entity));
//        }
        //java 8 부터 사용하는 stream()으로 변환
        List<TodoDTO> resultDTO = list.stream()
               .map(TodoDTO::toDTO)
               .collect(Collectors.toList());

        return resultDTO;
    }

    public TodoDTO write(TodoDTO todoDTO) {

        // todoDTO 를 TodoEntity 타입으로 변환하기
        TodoEntity todoEntity = todoDTO.toEntity();
        //save 메소드는 insert 를 실행하고 , insert 된 엔티티를 리턴합니다.
        //todoEntity 에는 자동생성된 문자열 필드값 id가 저장됩니다.
        todoEntity = todoRepository.save(todoEntity);
        
        //컨트롤러에는 DTO 타입으로 변환하여 리턴
        todoDTO =   TodoDTO.toDTO(todoEntity);
        // id, 날짜 2개 값이 새로 만들어진 것이 반영
        return todoDTO;
    }

    public List<TodoDTO> userList(String username) {
        List<TodoEntity> list = todoRepository.findByUsernameOrderByCreatedAtDesc(username);
        log.info(">>>>>>>list>>>>> {}",list);
        return list.stream().map(TodoDTO::toDTO).collect(Collectors.toList());
    }
}
