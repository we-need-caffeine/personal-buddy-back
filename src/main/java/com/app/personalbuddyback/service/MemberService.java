package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.MemberVO;

import java.util.Optional;

public interface MemberService {

//    회원가입
    public void join(MemberVO memberVO);

//    ID로 회원 상세 정보 조회
    public Optional<MemberVO> getMemberInfoById(Long id);

//    이메일 중복 체크
    public int checkEmailDuplicate(String email);

//    전화번호 중복 체크
    public int checkPhoneDuplicate(String phone);

//    닉네임 중복 체크
    public int checkNickNameDuplicate(String nickName);

//    로그인
    public Long login(MemberVO memberVO);

//    이메일로 회원 아이디 조회
    public Long getMemberIdByMemberEmail(String email);

//    이메일 찾기 (회원 존재 여부 확인)
    public int checkMemberEmailByNameAndPhone(MemberVO memberVO);

//    이메일 찾기 (회원 이메일 조회)
    public String findEmail(MemberVO memberVO);

//    비밀번호 찾기 (이름과 이메일이 일치하는 회원 존재 여부 확인)
    public int checkMemberByNameAndEmail(MemberVO memberVO);

//    비밀번호 찾기
    public Long findIdByNameAndEmail(MemberVO memberVO);

//    비밀번호 변경
    public void editPassword(MemberVO memberVO);

//    소셜 로그인으로 회원 정보 수정
    public void modify (MemberVO memberVO);

}
