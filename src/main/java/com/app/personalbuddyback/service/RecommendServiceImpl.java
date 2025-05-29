package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.InterestDataVO;
import com.app.personalbuddyback.repository.RecommendDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendServiceImpl implements RecommendService {

    private final RecommendDAO recommendDAO;

//    추천 데이터 작성
    @Override
    public void insert(InterestDataVO interestDataVO) {
        recommendDAO.insert(interestDataVO);
    }

//    추천 데이터 전체 조회
    @Override
    public List<InterestDataVO> selectAll() {
        return recommendDAO.selectAll();
    }

//    회원의 추천 데이터 전체 조회

    @Override
    public List<InterestDataVO> selectInterestDataByMemberId(Long memberId) {
        return recommendDAO.selectInterestDataByMemberId(memberId);
    }

    //    추천 데이터 수정
    @Override
    public void edit(InterestDataVO interestDataVO) {
        recommendDAO.update(interestDataVO);
    }

//    추천 데이터 삭제
    @Override
    public void remove(Long id) {
        recommendDAO.delete(id);
    }
}
