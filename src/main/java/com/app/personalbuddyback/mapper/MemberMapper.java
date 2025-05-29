package com.app.personalbuddyback.mapper;

import com.app.personalbuddyback.domain.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MemberMapper {
//    회원가입
    public void insert(MemberVO memberVO);

//    ID로 회원 상세 정보 조회
    public Optional<MemberVO> selectMemberInfoById (Long id);

//    이메일 중복 체크 & 이메일 존재 여부 확인(비밀번호 찾기)
    public int selectCountIdByEmail(String email);

//    전화번호 중복 체크
    public int selectCountIdByPhone(String phone);

//    닉네임 중복 체크
    public int selectCountIdByNickName(String nickname);

//    로그인
    public Long selectOne(MemberVO memberVO);

    public Optional<MemberVO> selectMemberByEmail(String memberEmail);

//    이메일로 아이디 조회
    public Long selectIdByEmail(String email);

//    이메일 찾기 (회원 존재 여부 확인)
    public int selectCountIdByNameAndPhone(MemberVO memberVO);

//    이메일 찾기 (회원 이메일 조회)
    public Optional<MemberVO> selectEmailByNameAndPhone(MemberVO memberVO);

//    비밀번호 찾기 (이름과 이메일이 일치하는 회원 존재 여부 확인)
    public int selectCountIdByNameAndEmail(MemberVO memberVO);

//    비밀번호 찾기 (이름과 이메일이 일치하는 회원의 ID 조회)
    public Long selectIdByNameAndEmail(MemberVO memberVO);

//    비밀번호 변경
    public void updatePassword(MemberVO memberVO);

//    회원 정보 수정
    public void update(MemberVO memberVO);

//    회원 탈퇴
    public void delete(Long id);

    // 현재 포인트 가져오기
    int getMemberPoint(Long memberId);

    // 포인트 업데이트
    void updateMemberPoint(Long memberId, int newPoint);

}
