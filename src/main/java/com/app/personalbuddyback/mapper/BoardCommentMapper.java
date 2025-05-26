package com.app.personalbuddyback.mapper;

import com.app.personalbuddyback.domain.BoardCommentLikeVO;
import com.app.personalbuddyback.domain.BoardCommentVO;
import com.app.personalbuddyback.domain.BoardCommentViewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardCommentMapper {

    //    게시글 댓글 전체 목록
    public List<BoardCommentViewDTO> selectBoardCommentsByBoardId(Long boardId);

    //      내가 쓴 댓글 (마이페이지용)
    public List<BoardCommentViewDTO> selectCommentsByMemberId(Long memberId);

    //    댓글 작성
    public void insertBoardComment(BoardCommentVO boardCommentVO);

    //    댓글 수정
    public void updateBoardComment(BoardCommentVO boardCommentVO);

    //    댓글 삭제
    public void deleteBoardComment(Long id);

    // 게시글 ID 기준으로 댓글 전체 삭제
    void deleteByBoardId(Long boardId);

    //    댓글 좋아요
    public void insertBoardCommentLike(BoardCommentLikeVO boardCommentLikeVO);

    //    댓글 좋아요 취소
   public void deleteBoardCommentLike(BoardCommentLikeVO boardCommentLikeVO);

    //    댓글 좋아요 여부
    public int checkBoardCommentLike(BoardCommentLikeVO boardCommentLikeVO);

    //    베스트 댓글 3개 조회
    public List<BoardCommentViewDTO> selectBestCommentsByBoardId(Long boardId);
}
