package com.app.personalbuddyback.repository;

import com.app.personalbuddyback.domain.InterestDTO;
import com.app.personalbuddyback.mapper.SurveyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SurveyDAO {

    private final SurveyMapper surveyMapper;

    public void insertInterest(InterestDTO interestDTO) {
        surveyMapper.insertInterest(interestDTO);
    }

    public void insertInterestDetail(InterestDTO interestDTO) {
        surveyMapper.insertInterestDetail(interestDTO);
    }

    public void deleteInterestDetail(Long memberId) {
        surveyMapper.deleteInterestDetailByMemberId(memberId);
    }

    public void deleteInterest(Long memberId) {
        surveyMapper.deleteInterestByMemberId(memberId);
    }

}
