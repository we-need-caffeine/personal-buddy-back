package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.InterestDTO;

import java.util.List;

public interface SurveyService {

    public void insertInterest(InterestDTO interestDTO);

    public void insertInterestDetail(InterestDTO interestDTO);

    public List<InterestDTO> interestList(Long memberId);

    public int selectCountByMemberId(Long memberId);

    public void deleteInterest(Long memberId);
}
