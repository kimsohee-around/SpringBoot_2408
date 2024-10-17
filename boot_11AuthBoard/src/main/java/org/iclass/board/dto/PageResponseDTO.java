package org.iclass.board.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
//@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class PageResponseDTO {
    private int totalPages;     //전체 페이지수(검색 결과 페이지수)
    private int totalCount;     //전체 글의 개수(검색 결과 글 개수)

    //페이지 목록의 시작,마지막 페이지 번호(검색 결과 페이지 목록)
    private int startPage;
    private int endPage;

    //해당 startNo, endNo 계산된 값으로 조회된 글목록도 저장합니다.
    private List<CommunityDTO> list;


}
