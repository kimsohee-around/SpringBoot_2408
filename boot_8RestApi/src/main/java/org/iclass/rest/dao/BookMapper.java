package org.iclass.rest.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.iclass.rest.dto.BookDTO;
import org.iclass.rest.dto.BookMemberDTO;
import org.iclass.rest.dto.PageReqDTO;

@Mapper
public interface BookMapper {
	
	int insert(BookDTO dto);
	int delete(String bcode);
	//검색 : PageReqDTO 에 검색할 컬럼 배열 types, 검색단어 keyword를 저장합니다.
	BookDTO selectByBcode(String bcode);
	List<BookDTO> searchList(PageReqDTO dto);
	
}




