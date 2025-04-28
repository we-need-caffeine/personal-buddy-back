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
        return memberMapper.checkEmail(email);
    }

//    전화번호 중복 체크
    public int checkPhone(String phone) {
        return memberMapper.checkPhone(phone);
    }

//    닉네임 중복 체크
    public int checkNickname(String nickname) {
        return memberMapper.checkNickname(nickname);
    }

//    로그인
    public void selectOne(MemberVO memberVO) {
        memberMapper.selectOne(memberVO);
    }

}
