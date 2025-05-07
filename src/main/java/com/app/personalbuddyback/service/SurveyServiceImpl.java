package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.InterestDTO;
import com.app.personalbuddyback.repository.SurveyDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class SurveyServiceImpl implements SurveyService {

    private final SurveyDAO surveyDAO;

    @Override
    public void insertInterest(InterestDTO interestDTO) {
        surveyDAO.insertInterest(interestDTO);
    }

    @Override
    public void insertInterestDetail(InterestDTO interestDTO) {
        surveyDAO.insertInterestDetail(interestDTO);
    }

    @Override
    public void deleteInterestDetail(Long memberId) {
        surveyDAO.deleteInterestDetail(memberId);
    }

    @Override
    public void deleteInterest(Long memberId) {
        surveyDAO.deleteInterest(memberId);
    }
}
