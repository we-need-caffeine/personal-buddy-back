package com.app.personalbuddyback.repository;

import com.app.personalbuddyback.domain.InterestDTO;
import com.app.personalbuddyback.mapper.SurveyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public List<InterestDTO> selectAllInterest(Long id) {
        return surveyMapper.selectAllInterest(id);
    }

    public void deleteInterestDetail(Long memberId) {
        surveyMapper.deleteInterestDetailByMemberId(memberId);
    }

    public void deleteInterest(Long memberId) {
        surveyMapper.deleteInterestByMemberId(memberId);
    }

}
