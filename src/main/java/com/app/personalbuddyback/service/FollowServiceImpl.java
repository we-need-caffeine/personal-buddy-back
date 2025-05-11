package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.FollowVO;
import com.app.personalbuddyback.domain.MemberVO;
import com.app.personalbuddyback.repository.FollowDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

    private final FollowDAO followDAO;

    @Override
    public void followMember(FollowVO followVO) {
        followDAO.save(followVO);
    }

    @Override
    public List<FollowVO> getMyFollower(Long followerMemberId) {
        return followDAO.findFollower(followerMemberId);
    }

    @Override
    public List<FollowVO> getMyFollowing(Long followingMemberId) {
        return followDAO.findFollowing(followingMemberId);
    }

    @Override
    public List<FollowVO> getMyFavorite(Long favoriteMemberId) {
        return followDAO.findFavorite(favoriteMemberId);
    }

    @Override
    public List<MemberVO> getMutualFollows(Long memberId) {
        return followDAO.findMutualFollows(memberId);
    }

    @Override
    public void toggleFollowing(FollowVO followVO) {
        followDAO.update(followVO);
    }

    @Override
    public void unfollowMember(FollowVO followVO) {
        followDAO.delete(followVO);
    }
}
