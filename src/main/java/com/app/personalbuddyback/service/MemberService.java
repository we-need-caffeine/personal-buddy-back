package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.MemberVO;

public interface MemberService {

//    회원가입
    public void join(MemberVO memberVO);

//    이메일 중복 체크
    public void checkEmailDuplicate(String email);

//    전화번호 중복 체크
    public void checkPhoneDuplicate(String phone);

//    닉네임 중복 체크
    public void checkNickNameDuplicate(String nickName);

//    로그인
    public void login(MemberVO memberVO);

//    이메일 찾기
    public String findEmail(String phone);

//    비밀번호 찾기
    public Long findPassword(MemberVO memberVO);

//    비밀번호 변경
    public void editPassword(MemberVO memberVO);

}
