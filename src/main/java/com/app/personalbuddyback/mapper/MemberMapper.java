package com.app.personalbuddyback.mapper;

import com.app.personalbuddyback.domain.MemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
//    회원가입
    public void insert(MemberVO memberVO);

//    이메일 중복 체크
    public int selectCountIdByEmail(String email);

//    전화번호 중복 체크
    public int selectCountIdByPhone(String phone);

//    닉네임 중복 체크
    public int selectCountIdByNickName(String nickname);

//    로그인
    public Long selectOne(MemberVO memberVO);

//    이메일 찾기
    public String selectEmailByPhone(String phone);

//    비밀번호 찾기
    public Long selectMemberByNameAndEmail(MemberVO memberVO);

//    비밀번호 변경
    public void updatePassword(MemberVO memberVO);

}
