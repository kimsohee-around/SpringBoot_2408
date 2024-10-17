package org.iclass.rest.dao;


import org.apache.ibatis.annotations.Mapper;
import org.iclass.rest.dto.BookDTO;
import org.iclass.rest.dto.BookMemberDTO;

import java.util.List;

@Mapper
public interface BookMemberMapper {
	
	int insert(BookMemberDTO dto);
	int update(BookMemberDTO dto);
	int delete(int idx);
	BookMemberDTO selectByIdx(int idx);
	List<BookMemberDTO> selectAll();
	
}
