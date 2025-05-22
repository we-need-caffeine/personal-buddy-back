package com.app.personalbuddyback.mapper;

import com.app.personalbuddyback.domain.AlertVO;
import com.app.personalbuddyback.domain.AlertViewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AlertMapper {
//    알림 발생
    public void insert(AlertVO alertVO);
//    알림 리스트
    public List<AlertViewDTO> selectAll(Map<String, Object> map);
//    읽지않은 알림 조회
    public Integer selectCount(Long receiverMemberId);
//    알림 - 읽음 여부
    public void update(Long memberId);
//    알림 삭제
    public void delete(Long id);
//    알림 전체 삭제
    public void deleteAll(Long receiverMemberId);
//    회원탈퇴용 알림 전체 삭제
    public void deleteAllByWithdraw(Long memberId);
}
