package org.iclass.board.repository;

import lombok.extern.slf4j.Slf4j;
import org.iclass.board.entity.TodoEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    void saveDummies() {
        String[] names = {"himedia","iclass"};
        List<TodoEntity> list = new ArrayList<>();
        //for(int i=1;i<10;i++)
        IntStream.range(1, 10).forEach(i -> {
           TodoEntity entity = TodoEntity.builder()
                   .title("spring" + i)
                   .username(names[i%2])
                   .done(i%2 == 0)
                   .build();
            list.add(entity);
        });
        todoRepository.saveAll(list);
        assertEquals(9, todoRepository.count());
        //count()메소드: select count() from 테이블;
    }

    @Test
    void deleteDummy() {
        todoRepository.deleteAll();
        assertEquals(0,todoRepository.count());
    }

    @Test
    void findAll(){
        List<TodoEntity> list = todoRepository.findAll();
        log.info("All Todos : {} ",list);
        assertNotNull(list);
    }
}