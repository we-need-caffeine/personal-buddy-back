package com.app.personalbuddyback.repository;

import com.app.personalbuddyback.domain.MemberVO;
import com.app.personalbuddyback.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberDAO {

    private final MemberMapper memberMapper;

//    회원가입
    public void insert(MemberVO memberVO) {
        memberMapper.insert(memberVO);
    }

//    이메일 중복 체크
    public int checkEmail(String email) {
        return memberMapper.selectCountIdByEmail(email);
    }

//    전화번호 중복 체크
    public int checkPhone(String phone) {
        return memberMapper.selectCountIdByPhone(phone);
    }

//    닉네임 중복 체크
    public int checkNickName(String nickname) {
        return memberMapper.selectCountIdByNickName(nickname);
    }

//    로그인
    public void selectOne(MemberVO memberVO) {
        memberMapper.selectOne(memberVO);
    }

//    이메일 찾기
    public String selectEmailByPhone(String phone) {
        return memberMapper.selectEmailByPhone(phone);
    }

//    비밀번호 찾기
    public Long selectMemberByNameAndEmail(MemberVO memberVO) {
        return memberMapper.selectMemberByNameAndEmail(memberVO);
    }

//    비밀번호 변경
    public void updatePassword(MemberVO memberVO) {
        memberMapper.updatePassword(memberVO);
    }
}
