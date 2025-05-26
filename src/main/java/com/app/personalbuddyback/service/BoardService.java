package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BoardService {

    //    게시판 - 게시글 전체 조회
    public List<BoardDTO> getBoards(Map<String,Object> params);

    //    게시판 - 게시글 Hot 전체 조회
    public List<BoardDTO> getBoardsHot();

    // 게시글 전체 목록 조회
    public List<BoardListViewDTO> getAllBoards();

    // 내가 쓴 게시글 목록 조회 (마이페이지)
    public List<BoardListViewDTO> getBoardsByMemberId(Long memberId);

    // HOT 게시글 목록 조회 (좋아요 수 많은 상위 10개)
    public List<BoardListViewDTO> getHotBoards();

    // 게시글 검색 + 정렬 + 해시태그 필터링 조회
    public List<BoardListViewDTO> getBoardsBySearch(Map<String, Object> params);

    // 게시글 상세 조회
    public Optional<BoardViewDTO> getBoardById(Long id);

    // 게시글 단독 등록
    public void writeBoard(BoardVO boardVO);

    // 게시글 이미지 단독 등록
    public void addBoardImage(BoardImgVO boardImgVO);

    // 게시글 + 이미지 함께 등록
    public void writeBoardWithImages(BoardVO boardVO, List<BoardImgVO> images);

    // 게시글 수정
    public void updateBoard(BoardVO boardVO);

    // 게시글 이미지 전체 삭제
    public void removeAllBoardImages(Long boardId);

    // 게시글 이미지 단일 삭제
    public void removeBoardImageById(Long id);

    // 게시글 이미지 파일명으로 단일 삭제
    void removeBoardImageByName(String name);

    void saveBoardImage(Long boardId, MultipartFile image);

    // 게시글 단독 삭제
    public void removeBoard(Long id);

    // 게시글 조회수 증가
    public void increaseBoardViews(Long id);

    // 게시글 좋아요 추가
    public void increaseLikeBoard(BoardLikeVO boardLikeVO);

    // 게시글 좋아요 취소
    public void deleteLikeBoard(BoardLikeVO boardLikeVO);

    // 게시글 좋아요 여부 확인
    public int isBoardLiked(BoardLikeVO boardLikeVO);

    // 마이페이지 (내가 쓴 게시글)
    public List<BoardListViewDTO> getBoardByMemberId(Long memberId);

}