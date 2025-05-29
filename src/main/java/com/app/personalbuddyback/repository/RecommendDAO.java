package com.app.personalbuddyback.repository;

import com.app.personalbuddyback.domain.InterestDataVO;
import com.app.personalbuddyback.mapper.RecommendMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RecommendDAO {

    private final RecommendMapper recommendMapper;

//    추천 데이터 작성
    public void insert(InterestDataVO interestDataVO) {
        recommendMapper.insert(interestDataVO);
    }

//    추천 데이터 전체 조회
    public List<InterestDataVO> selectAll() {
        return recommendMapper.selectAll();
    }

//    회원의 추천 데이터 전체 조회
    public List<InterestDataVO> selectInterestDataByMemberId(Long memberId) {
        return recommendMapper.selectInterestDataByMemberId(memberId);
    }

//    추천 데이터 수정
    public void update(InterestDataVO interestDataVO) {
        recommendMapper.update(interestDataVO);
    }

//    추천 데이터 삭제
    public void delete(Long id) {
        recommendMapper.delete(id);
    }
}
