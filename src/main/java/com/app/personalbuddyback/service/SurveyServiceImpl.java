package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.InterestDTO;
import com.app.personalbuddyback.repository.SurveyDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class SurveyServiceImpl implements SurveyService {

    private final SurveyDAO surveyDAO;

    @Override
    public void insertInterest(InterestDTO interestDTO) {
        surveyDAO.insertInterest(interestDTO);
        surveyDAO.insertInterestDetail(interestDTO);
    }

    @Override
    public List<InterestDTO> interestList(Long id) {
        return surveyDAO.selectAllInterest(id);
    }

    @Override
    public void deleteInterest(Long memberId) {
        surveyDAO.deleteInterestDetail(memberId);
        surveyDAO.deleteInterest(memberId);
    }
}
