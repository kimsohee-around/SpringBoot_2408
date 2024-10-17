package org.iclass.board.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UploadRequestDTO {

    private long id;
    private String picture;
    private String nickname;
    private MultipartFile file;
}
