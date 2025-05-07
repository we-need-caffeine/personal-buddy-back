package com.app.personalbuddyback.mapper;

import com.app.personalbuddyback.domain.FollowVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FollowMapper {
//    팔로워 추가
    public void insert(FollowVO followVO);
//    나를 구독한 유저
    public List<FollowVO> selectFollower(Long followerMemberId);
//    내가 구독한 유저
    public List<FollowVO> selectFollowing(Long followingMemberId);
//    즐겨찾기 한 유저의 리스트
    public List<FollowVO> selectFavorite(Long favoriteMemberId);
//    즐겨찾기 토글
    public void update(FollowVO followVO);
//    팔로우 취소
    public void delete(FollowVO followVO);
}
