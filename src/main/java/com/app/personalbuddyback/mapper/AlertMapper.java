package com.app.personalbuddyback.mapper;

import com.app.personalbuddyback.domain.AlertVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AlertMapper {
//    알림 발생
    public void insert(AlertVO alertVO);
//    알림 리스트
    public List<AlertVO> selectAll();
//    알림 - 읽음 여부
    public void update(Long id);
//    알림 삭제
    public void delete(Long id);
//    알림 전체 삭제
    public void deleteAll(Long receiverMemberId);
}
