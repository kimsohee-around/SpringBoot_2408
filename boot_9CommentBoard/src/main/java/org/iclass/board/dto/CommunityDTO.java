package org.iclass.board.dto;

import java.sql.Date;

import jakarta.validation.constraints.Size;
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
public class CommunityDTO {
	private int rnum;
	private int idx;
	private String writer;
	@Size(min = 2, message = "제목은 2글자 이상 입력하세요.")
	private String title;

	@Size(min =10 , message = "글 내용은 10글자 이상 입력하세요.")
	private String content;
	private int readCount;
	private Date createdAt;
	private String ip;
	private int commentCount;
	
}
