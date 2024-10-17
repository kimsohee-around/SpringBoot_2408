package org.iclass.board.repository;

import lombok.extern.slf4j.Slf4j;
import org.iclass.board.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class UserRepositoryTest {
    
    @Autowired
    private UserRepository userRepository;
    
    @Test
    void save() {
        //기본 CRUD 는 CrudJpaRepository 의 메소드로 실행하기
        //  메소드 인자는 Entity타입
        UserEntity entity = UserEntity.builder()
                .username("himedia")
                .password("12345")
                .role("ROLE_ADMIN")
                .build();
        log.info("1.저장할 user 확인: {}", entity);
        //정상 실행 완료하면 entity 리턴
        entity = userRepository.save(entity);
        
        // 저장한 row 를 select. 자동으로 생성된 id값으로 조회하기
        log.info("2.저장한 User 조회: {}"
                ,userRepository.findById(entity.getId()));
        assertNotNull(entity);
    }


    @Test
    void findById() {
        // pk 컬럼 id로 조회하기: 리턴타입은 Optional 입니다.
        Optional<UserEntity> optional = userRepository.findById(1L);
//        optional.get();
// Optional 타입에서 엔티티를 가져오는 메소드. 단,null 이 아닌지 검사 필수
// 기존 방식은 예외 발생을 막기 위해 if(오브젝트변수 != null) {}  사용 -> 메소드 ifPresent 제공.
        optional.ifPresent( e -> {      //isPresent() 는 if문을 직접 작성
           UserEntity entity = optional.get();
           log.info("조회한 entity: {}", entity);
        });

        assertNotNull(optional);
    }

    @Test
    void updatePassword() {
        //1.update 할 엔티티 1개를 먼저 PK 조회 -> 저장
        //2. 1번에서 조회한 엔티티 중 수정할 필드를 값을 setter 로 저장.
        //3. save 메소드 실행합니다. 인자는 2번의 엔티티

        Optional<UserEntity> optional = userRepository.findById(4L);
        optional.ifPresent( e -> {
            UserEntity entity = optional.get();
            //2.
            entity.setPassword("99999");
            //3.
            entity = userRepository.save(entity);
            log.info("변경된 entity: {}", entity);
        });


    }



}