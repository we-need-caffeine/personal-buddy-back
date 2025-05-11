package com.app.personalbuddyback.Ash0.service;

import com.app.personalbuddyback.domain.InterestDTO;
import com.app.personalbuddyback.mapper.SurveyMapper;
import com.app.personalbuddyback.service.SurveyService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
public class SurveyServiceTests {

    @Autowired
    private SurveyService surveyService;

//    @Test
//    public void insertInterestTest() {
//        InterestDTO interestDTO = new InterestDTO();
//        interestDTO.setMemberId(2L);
//        interestDTO.setInterestType("음식");
//        interestDTO.setInterestDetailInformation("한식");
//        interestDTO.setInterestDetailPlace("음식점");
//        interestDTO.setInterestDetailShopping("젤리");
//
//        surveyService.insertInterest(interestDTO);
//        Long generatedId = interestDTO.getInterestId();
//        log.info("generatedId: {}", generatedId);
//        surveyService.insertInterestDetail(interestDTO);
//    }
//
//    @Test
//    public void deleteInterestTest() {
//        surveyService.deleteInterestDetail(2L);
//        surveyService.deleteInterest(2L);
//    }
}
