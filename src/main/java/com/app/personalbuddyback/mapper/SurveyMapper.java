package com.app.personalbuddyback.mapper;

import com.app.personalbuddyback.domain.InterestDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SurveyMapper {
//    설문조사 대분류 추가
    public void insertInterest(InterestDTO interestDTO);
//    설문조사 상세 추가
    public void insertInterestDetail(InterestDTO interestDTO);
//    설문조사 상세 제거
    public void deleteInterestDetailByMemberId(Long memberId);
//    설문조사 대분류 제거
    public void deleteInterestByMemberId(Long memberId);
}
