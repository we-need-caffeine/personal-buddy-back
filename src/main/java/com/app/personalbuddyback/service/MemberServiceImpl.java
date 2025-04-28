package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.MemberVO;
import com.app.personalbuddyback.repository.MemberDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class MemberServiceImpl implements MemberService {

    private final MemberDAO memberDAO;

    @Override
    public void join(MemberVO memberVO) {
        memberDAO.insert(memberVO);
    }

    @Override
    public void checkEmailDuplicate(String email) {
        memberDAO.checkEmail(email);
    }

    @Override
    public void checkPhoneDuplicate(String phone) {
        memberDAO.checkPhone(phone);
    }

    @Override
    public void checkNickNameDuplicate(String nickName) {
        memberDAO.checkNickName(nickName);
    }

    @Override
    public void login(MemberVO memberVO) {
        memberDAO.selectOne(memberVO);
    }

    @Override
    public String findEmail(String phone) {
        return memberDAO.selectEmailByPhone(phone);
    }

    @Override
    public Long findPassword(MemberVO memberVO) {
        return memberDAO.selectMemberByNameAndEmail(memberVO);
    }

    @Override
    public void editPassword(MemberVO memberVO) {
        memberDAO.updatePassword(memberVO);
    }
}
