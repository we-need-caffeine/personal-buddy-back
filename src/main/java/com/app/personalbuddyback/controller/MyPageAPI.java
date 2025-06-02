package com.app.personalbuddyback.controller;

import com.app.personalbuddyback.domain.MemberPointLogVO;
import com.app.personalbuddyback.domain.MemberVO;
import com.app.personalbuddyback.service.MemberService;
import com.app.personalbuddyback.service.PointService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("mypages/api/*")
public class MyPageAPI {

    private final PointService pointService;
    private final MemberService memberService;

    @Operation(summary = "포인트 로그 조회", description = "회원의 포인트 내역을 조회")
    @GetMapping("point/log/{memberId}")
    public List<MemberPointLogVO> getPointLog(@PathVariable Long memberId){
        return pointService.getPointLogs(memberId);
    }
    
    @Operation(summary = "비밀번호 변경", description = "회원의 비밀번호를 변경")
    @PutMapping("member/update-password")
    public void updatePassword(@RequestBody MemberVO memberVO){
        memberService.editPassword(memberVO);
    }
}
