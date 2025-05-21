package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.AlertVO;
import com.app.personalbuddyback.domain.GuestBookVO;
import com.app.personalbuddyback.domain.GuestBookViewDTO;
import com.app.personalbuddyback.domain.MemberVO;
import com.app.personalbuddyback.repository.AlertDAO;
import com.app.personalbuddyback.repository.GuestBookDAO;
import com.app.personalbuddyback.repository.MemberDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class GuestBookServiceImpl implements GuestBookService {

    private final GuestBookDAO guestBookDAO;
    private final AlertDAO alertDAO;

    @Override
    public void writeGuestBook(GuestBookVO guestBookVO) {
        if (guestBookVO.getOwnerMemberId() == guestBookVO.getWriterMemberId()) {
            guestBookDAO.save(guestBookVO);
        } else {
            AlertVO alertVO = new AlertVO();
            alertVO.setAlertType("guestbook");
            alertVO.setAlertMessage("님이 방명록을 남겼습니다");
            alertVO.setAlertParam(guestBookVO.getOwnerMemberId());
            alertVO.setReceiverMemberId(guestBookVO.getOwnerMemberId());
            alertVO.setSenderMemberId(guestBookVO.getWriterMemberId());
            alertDAO.save(alertVO);
            guestBookDAO.save(guestBookVO);
        }
    }

    @Override
    public List<GuestBookVO> getAllGuestBooksByMemberId(Long ownerMemberId) {
        return guestBookDAO.findAll(ownerMemberId);
    }

    @Override
    public List<GuestBookViewDTO> getGuestBooksOnePageByMemberIdAndPage(Long ownerMemberId, Integer page) {
        Map<String, Object> map = new HashMap<>();
        map.put("ownerMemberId", ownerMemberId);
        map.put("page", page);
        return guestBookDAO.findOnePage(map);
    }

    @Override
    public void deleteGuestBookById(Long id) {
        guestBookDAO.delete(id);
    }
}
