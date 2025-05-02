package com.app.personalbuddyback.repository;

import com.app.personalbuddyback.domain.BoardImgVO;
import com.app.personalbuddyback.domain.BoardListViewDTO;
import com.app.personalbuddyback.domain.BoardVO;
import com.app.personalbuddyback.domain.BoardViewDTO;
import com.app.personalbuddyback.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BoardDAO {

    private final BoardMapper boardMapper;

    // 게시글 전체 목록
    public List<BoardListViewDTO> findAll() {
        return boardMapper.selectBoardList();
    }

    // HOT 게시글
    public List<BoardListViewDTO> findHotList() {
        return boardMapper.selectHotBoardList();
    }

    // 게시글 검색 + 정렬 + 해시태그 필터링
    public List<BoardListViewDTO> findBoardListBySearch(Map<String,Object> params) {
        return boardMapper.selectBoardListBySearch(params);
    }

    // 게시글 상세 조회
    public Optional<BoardViewDTO>findById(Long id) {
        return boardMapper.selectBoardById(id);
    }

    // 게시글 작성
    public void save(BoardVO boardVO) {
        boardMapper.insertBoard(boardVO);
    }

    // 게시글 이미지 등록
    public void saveImage(BoardImgVO boardImgVO) {
        boardMapper.insertBoardImage(boardImgVO);
    }

    // 게시글 수정
    public void update(BoardVO boardVO) {

    }

}
