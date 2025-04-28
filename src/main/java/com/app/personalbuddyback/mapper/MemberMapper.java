package com.app.personalbuddyback.mapper;

import com.app.personalbuddyback.domain.MemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
//    회원가입
    public void insert(MemberVO memberVO);

//    이메일 중복 체크
    public int checkEmail(String email);

//    전화번호 중복 체크
    public int checkPhone(String phone);

//    닉네임 중복 체크
    public int checkNickname(String nickname);

//    로그인
    public void selectOne(MemberVO memberVO);
}
