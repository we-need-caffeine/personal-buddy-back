package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.*;
import com.app.personalbuddyback.repository.BoardCommentDAO;
import com.app.personalbuddyback.repository.BoardDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class BoardCommentServiceImpl implements BoardCommentService {

    private final BoardCommentDAO boardCommentDAO;
    private final BoardDAO boardDAO;

    // 댓글 전체 목록 조회
    @Override
    public List<BoardCommentViewDTO> getBoardComments(Long boardId) {
        return boardCommentDAO.findComments(boardId);
    }

    // 마이페이지 (내가 쓴 댓글)
    @Override
    public List<BoardCommentViewDTO> getCommentsByMemberId(Long memberId) {
        return boardCommentDAO.findCommentsByMemberId(memberId);
    }

    // 댓글 작성
    @Override
    public void writeComment(BoardCommentVO boardcommentVO) {
        boardCommentDAO.saveComment(boardcommentVO);
    }

    // 댓글 수정
    @Override
    public void updateComment(BoardCommentVO boardcommentVO) {
        boardCommentDAO.updateComment(boardcommentVO);
    }

    // 댓글 삭제
    @Override
    public void deleteComment(Long id) {
        boardCommentDAO.deleteComment(id);
    }

    // 댓글 좋아요 추가
    @Override
    public void likeComment(BoardCommentLikeVO boardCommentLikeVO) {
        boardCommentDAO.saveLikeComment(boardCommentLikeVO);
    }

    // 댓글 좋아요 취소
    @Override
    public void deleteLikeComment(BoardCommentLikeVO boardCommentLikeVO) {
        boardCommentDAO.deleteLikeComment(boardCommentLikeVO);
    }

    // 댓글 좋아요 여부 확인
    @Override
    public int isBoardCommentLiked(BoardCommentLikeVO boardCommentLikeVO) {
        return boardCommentDAO.isCommentLiked(boardCommentLikeVO);
    }

    // 베스트 댓글 3개 조회
    @Override
    public List<BoardCommentViewDTO> getBestCommentsByBoardId(Long boardId) {
        return boardCommentDAO.findBestCommentsByBoardId(boardId);
    }
}

