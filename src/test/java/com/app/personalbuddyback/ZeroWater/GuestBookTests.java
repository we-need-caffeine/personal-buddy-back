package com.app.personalbuddyback.ZeroWater;

import com.app.personalbuddyback.domain.GuestBookVO;
import com.app.personalbuddyback.domain.GuestBookViewDTO;
import com.app.personalbuddyback.domain.MemberVO;
import com.app.personalbuddyback.mapper.GuestBookMapper;
import com.app.personalbuddyback.repository.GuestBookDAO;
import com.app.personalbuddyback.repository.MemberDAO;
import com.app.personalbuddyback.service.GuestBookService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class GuestBookTests {

    @Autowired
    private GuestBookMapper guestBookMapper;

    @Autowired
    private GuestBookService guestBookService;
    @Autowired
    private GuestBookDAO guestBookDAO;
    @Autowired
    private MemberDAO memberDAO;

    @Test
    public void insertTest() {
        GuestBookVO guestBookVO = new GuestBookVO();
        guestBookVO.setOwnerMemberId(1L);
        guestBookVO.setWriterMemberId(2L);
        guestBookVO.setGuestbookContent("내용");

        guestBookMapper.insert(guestBookVO);
    }
    @Test
    public void selectAllTest() {
        List<GuestBookVO> guestBookVOList = guestBookMapper.selectAll(1L);

        guestBookVOList.forEach(guestBookVO -> log.info(guestBookVO.getGuestbookContent()));
    }

    @Test
    public void deleteTest() {
        guestBookMapper.delete(1L);
    }

    @Test
    public void selectGuestBooksViewTest() {
            List<GuestBookVO> guestBooks = guestBookDAO.findAll(1L);
            List<GuestBookViewDTO> guestBookViews = new ArrayList<>();
            GuestBookViewDTO guestBookViewDTO = new GuestBookViewDTO();

            guestBooks.forEach(guestBook -> {
                Optional<MemberVO> memberVO = memberDAO.selectMemberById(guestBook.getWriterMemberId());

                guestBookViewDTO.setId(guestBook.getId());
                guestBookViewDTO.setGuestbookContent(guestBook.getGuestbookContent());
                guestBookViewDTO.setOwnerMemberId(guestBook.getOwnerMemberId());
                guestBookViewDTO.setWriterMemberId(memberVO.get().getId());
                guestBookViewDTO.setGuestbookCreateTime(guestBook.getGuestbookCreateTime());
                guestBookViewDTO.setMemberImgName(memberVO.get().getMemberImgName());
                guestBookViewDTO.setMemberImgPath(memberVO.get().getMemberImgPath());

                guestBookViews.add(guestBookViewDTO);
            });
            log.info(guestBookViews.toString());
    }
}
