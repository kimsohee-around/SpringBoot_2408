package org.iclass.board.dao;

import org.apache.ibatis.annotations.Mapper;
import org.iclass.board.dto.CommentDTO;
import org.iclass.board.dto.CommunityDTO;

import java.util.List;

@Mapper
public interface CommunityCommentsMapper {
       int insert(CommentDTO dto);
       int delete(int idx);
       List<CommentDTO> getCommentList(int mref);
       int plusCommentCount(int mref);
       int minusCommentCount(int mref);
       int selectMrefByIdx(int idx);
       CommentDTO selectByIdx(int idx);
}
