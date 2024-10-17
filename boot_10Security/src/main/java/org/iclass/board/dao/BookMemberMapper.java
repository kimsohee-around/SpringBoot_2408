package org.iclass.board.dao;


import org.apache.ibatis.annotations.Mapper;
import org.iclass.board.dto.BookMemberDTO;

import java.util.List;

@Mapper
public interface BookMemberMapper {
	//스프링 시큐리티 login 실행 -> CustomerUserDetailsService
	//              loadUserByUsername 메소드에서 실행합니다.
	BookMemberDTO selectByUsername(String username);

	int insert(BookMemberDTO dto);
	int update(BookMemberDTO dto);
	int delete(int idx);
	BookMemberDTO selectByIdx(int idx);
	List<BookMemberDTO> selectAll();
	
}
