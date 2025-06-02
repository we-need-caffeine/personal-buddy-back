package com.app.personalbuddyback.controller;

import com.app.personalbuddyback.domain.InterestDataVO;
import com.app.personalbuddyback.service.RecommendService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recommends/api")
public class RecommendAPI {

    private final RecommendService recommendService;

    @Operation(summary = "추천 데이터 작성", description = "추천 데이터 작성 API")
    @ApiResponse(responseCode = "200", description = "추천 데이터 작성 성공")
    @PostMapping("insert")
    public void insert(@RequestBody InterestDataVO interestDataVO) {
        recommendService.insert(interestDataVO);
    }

    @Operation(summary = "추천 데이터 전체 조회", description = "추천 데이터 전체 조회 API")
    @ApiResponse(responseCode = "200", description = "추천 데이터 전체 조회 성공")
    @PostMapping("list")
    public List<InterestDataVO> list() {
        return recommendService.selectAll();
    }

    @Operation(summary = "회원의 추천 데이터 전체 조회", description = "회원의 추천 데이터 전체 조회 API")
    @ApiResponse(responseCode = "200", description = "추천 데이터 전체 조회 성공")
    @PostMapping("recommend")
    public List<InterestDataVO> recommend(@RequestBody Map<String, Long> request) {
        Long memberId = request.get("memberId");
        return recommendService.selectInterestDataByMemberId(memberId);
    }

    @Operation(summary = "추천 데이터 수정", description = "추천 데이터 수정 API")
    @ApiResponse(responseCode = "200", description = "추천 데이터 수정 성공")
    @PutMapping("edit")
    public void edit(@RequestBody InterestDataVO interestDataVO) {
        recommendService.edit(interestDataVO);
    }

    @Operation(summary = "추천 데이터 삭제", description = "추천 데이터 삭제 API")
    @ApiResponse(responseCode = "200", description = "추천 데이터 삭제 성공")
    @DeleteMapping("remove")
    public void remove(@RequestBody Long id) {
        recommendService.remove(id);
    }
}
