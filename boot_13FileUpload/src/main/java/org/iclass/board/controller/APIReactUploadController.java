package org.iclass.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iclass.board.dto.GalleryDTO;
import org.iclass.board.service.SingleFileUploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@Slf4j
public class APIReactUploadController {

    private final SingleFileUploadService uploadService;

    @PostMapping("/reactApp")
    public ResponseEntity<?> upload(GalleryDTO dto) throws IOException {
        //GalleryDTO 에 MultipartFile 타입이 있어서 새로 dto 정의하지 않고
        //   활용합니다. 여기서는 MultipartFile file 하나만 사용합니다.
        MultipartFile file = dto.getFile();
        log.info("파일명:{}",file.getOriginalFilename());
        log.info("파일크기:{}",file.getSize());

        uploadService.upload(dto);

        return ResponseEntity
                .ok(Map.of("message",
                        "file successfully uploaded!"));
    }

}
