package com.app.personalbuddyback.mapper;

import com.app.personalbuddyback.domain.FollowVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FollowMapper {
//    팔로워 추가
    public void insert(FollowVO followVO);
//    팔로워 리스트 포기
    public List<FollowVO> selectFollower(Long followerMemberId);
//    팔로우 리스트 보기
    public List<FollowVO> selectFollow(Long followingMemberId);
//    즐겨찾기 토글
    public void update(FollowVO followVO);
//    팔로우 취소
    public void delete(FollowVO followVO);
}
