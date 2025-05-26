package com.app.personalbuddyback.mapper;

import com.app.personalbuddyback.domain.InterestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SurveyMapper {
//    설문조사 대분류 추가
    public void insertInterest(InterestDTO interestDTO);
//    설문조사 상세 추가
    public void insertInterestDetail(InterestDTO interestDTO);
//    설문조사 전체 조회
    public List<InterestDTO> selectAllInterest(Long id);
//    회원의 설문조사 정보 유무 체크
    public int selectCountByMemberId(Long memberId);
//    설문조사 상세 제거
    public void deleteInterestDetailByMemberId(Long memberId);
//    설문조사 대분류 제거
    public void deleteInterestByMemberId(Long memberId);
}
