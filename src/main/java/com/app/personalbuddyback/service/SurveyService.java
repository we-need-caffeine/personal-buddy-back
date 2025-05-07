package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.InterestDTO;

public interface SurveyService {

    public void insertInterest(InterestDTO interestDTO);

    public void insertInterestDetail(InterestDTO interestDTO);

    public void deleteInterestDetail(Long memberId);

    public void deleteInterest(Long memberId);
}
