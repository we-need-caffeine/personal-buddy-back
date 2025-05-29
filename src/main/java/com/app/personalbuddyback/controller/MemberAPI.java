package com.app.personalbuddyback.controller;

import com.app.personalbuddyback.domain.*;
import com.app.personalbuddyback.service.*;
import com.app.personalbuddyback.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members/api")
public class MemberAPI {

    private final MemberService memberService;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    private final MyTreeService myTreeService;
    private final AchievementService achievementService;
    private final SurveyService surveyService;
    private final CalendarService calendarService;

    @Operation(summary = "회원가입", description = "회원가입 API")
    @ApiResponse(responseCode = "200", description = "회원가입 성공")
    @PostMapping("join")
    public void join(@RequestBody MemberVO memberVO) {
        String password = memberVO.getMemberPassword();
        String encodedPassword = passwordEncoder.encode(password);

        memberVO.setMemberPassword(encodedPassword);

        memberService.join(memberVO);
        Long memberId = memberVO.getId();

//        캘린더 추가
        CalendarVO calendarVO = new CalendarVO();
        calendarVO.setMemberId(memberId);
        calendarVO.setCalendarIndex(1);
        calendarVO.setCalendarTitle("퍼스널버디");
        calendarService.registerCalendar(calendarVO);

//        TreeVO treeVO = new TreeVO();
//
//        treeVO.setMemberId(memberId);
//
//        myTreeService.registerMemberTree(treeVO);
//        achievementService.createAchievementComplete(memberId);
    }

    @Operation(summary = "이메일 중복 조회", description = "이메일 중복 조회 API")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping("email/check")
    public ResponseEntity<Boolean> checkEmail(@RequestParam String email) {
        boolean isEmailDuplicate = memberService.checkEmailDuplicate(email) > 0;

        return ResponseEntity.ok(isEmailDuplicate);
    }

    @Operation(summary = "전화번호 중복 조회", description = "전화번호 중복 조회 API")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping("phone/check")
    public ResponseEntity<Boolean> checkPhone(@RequestParam String phone) {
        boolean isPhoneDuplicate = memberService.checkPhoneDuplicate(phone) > 0;

        return ResponseEntity.ok(isPhoneDuplicate);
    }

    @Operation(summary = "닉네임 중복 조회", description = "닉네임 중복 조회 API")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping("nickname/check")
    public ResponseEntity<Boolean> checkNickname(@RequestParam String nickname) {
        boolean isNickNameDuplicate = memberService.checkNickNameDuplicate(nickname) > 0;

        return ResponseEntity.ok(isNickNameDuplicate);
    }

    @Operation(summary = "로그인", description = "로그인 API")
    @ApiResponse(responseCode = "200", description = "로그인 성공")
    @PostMapping("login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody MemberVO memberVO) {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> claims = new HashMap<>();
        Long memberId = memberService.login(memberVO);
        Optional<MemberVO> foundUser = memberService.getMemberInfoById(memberId);

        if (foundUser.isEmpty()) {
            response.put("message", "이메일 혹은 비밀번호를 잘못 입력하셨거나 등록되지 않은 이메일 입니다.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        response.put("message", "로그인 성공!");
        response.put("memberId", foundUser.get().getId());
        claims.put("email", foundUser.get().getMemberEmail());
        claims.put("name", foundUser.get().getMemberName());
        String jwtToken = jwtTokenUtil.generateToken(claims);
        response.put("jwtToken", jwtToken);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "토큰으로 회원 정보 조회", description = "토큰으로 회원 정보 조회 API")
    @ApiResponse(responseCode = "200", description = "회원 조회 성공")
    @PostMapping("profile")
    public ResponseEntity<Map<String, Object>> profile(
            @RequestHeader(value = "Authorization", required = false) String jwtToken
    ){
        Map<String, Object> response = new HashMap<>();
        String token = jwtToken != null ? jwtToken.replace("Bearer ", "") : null;

//        Alt + Ctrl + T
        if(token != null && jwtTokenUtil.isTokenValid(token)) {
//            유저 정보로 바꾸기
            Claims claims = jwtTokenUtil.parseToken(token);
            String memberEmail = claims.get("email").toString();
            Long memberId = memberService.getMemberIdByMemberEmail(memberEmail);
            MemberVO foundUser = memberService.getMemberInfoById(memberId).orElseThrow(() -> {
                throw new RuntimeException("member profile, Not found User");
            }); // 반갑구리

            foundUser.setMemberPassword(null);
            response.put("currentUser", foundUser);
            return ResponseEntity.ok(response);
        }

        response.put("message", "토큰이 만료되었습니다.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);

    }

    @Operation(summary = "이메일 찾기", description = "이메일 찾기 API")
    @ApiResponse(responseCode = "200", description = "이메일 찾기 성공")
    @PostMapping("find/email")
    public ResponseEntity<Optional<MemberVO>> findEmail(@RequestBody MemberVO memberVO) {
        Optional<MemberVO> foundUser = memberService.findEmail(memberVO);
        return ResponseEntity.ok(foundUser);
    }

    @Operation(summary = "비밀번호 찾기", description = "비밀번호 찾기 API")
    @ApiResponse(responseCode = "200", description = "비밀번호 찾기 성공")
    @PostMapping("/find/password")
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
    @PutMapping("/password/edit")
    public ResponseEntity<Map<String, Object>> updatePassword(@RequestBody MemberVO memberVO) {
        Map<String, Object> response = new HashMap<>();
        Long id = memberVO.getId();
        Optional<MemberVO> foundMember = memberService.getMemberInfoById(id);

        if (foundMember.isEmpty()) {
            response.put("message", "존재하지 않는 회원입니다.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        String password = memberVO.getMemberPassword();
        String encodedPassword = passwordEncoder.encode(password);

        memberVO.setMemberPassword(encodedPassword);

        memberService.editPassword(memberVO); // 실제 update 쿼리 실행

        return ResponseEntity.ok(Map.of("message", "비밀번호가 성공적으로 변경되었습니다."));
    }

    @Operation(summary = "회원 정보 수정", description = "회원 정보 수정 API")
    @ApiResponse(responseCode = "200", description = "회원 정보 수정 성공")
    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> edit(@RequestBody MemberVO memberVO) {
        Map<String, Object> response = new HashMap<>();
        Long id = memberVO.getId();
        Optional<MemberVO> foundMember = memberService.getMemberInfoById(id);

        if (foundMember.isEmpty()) {
            response.put("message", "존재하지 않는 회원입니다.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        memberService.edit(memberVO); // 실제 update 쿼리 실행

        return ResponseEntity.ok(Map.of("message", "회원정보가 성공적으로 변경되었습니다."));
    }

    @Operation(summary = "회원 탈퇴", description = "회원 탈퇴 API")
    @ApiResponse(responseCode = "200", description = "회원 탈퇴 성공")
    @DeleteMapping("/withdraw")
    public ResponseEntity<Map<String, Object>> withdraw(@RequestParam Long memberId) {
        Map<String, Object> response = new HashMap<>();

        surveyService.deleteInterest(memberId);
        memberService.withdraw(memberId);


        response.put("message", "회원 탈퇴 완료");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
