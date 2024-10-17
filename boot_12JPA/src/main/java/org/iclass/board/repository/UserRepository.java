package org.iclass.board.repository;

import org.iclass.board.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository
        extends JpaRepository<UserEntity, Long> {

    //여기에 아무것도 작성 안합니다. -> 기본적인 CRUD 는 실행가능
    //조인 등 복잡한 SQL -> 메소드 정의하겠습니다.

}
/*  JpaRepository 는 CrudJpaRepository를 상속 받은 인터페이스 입니다.
    CrudJpaRepository 의 메소드는 별도로 구현하지 않고 바로 실행할 수
    있습니다.
    //insert, update
    <S extends T> S save(S entity);
    <S extends T> Iterable<S> saveAll(Iterable<S> entities);
    // select , 메소드 인자가 ID 타입은 PK 값을 전달합니다.
    //          메소드 리턴  T,S 타입은 Entity 타입입니다.
    Optional<T> findById(ID id);
    boolean existsById(ID id);
    Iterable<T> findAll();
        ㄴ 자식 인터페이스 List
    Iterable<T> findAllById(Iterable<ID> ids);
    long count();
    // delete
    void deleteById(ID id);
    void delete(T entity);
    void deleteAllById(Iterable<? extends ID> ids);
    void deleteAll(Iterable<? extends T> entities);
    void deleteAll();
 */