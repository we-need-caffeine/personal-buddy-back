package com.app.personalbuddyback.repository;

import com.app.personalbuddyback.domain.FollowVO;
import com.app.personalbuddyback.mapper.FollowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FollowDAO {
    private final FollowMapper followMapper;

    //    팔로워 추가
    public void save(FollowVO followVO) {
        followMapper.insert(followVO);
    }
    //    팔로워 리스트 포기
    public List<FollowVO> findFollower(Long followerMemberId) {
        return followMapper.selectFollower(followerMemberId);
    }
    //    팔로우 리스트 보기
    public List<FollowVO> findFollowing(Long followingMemberId) {
        return followMapper.selectFollowing(followingMemberId);
    }
    //    내가 즐겨찾기 한 유저의 리스트
    public List<FollowVO> findFavorite(Long favoriteMemberId) {
        return followMapper.selectFavorite(favoriteMemberId);
    }
    //    즐겨찾기 토글
    public void update(FollowVO followVO) {
        followMapper.update(followVO);
    }
    //    팔로우 취소
    public void delete(FollowVO followVO) {
        followMapper.delete(followVO);
    }
}
