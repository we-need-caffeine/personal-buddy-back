package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.*;
import com.app.personalbuddyback.repository.CalendarDAO;
import com.app.personalbuddyback.repository.ScheduleDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleDAO scheduleDAO;
    private final CalendarDAO calendarDAO;

    // 일정 등록
    @Override
    public void registerSchedule(ScheduleSaveDTO scheduleSaveDTO) {
        // 1. 먼저 스케줄을 등록해서 scheduleId를 받아옴
        scheduleDAO.saveSchedule(scheduleSaveDTO);
        Long scheduleId = scheduleSaveDTO.getId();  // selectKey로 설정된 ID

        // 2. 스케줄 멤버 등록
        List<Long> memberIds = scheduleSaveDTO.getMemberIds();
        if (memberIds != null && !memberIds.isEmpty()) {
            for (Long memberId : memberIds) {
                ScheduleMemberVO scheduleMemberVO = new ScheduleMemberVO();
                scheduleMemberVO.setMemberId(memberId);
                scheduleMemberVO.setScheduleId(scheduleId);

                scheduleDAO.saveScheduleMember(scheduleMemberVO);
            }
        }
    }

    // 색상조회
    @Override
    public List<String> getColors() {
        return  List.of("#01CD74", "#4AB3F7", "#F35F8C", "#B38BDC", "#3FC2C8");
    }

    // 일정 멤버 등록
    @Override
    public void addScheduleMember(ScheduleMemberVO scheduleMemberVO) {
        scheduleDAO.saveScheduleMember(scheduleMemberVO);
    }

    // 일정 멤버 조회
    @Override
    public List<MemberVO> getScheduleMembers(Long scheduleId) {
        return scheduleDAO.findAllScheduleMembersByScheduleId(scheduleId);
    }

    // 추가 가능한 공유 일정 멤버 조회
    @Override
    @Transactional
    public List<MemberVO> getAvailableScheduleMembers(Long calendarId, Long scheduleId) {
        List<MemberVO> calendarMembers = calendarDAO.findAllCalendarMembersByCalendarId(calendarId);
        List<MemberVO> scheduleMembers = scheduleDAO.findAllScheduleMembersByScheduleId(scheduleId);

        return calendarMembers.stream()
                .filter(calendarMember -> {
                    boolean exists = false;
                    for (MemberVO scheduleMember : scheduleMembers) {
                        if (scheduleMember.getId().equals(calendarMember.getId())) {
                            exists = true;
                            break;
                        }
                    }
                    return !exists;
                })
                .toList();
    }

    // 일정 전체 조회
    @Override
    public List<ScheduleVO> getSchedules(Long memberId) {
        return scheduleDAO.findAllSchedulesByCalendarId(memberId);
    }

    // 일정 단일 조회
    @Override
    public Optional<ScheduleViewDTO> getSchedule(Long scheduleId) {
        Optional<ScheduleViewDTO> optional = scheduleDAO.findSchedule(scheduleId);

        if (optional.isPresent()) {
            ScheduleViewDTO scheduleViewDTO = optional.get();

            List<MemberVO> members = scheduleDAO.findAllScheduleMembersByScheduleId(scheduleId);
            scheduleViewDTO.setMembers(members);


            return Optional.of(scheduleViewDTO); // 다시 감싸서 반환
        }

        return Optional.empty(); // 없으면 빈 Optional 반환
    }

    @Override
    // 일정 대분류 전체 조회
    public List<ScheduleCategoryVO> getScheduleCategories() {
        return scheduleDAO.findAllCategories();
    }

    // 일정 소분류 전체 조회
    public List<ScheduleSubCategoryVO> getScheduleSubCategories(Long categoryId) {
        return scheduleDAO.findAllSubCategories(categoryId);
    }

    // 일정 수정
    @Override
    @Transactional
    public void modifySchedule(ScheduleSaveDTO scheduleSaveDTO) {
        // 일정 정보 수정

        scheduleDAO.updateSchedule(scheduleSaveDTO);

//        if (groupId != null) {
//            // 기존 공유 멤버 제거
//            scheduleDAO.deleteAllScheduleGroupMembersByScheduleMemberGroupId(groupId);
//
//            List<Long> memberIds = scheduleSaveDTO.getMemberIds();
//            // 새 공유 멤버 추가
//            if (memberIds != null && !memberIds.isEmpty()) {
//                for (Long memberId : memberIds) {
//                    ScheduleMemberVO memberVO = new ScheduleMemberVO();
//                    memberVO.setMemberId(memberId);
//                    memberVO.setScheduleMemberGroupId(groupId);
//                    scheduleDAO.saveScheduleGroupMember(memberVO);
//                }
//            }
//        }
    }

    // 일정 멤버 추방
    @Override
    public void expelScheduleMember(Long ScheduleGroupMemberId) {
        scheduleDAO.deleteScheduleMember(ScheduleGroupMemberId);
    }

    // 일정 삭제
    @Override
    @Transactional
    public void deleteSchedule(Long scheduleId) {
        scheduleDAO.deleteAllScheduleMembersByScheduleId(scheduleId);
        scheduleDAO.deleteSchedule(scheduleId);
    }
}
