package org.iclass.board.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    private int idx;
    private int mref;

    @Email
    private String writer;
    @Size(min = 4, message = "글 내용은 4글자 이상입니다.")
    private String content;
    //JsonFormat 은 자바객체를 json 으로 변환(직렬화)할때 적용할 패턴.
    // dto 출력할 때에는 적용이 안됩니다
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;
    private String ip;
    private int heart;
}
