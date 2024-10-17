package org.iclass.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iclass.board.dao.CommunityCommentsMapper;
import org.iclass.board.dto.CommentDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.stream.events.Comment;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommunityCommentsMapper commentsMapper;

    @Transactional
    public int write(CommentDTO comment) {
        //아래 2개의 DML 은 하나의 트랜잭션. 성공하면 2개 모두 commit
        // 하나라도 오류로 실패하면 2개 모두 rollback
        int result = commentsMapper.insert(comment);
        commentsMapper.plusCommentCount(comment.getMref());
//        return result;
        return comment.getIdx();
    }
    @Transactional
    public int remove(int idx){
        int mref = commentsMapper.selectMrefByIdx(idx);
        int result = commentsMapper.delete(idx);
        commentsMapper.minusCommentCount(mref);
        return mref;   //리턴값은 필요에 따라 결정.
    }


    public List<CommentDTO> getComments(int mref) {
        return commentsMapper.getCommentList(mref);
    }

    public CommentDTO selectByIdx(int idx) {
        return  commentsMapper.selectByIdx(idx);
    }
}
