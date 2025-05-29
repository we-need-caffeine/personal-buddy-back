package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.InterestDataVO;

import java.util.List;

public interface RecommendService {

//    추천 데이터 작성
    public void insert(InterestDataVO interestDataVO);

//    추천 데이터 전체 조회
    public List<InterestDataVO> selectAll();

//    회원의 추천 데이터 전체 조회
    public List<InterestDataVO> selectInterestDataByMemberId(Long memberId);

//    추천 데이터 수정
    public void edit(InterestDataVO interestDataVO);

//    추천 데이터 삭제
    public void remove(Long id);
}
