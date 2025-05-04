package com.app.personalbuddyback.Ash0.service;

import com.app.personalbuddyback.domain.InterestDTO;
import com.app.personalbuddyback.mapper.SurveyMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class SurveyServiceTests {

    @Autowired
    private SurveyMapper surveyMapper;

    @Test
    public void insertInterestTest() {
        InterestDTO interestDTO = new InterestDTO();
        interestDTO.setMemberId(1L);
    }
}
