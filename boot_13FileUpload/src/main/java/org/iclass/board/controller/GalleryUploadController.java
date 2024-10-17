package org.iclass.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iclass.board.dto.GalleryDTO;
import org.iclass.board.repository.GalleryUploadRepository;
import org.iclass.board.service.GalleryUploadService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.io.File;
import java.io.IOException;

@RequiredArgsConstructor
@Controller
@Slf4j
public class GalleryUploadController {

    private final GalleryUploadService uploadService;

    @GetMapping("/gallery")
    public String gallery(Model model) {
        GalleryDTO dto =uploadService.one(1);
        model.addAttribute("dto", dto);
        return "gallery";
    }

    @PostMapping("/gallery")
    public String upload(GalleryDTO dto) throws IOException {
        MultipartFile file = dto.getFile();
        log.info("파일명:{}",file.getOriginalFilename());
        log.info("파일크기:{}",file.getSize());

        uploadService.uploadGallery(dto);

        return "redirect:/gallery";
    }

    @GetMapping("/galleries")
    public String galleries(Model model) {
       //전체 리스트 가져오기
        List<GalleryDTO> list = uploadService.list();
        model.addAttribute("list", list);
        return "galleries";
    }

    @PostMapping("/galleries")
    public String uploadMany(GalleryDTO dto) throws IOException {
        //하나의 요청으로 여러개의 파일을 업로드
        uploadService.uploadManyFile(dto);
        return "redirect:/galleries";
    }
}
