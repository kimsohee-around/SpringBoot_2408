package org.iclass.board.service;

import org.iclass.board.dto.GalleryDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class SingleFileUploadService {

    public void upload(GalleryDTO dto) throws IOException {
        final String path = "c:\\upload";    //파일서버 역할의 디렉토리
        MultipartFile file = dto.getFile();
        if(file.getSize() !=0){
            File pathFile
                    = new File(path + "\\"
                    + file.getOriginalFilename());
            file.transferTo(pathFile);      // 파일 서버에 저장하기
        }
    }
}
