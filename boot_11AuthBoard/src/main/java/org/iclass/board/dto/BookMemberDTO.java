package org.iclass.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BookMemberDTO {
	
	private int mem_idx;
	private String name;
	private String email;
	private String tel;
	private String password;
	private String roles;

}
