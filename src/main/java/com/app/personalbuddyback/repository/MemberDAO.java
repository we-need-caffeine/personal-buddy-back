package com.app.personalbuddyback.repository;

import com.app.personalbuddyback.domain.MemberVO;
import com.app.personalbuddyback.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberDAO {

    private final MemberMapper memberMapper;

//    회원가입
    public void insert(MemberVO memberVO) {
        memberMapper.insert(memberVO);
    }

//    ID로 회원 상세 정보 조회
    public Optional<MemberVO> selectMemberById(Long id){
        return memberMapper.selectMemberInfoById(id);
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
    public Long selectOne(MemberVO memberVO) {
        return memberMapper.selectOne(memberVO);
    }

    public Optional<MemberVO> selectMemberByEmail (String memberEmail) { return memberMapper.selectMemberByEmail(memberEmail); }

//    이메일로 회원 아이디 조회
    public Long selectIdByEmail(String email) {
        return memberMapper.selectIdByEmail(email);
    }

//    이메일 찾기 (회원 존재 여부 확인)
    public int selectCountIdByNameAndPhone(MemberVO memberVO) {
        return memberMapper.selectCountIdByNameAndPhone(memberVO);
    }

//    이메일 찾기 (회원 이메일 조회)
    public Optional<MemberVO> selectEmailByNameAndPhone(MemberVO memberVO) {
        return memberMapper.selectEmailByNameAndPhone(memberVO);
    }

//    비밀번호 찾기 (이름과 이메일이 일치하는 회원 존재 여부 확인)
    public int selectCountIdByNameAndEmail(MemberVO memberVO) {
        return memberMapper.selectCountIdByNameAndEmail(memberVO);
    }

//    비밀번호 찾기 (이름과 이메일이 일치하는 회원의 ID 조회)
    public Long selectIdByNameAndEmail(MemberVO memberVO) {
        return memberMapper.selectIdByNameAndEmail(memberVO);
    }

//    비밀번호 변경
    public void updatePassword(MemberVO memberVO) {
        memberMapper.updatePassword(memberVO);
    }

//    회원 정보 수정
    public void update(MemberVO memberVO) {
        memberMapper.update(memberVO);
    }

//    회원 탈퇴
    public void delete(Long id) {
        memberMapper.delete(id);
    }
}
