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

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleDAO scheduleDAO;
    private final CalendarDAO calendarDAO;

    // 일정 등록
    @Override
    public void registerSchedule(ScheduleSaveDTO scheduleSaveDTO) {
        ScheduleMemberGroupVO scheduleMemberGroupVO = new ScheduleMemberGroupVO();
        scheduleDAO.saveScheduleMemberGroup(scheduleMemberGroupVO);
        Long groupId = scheduleMemberGroupVO.getId();
        scheduleSaveDTO.setScheduleMemberGroupId(groupId);

        List<Long> memberIds = scheduleSaveDTO.getMemberIds();
        if (memberIds != null && !memberIds.isEmpty()) {
            for(Long memberId : memberIds) {
                ScheduleGroupMemberVO scheduleGroupMemberVO = new ScheduleGroupMemberVO();
                scheduleGroupMemberVO.setMemberId(memberId);
                scheduleGroupMemberVO.setScheduleMemberGroupId(groupId);
                scheduleDAO.saveScheduleGroupMember(scheduleGroupMemberVO);
            }
        }
        scheduleDAO.saveSchedule(scheduleSaveDTO);
    }

    // 색상조회
    @Override
    public List<String> getColors() {
        return  List.of("#01CD74", "#4AB3F7", "#F35F8C", "#B38BDC", "#3FC2C8");
    }

    // 일정 멤버 등록
    @Override
    public void addScheduleMember(ScheduleGroupMemberVO scheduleGroupMemberVO) {
        scheduleDAO.saveScheduleGroupMember(scheduleGroupMemberVO);
    }

    // 일정 멤버 조회
    @Override
    public List<MemberVO> getScheduleMembers(Long scheduleMemberGroupId) {
        return scheduleDAO.findAllScheduleGroupMembersByScheduleMemberGroupId(scheduleMemberGroupId);
    }

    // 추가 가능한 공유 일정 멤버 조회
    @Override
    @Transactional
    public List<MemberVO> getAvailableScheduleMembers(Long calendarId, Long scheduleMemberGroupId) {
        List<MemberVO> calendarMembers = calendarDAO.findAllCalendarMembersByCalendarId(calendarId);
        List<MemberVO> scheduleMembers = scheduleDAO.findAllScheduleGroupMembersByScheduleMemberGroupId(scheduleMemberGroupId);

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
        return scheduleDAO.findAllSchedulesByMemberId(memberId);
    }

    // 일정 단일 조회
    @Override
    public Optional<ScheduleViewDTO> getSchedule(Long scheduleId) {
        return scheduleDAO.findSchedule(scheduleId);
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

        Long groupId = scheduleSaveDTO.getScheduleMemberGroupId();

        if (groupId != null) {
            // 기존 공유 멤버 제거
            scheduleDAO.deleteAllScheduleGroupMembersByScheduleMemberGroupId(groupId);

            List<Long> memberIds = scheduleSaveDTO.getMemberIds();
            // 새 공유 멤버 추가
            if (memberIds != null && !memberIds.isEmpty()) {
                for (Long memberId : memberIds) {
                    ScheduleGroupMemberVO memberVO = new ScheduleGroupMemberVO();
                    memberVO.setMemberId(memberId);
                    memberVO.setScheduleMemberGroupId(groupId);
                    scheduleDAO.saveScheduleGroupMember(memberVO);
                }
            }
        }
    }

    // 일정 멤버 추방
    @Override
    public void expelScheduleMember(Long ScheduleGroupMemberId) {
        scheduleDAO.deleteScheduleGroupMember(ScheduleGroupMemberId);
    }

    // 일정 삭제
    @Override
    @Transactional
    public void deleteSchedule(Long scheduleId) {
        scheduleDAO.deleteAllScheduleGroupMembersByScheduleId(scheduleId);
        scheduleDAO.deleteScheduleMemberGroupByScheduleId(scheduleId);
        scheduleDAO.deleteSchedule(scheduleId);
    }
}
