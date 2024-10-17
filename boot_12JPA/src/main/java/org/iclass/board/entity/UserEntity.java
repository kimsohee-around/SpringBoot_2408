package org.iclass.board.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
// 이 클래스는 db와 직접 연결되는 엔티티
//   -변수들을 테이블 컬럼으로 만듭니다.
//   -클래스 이름으로 테이블을 생성합니다.
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
//   - 테이블이름변경, 제약조건 등의 설정을 합니다.
public class UserEntity {

    @Id      // PK 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;            //기존방식은 시퀀스

    @Column(nullable = false)
    private String username;   //로그인 계정(이메일, userid 등). 제약 조건 unique
    private String password;
    private String role;

    private String nickname;
    private String picture;    //프로필이미지 파일명

    @CreatedDate
    private LocalDate join_date;
}
