package com.app.personalbuddyback.repository;

import com.app.personalbuddyback.domain.BoardCommentLikeVO;
import com.app.personalbuddyback.domain.BoardCommentVO;
import com.app.personalbuddyback.domain.BoardCommentViewDTO;
import com.app.personalbuddyback.mapper.BoardCommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BoardCommentDAO {

    private final BoardCommentMapper boardCommentMapper;


    // 게시글 댓글 전체 목록
    public List<BoardCommentViewDTO> findComments(Map<String, Object> params) {
        return boardCommentMapper.selectBoardCommentsByBoardId(params);
    }

    // 댓글 작성
    public void saveComment(BoardCommentVO commentVO) {
        boardCommentMapper.insertBoardComment(commentVO);
    }

    // 마이페이지 내가 쓴 댓글
    public List<BoardCommentViewDTO>findCommentsByMemberId(Long memberId) {
        return boardCommentMapper.selectCommentsByMemberId(memberId);
    }

    // 댓글 수정
    public void updateComment(BoardCommentVO commentVO) {
        boardCommentMapper.updateBoardComment(commentVO);
    }

    // 댓글 삭제
    public void deleteComment(Long id) {
        boardCommentMapper.deleteBoardComment(id);
    }

    // 댓글 좋아요
    public void saveLikeComment(BoardCommentLikeVO likeVO) {
        boardCommentMapper.insertBoardCommentLike(likeVO);
    }

    // 댓글 좋아요 취소
    public void deleteLikeComment(BoardCommentLikeVO likeVO) {
        boardCommentMapper.deleteBoardCommentLike(likeVO);
    }

    // 댓글 좋아요 여부
    public int isCommentLiked(BoardCommentLikeVO likeVO) {
        return boardCommentMapper.checkBoardCommentLike(likeVO);
    }

    // 베스트 댓글 3개 조회
    public List<BoardCommentViewDTO> findBestCommentsByBoardId(Long boardId) {
        return boardCommentMapper.selectBestCommentsByBoardId(boardId);
    }
}
