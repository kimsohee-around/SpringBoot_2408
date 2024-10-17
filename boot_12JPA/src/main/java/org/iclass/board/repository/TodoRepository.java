package org.iclass.board.repository;

import org.iclass.board.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String> {

    List<TodoEntity> findByUsername(String username);
    // select * from todo_entity where username=?
    // TodoEntity 에서 사용한 컬럼명을 이용한 메소드를 추가
    List<TodoEntity> findByUsernameOrderByCreatedAtDesc(String username);
    List<TodoEntity> findByCreatedAtAfter(LocalDateTime someday);
}
