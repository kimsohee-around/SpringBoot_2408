package org.iclass.board.dto;

import lombok.*;
import org.iclass.board.entity.TodoEntity;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
public class TodoDTO {

    private String id;
    private String title;
    private String username;
    private boolean done;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    //1. entity를 dto로 변환하는 메소드. 인자 entity -> setDTO
    public static TodoDTO toDTO(TodoEntity entity){
                        // 메소드이름 of 추천.
        return  TodoDTO.builder()
                .title(entity.getTitle())
                .done(entity.isDone())
                .username(entity.getUsername())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .id(entity.getTodo_id())
                .build();
    }

    //2. dto를 entity로 변환하는 메소드. 리턴 entity  -> toEntity
    //   현재 인스턴스 DTO 값으로 실행합니다.
    public TodoEntity toEntity(){
        return  TodoEntity.builder()
                .todo_id(this.id)
                .title(this.title)
                .done(this.done)
                .username(this.username)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }

}
