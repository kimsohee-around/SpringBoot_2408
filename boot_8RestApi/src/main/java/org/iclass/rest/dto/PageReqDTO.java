package org.iclass.rest.dto;

import java.time.LocalDate;


import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
//@NoArgsConstructor
@Data
@AllArgsConstructor
@Slf4j
public class PageReqDTO {
	@Builder.Default
	private int page =1;
	@Builder.Default
	private int size= 10;      //size 는 한 개 페이지 글 갯수
	private int start;		//페이지 시작 글번호 rownum
	private int end;		//페이지 마지막 글번호 rownum

	//검색 파라미터
	private String[] types;
	//검색할 컬럼 지정. 여러개 컬럼 검색가능하도록 배열에 저장합니다.
	private String keyword;	 // 검색할 단어.
	private String type;

	public PageReqDTO(){
		log.info("--- 기본생성자 PageReqDTO ---");
	}

	public void setTypes(){
		if(type !=null && !type.isEmpty()){
			types = type.split("");
		}
	}

	
}
