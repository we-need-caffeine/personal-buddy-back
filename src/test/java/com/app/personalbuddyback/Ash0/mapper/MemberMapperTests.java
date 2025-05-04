package com.app.personalbuddyback.Ash0.mapper;

import com.app.personalbuddyback.domain.MemberVO;
import com.app.personalbuddyback.mapper.MemberMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@Slf4j
public class MemberMapperTests {

    @Autowired
    private MemberMapper memberMapper;

//    회원가입
    @Test
    public void insertTest() {

        MemberVO memberVO = new MemberVO();
        memberVO.setId(1L);
        memberVO.setMemberEmail("oasis1340@gmail.com");
        memberVO.setMemberPassword("123456");
        memberVO.setMemberName("장재영");
        try {
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            Date birth = date.parse("1998-12-11");
            memberVO.setMemberBirth(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        memberVO.setMemberGender("남성");
        memberVO.setMemberPhone("010-1234-5678");
        memberVO.setMemberNickName("babo");
        memberVO.setMemberStatusMessage("반갑읍니다");
        memberVO.setMemberImgName("바보");
        memberVO.setMemberImgPath("/img/babo.jpg");
        memberVO.setMemberPoint(0);
        memberVO.setMemberAdmin(0);
        memberVO.setMemberTermServiceAgree(1);
        memberVO.setMemberTermInformationAgree(1);
        memberVO.setMemberTermLocationAgree(1);
        memberVO.setMemberTermPromotionAgree(0);
        memberMapper.insert(memberVO);
    }

//    이메일 중복 체크
    @Test
    public void selectCountIdByEmailTest() {
        log.info("======checkEmailTest======");
        log.info("{}", memberMapper.selectCountIdByEmail("oasis1340@gmail.com"));
    }

//    전화번호 중복 체크
    @Test
    public void selectCountIdByPhoneTest() {
        log.info("======checkPhoneTest======");
        log.info("{}", memberMapper.selectCountIdByPhone("010-1234-5678"));
    }

//    닉네임 중복 체크
    @Test
    public void selectCountIdByNickNameTest() {
        log.info("======checkNickNameTest======");
        log.info("{}", memberMapper.selectCountIdByNickName("babo"));
    }

//    로그인
    @Test
    public void selectOneTest() {
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberEmail("oasis1340@gmail.com");
        memberVO.setMemberPassword("123456");
        Long memberId = memberMapper.selectOne(memberVO);
        log.info("======selectOneTest======");
        log.info("{}", memberId);
    }

//    이메일 찾기
    @Test
    public void selectEmailByPhoneTest() {
        String memberEmail = memberMapper.selectEmailByPhone("010-1234-5678");
        log.info("======selectEmailByPhoneTest======");
        log.info("{}", memberEmail);
    }

//    비밀번호 찾기
    @Test
    public void selectMemberByNameAndEmailTest() {
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberName("장재영");
        memberVO.setMemberEmail("oasis1340@gmail.com");
        Long memberId = memberMapper.selectMemberByNameAndEmail(memberVO);
        log.info("======selectMemberByNameAndEmailTest======");
        log.info("{}", memberId);
    }

//    비밀번호 변경
    @Test
    public void updatePasswordTest() {
        MemberVO memberVO = new MemberVO();
        memberVO.setId(2L);
        memberVO.setMemberPassword("123123");
        memberMapper.updatePassword(memberVO);
    }

}
