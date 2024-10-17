package org.iclass.board.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
@Slf4j
@Data
//@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDTO {
    private int page=1;
    private int size;
    //페이지 글목록 시작, 마지막 번호
    private int startNo;
    private int endNo;
    //검색
    private String type;
    private String keyword;
    //동적 SQL에 전달할 배열
    private String[] types;

    public PageRequestDTO() {
        log.info("============= no args ========");

    }

    public void setTypes(){
//        startNo = (page - 1) * size+1;
//        endNo = startNo + size - 1;
        if(this.type!=null && !this.type.isEmpty() && !type.equals("a")){
            this.types = this.type.split("");
            //"twc" 를 ["t","w","c"] 배열로 쪼개기
        }
    }

    // 검색 기능이 추가되면 url 이 매우 길어져서
    // 목록 버튼에 파라미터가 많아집니다. 파라미터를 하나의 문자열로
    // 저장하려고 합니다.
    private String link;
    public String getLink(){
        if (this.link==null || this.link.isEmpty()){
            StringBuilder builder = new StringBuilder();
            builder.append("page=").append(this.page);
            //검색 값이 있을 때만
            if(type !=null && !type.isEmpty() && keyword != null) {
                builder.append("&type=").append(type);
                builder.append("&keyword=")
                        .append(URLEncoder.encode(keyword, StandardCharsets.UTF_8));
            }

            this.link = builder.toString();
        }
        return this.link;
    }
}
