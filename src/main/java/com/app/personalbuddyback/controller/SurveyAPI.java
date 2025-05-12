package com.app.personalbuddyback.controller;

import com.app.personalbuddyback.domain.InterestDTO;
import com.app.personalbuddyback.service.SurveyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/surveys/api")
public class SurveyAPI {

    private final SurveyService surveyService;

    @Operation(summary = "설문조사 작성", description = "설문조사 작성 API")
    @ApiResponse(responseCode = "200", description = "설문조사 작성 성공")
    @PostMapping("insert")
    public void insert(@RequestBody InterestDTO interestDTO) {
        surveyService.insertInterest(interestDTO);
    }

    @Operation(summary = "설문조사 전체 조회", description = "설문조사 전체 조회 API")
    @ApiResponse(responseCode = "200", description = "설문조사 전체 조회 성공")
    @PostMapping("list")
    public List<InterestDTO> list(Long id) {
        return surveyService.interestList(id);
    }

    @Operation(summary = "설문조사 삭제", description = "설문조사 삭제 API")
    @ApiResponse(responseCode = "200", description = "설문조사 삭제 성공")
    @DeleteMapping("remove")
    public void remove(@RequestBody Long memberId) {
        surveyService.deleteInterest(memberId);
    }
}
