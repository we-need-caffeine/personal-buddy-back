package com.app.personalbuddyback.controller;

import com.app.personalbuddyback.domain.MemberVO;
import com.app.personalbuddyback.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberAPI {

    private final MemberService memberService;

    @Operation(summary = "회원가입", description = "회원가입 API")
    @ApiResponse(responseCode = "200", description = "회원가입 성공")
    @PostMapping("join")
    public void join(@RequestBody MemberVO memberVO) {
        memberService.join(memberVO);
    }

    @Operation(summary = "이메일 중복 조회", description = "이메일 중복 조회 API")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping("check-email")
    public ResponseEntity<Boolean> checkEmail(@RequestParam String email) {
        boolean isEmailDuplicate = memberService.checkEmailDuplicate(email) > 0;

        return ResponseEntity.ok(isEmailDuplicate);
    }

    @Operation(summary = "전화번호 중복 조회", description = "전화번호 중복 조회 API")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping("check-phone")
    public ResponseEntity<Boolean> checkPhone(@RequestParam String phone) {
        boolean isPhoneDuplicate = memberService.checkPhoneDuplicate(phone) > 0;

        return ResponseEntity.ok(isPhoneDuplicate);
    }

    @Operation(summary = "닉네임 중복 조회", description = "닉네임 중복 조회 API")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping("check-nickname")
    public ResponseEntity<Boolean> checkNickname(@RequestParam String nickname) {
        boolean isNickNameDuplicate = memberService.checkNickNameDuplicate(nickname) > 0;

        return ResponseEntity.ok(isNickNameDuplicate);
    }

    @Operation(summary = "로그인", description = "로그인 API")
    @ApiResponse(responseCode = "200", description = "로그인 성공")
    @PostMapping("login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody MemberVO memberVO) {
        Map<String, Object> response = new HashMap<>();
        Long memberId = memberService.login(memberVO);
        Optional<MemberVO> foundUser = memberService.getMemberInfoById(memberId);

        if (foundUser.isEmpty()) {
            response.put("message", "이메일 혹은 비밀번호를 잘못 입력하셨거나 등록되지 않은 이메일 입니다.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        response.put("message", "로그인 성공!");
        response.put("memberId", foundUser.get().getId());

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "이메일 찾기", description = "이메일 찾기 API")
    @ApiResponse(responseCode = "200", description = "이메일 찾기 성공")
    @PostMapping("find-email")
    public void findEmail(@RequestBody MemberVO memberVO) {
        memberService.findEmail(memberVO);

    }

    @Operation(summary = "비밀번호 찾기", description = "비밀번호 찾기 API")
    @ApiResponse(responseCode = "200", description = "비밀번호 찾기 성공")
    @PostMapping("/find-id-by-name-email")
    public ResponseEntity<Map<String, Object>> findIdByNameAndEmail(@RequestBody MemberVO memberVO) {
        Map<String, Object> response = new HashMap<>();
        Long id = memberService.findIdByNameAndEmail(memberVO);

        if (id == null) {
            response.put("message", "이름 혹은 이메일을 잘못 입력하셨거나 등록되지 않은 이메일 입니다.");
           return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        response.put("message", "회원");
        response.put("id", id);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "비밀번호 변경", description = "비밀번호 변경 API")
    @ApiResponse(responseCode = "200", description = "비밀번호 변경 성공")
    @PutMapping("/update-password")
    public ResponseEntity<Map<String, Object>> updatePassword(@RequestBody MemberVO memberVO) {
        Map<String, Object> response = new HashMap<>();
        Long id = memberVO.getId();
        Optional<MemberVO> foundMember = memberService.getMemberInfoById(id);

        if (foundMember.isEmpty()) {
            response.put("message", "존재하지 않는 회원입니다.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        memberService.editPassword(memberVO); // 실제 update 쿼리 실행

        return ResponseEntity.ok(Map.of("message", "비밀번호가 성공적으로 변경되었습니다."));
    }




}
