package com.app.personalbuddyback.ZeroWater;

import com.app.personalbuddyback.domain.GuestBookVO;
import com.app.personalbuddyback.mapper.GuestBookMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class GuestBookTests {

    @Autowired
    private GuestBookMapper guestBookMapper;

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

}
