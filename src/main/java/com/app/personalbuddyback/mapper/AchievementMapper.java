package com.app.personalbuddyback.mapper;

import com.app.personalbuddyback.domain.AchievementViewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AchievementMapper {
    public void insertAchievement();

    public void insertAchievementComplete();

    public void insertMemberAchievement();

    public List<AchievementViewDTO> selectAchievementsByMemberId(Long memberId);

    public void updateAchievementComplete();
}
