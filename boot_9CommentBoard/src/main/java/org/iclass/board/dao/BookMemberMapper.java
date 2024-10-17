package org.iclass.board.dao;


import org.apache.ibatis.annotations.Mapper;
import org.iclass.board.dto.BookMemberDTO;

import java.util.List;

@Mapper
public interface BookMemberMapper {
	
	//로그인을 위한 조회 추가
	BookMemberDTO selectByUsername(String email);
	// 기존 SQL 실행 메소드
	int insert(BookMemberDTO dto);
	int update(BookMemberDTO dto);
	int delete(int idx);
	BookMemberDTO selectByIdx(int idx);
	List<BookMemberDTO> selectAll();
	
}
