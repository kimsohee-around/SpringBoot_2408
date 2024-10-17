package org.iclass.board.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.Modifying;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class TodoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    // UUID 는 자바클래스. 임의의 문자열을 만들어 줍니다.
    private String todo_id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String username;
    private boolean done;
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;



}
