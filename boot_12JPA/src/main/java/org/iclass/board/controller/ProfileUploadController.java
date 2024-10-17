package org.iclass.board.controller;

import org.iclass.board.dto.UploadRequestDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ProfileUploadController {


    @GetMapping("/profile")
    public String profile() {

        return "profile";
    }


    @PostMapping("/upload")
    public String upload(UploadRequestDTO dto){
        MultipartFile file = dto.getFile();
        String fileName = file.getOriginalFilename();
        String contentType = file.getContentType();
        String originalFilename = file.getOriginalFilename();
        long size = file.getSize();

        return "redirect:/profile";
    }
}
