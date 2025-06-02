package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.MemberVO;
import com.app.personalbuddyback.repository.MemberDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class MemberServiceImpl implements MemberService {

    private final MemberDAO memberDAO;
    private final PasswordEncoder passwordEncoder;
    //    회원가입
    @Override
    public void join(MemberVO memberVO) {
        memberDAO.insert(memberVO);
    }

    //    ID로 회원 상세 정보 조회
    @Override
    public Optional<MemberVO> getMemberInfoById(Long id) {
        return memberDAO.selectMemberById(id);
    }

    //    이메일 중복 체크 & 이메일 존재 여부 확인(비밀번호 찾기)
    @Override
    public int checkEmailDuplicate(String email) {
        return memberDAO.checkEmail(email);
    }

    //    전화번호 중복 체크
    @Override
    public int checkPhoneDuplicate(String phone) {
        return memberDAO.checkPhone(phone);
    }

    //    닉네임 중복 체크
    @Override
    public int checkNickNameDuplicate(String nickName) {
        return memberDAO.checkNickName(nickName);
    }

    //    로그인
    @Override
    public Long login(MemberVO memberVO) {
        Optional<MemberVO> foundMember = memberDAO.selectMemberByEmail(memberVO.getMemberEmail());

        if (foundMember.isEmpty()) return null;

//        String provider = foundMember.get().getMemberProvider();
//        if ("local".equals(provider)) {
//            if (!passwordEncoder.matches(memberVO.getMemberPassword(), foundMember.get().getMemberPassword())) {
//                return null;
//            }
//        }

        return foundMember.get().getId();
    }

    @Override
    public Long getMemberIdByMemberEmail(String email) {
        return memberDAO.selectIdByEmail(email);
    }

    //    이메일 찾기 (회원 존재 여부 확인)
    @Override
    public int checkMemberEmailByNameAndPhone(MemberVO memberVO) {
        return memberDAO.selectCountIdByNameAndPhone(memberVO);
    }
    //    이메일 찾기 (회원 이메일 조회)
    @Override
    public Optional<MemberVO> findEmail(MemberVO memberVO) {
        return memberDAO.selectEmailByNameAndPhone(memberVO);
    }

    //    비밀번호 찾기 (이름과 이메일이 일치하는 회원 존재 여부 확인)
    @Override
    public int checkMemberByNameAndEmail(MemberVO memberVO) {
        return memberDAO.selectCountIdByNameAndEmail(memberVO);
    }

    //    비밀번호 찾기 (이름과 이메일이 일치하는 회원의 ID 조회)
    @Override
    public Long findIdByNameAndEmail(MemberVO memberVO) {
        return memberDAO.selectIdByNameAndEmail(memberVO);
    }

    // 비밀번호 변경
    @Override
    public void editPassword(MemberVO memberVO) {
        memberDAO.updatePassword(memberVO);
    }

    // 회원 정보 수정
    @Override
    public void edit(MemberVO memberVO) {
        memberDAO.update(memberVO);
    }

    // 회원 탈퇴
    @Override
    public void withdraw(Long id) {
        memberDAO.delete(id);
    }

}
