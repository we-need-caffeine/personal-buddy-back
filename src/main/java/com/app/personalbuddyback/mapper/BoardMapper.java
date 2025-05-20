package com.app.personalbuddyback.mapper;
import com.app.personalbuddyback.domain.*;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface BoardMapper {

    //    게시판 - 게시글 전체조회
    public List<BoardDTO> selectBoards(Map<String, Object> params);

    //    게시판 - 게시글 Hot 전체조회
    public List<BoardDTO> selectHotBoards();


    //    게시글 전체 목록 조회
    public List<BoardListViewDTO> selectBoardList();

    //    내가 쓴 게시글(마이페이지용)
    List<BoardListViewDTO> selectBoardListByMemberId(Long memberId);

    //    HOT 게시글 조회
    public List<BoardListViewDTO> selectHotBoardList();

    //    게시글 검색 + 정렬 + 해시태그 필터링
    public List<BoardListViewDTO> selectBoardListBySearch(Map<String,Object> params);

    //    게시글 상세 조회 (글 정보 + 작성자 정보 + 댓글수 + 좋아요수)
    public Optional<BoardViewDTO> selectBoardById(Long id);

    //    게시글 작성
    public void insertBoard(BoardVO boardVO);

    //    게시글 수정
    public void updateBoard(BoardVO boardVO);

    //    게시글 삭제
    public void deleteBoard(Long id);

    //    게시글 이미지 추가
    public void insertBoardImage(BoardImgVO boardImgVO);

    //    게시글 이미지 전체 삭제
    public void deleteBoardImages(Long boardId);

    //    게시글 이미지 단일 삭제
    public void deleteBoardImageById(Long id);

    //    게시글 조회수 1 증가
    public void updateBoardViews(Long id);

    //    게시글 좋아요 추가
    public void insertBoardLike(BoardLikeVO boardLikeVO);

    //    게시글 좋아요 삭제
    public void deleteBoardLike(BoardLikeVO boardLikeVO);

    //    게시글 좋아요 여부 체크(로그인이 된 유저가 좋아요를 눌렸는지)
    public int checkBoardLike(BoardLikeVO boardLikeVO);
}