package com.app.personalbuddyback.repository;

import com.app.personalbuddyback.domain.FollowVO;
import com.app.personalbuddyback.domain.MemberVO;
import com.app.personalbuddyback.domain.ProfileCardDTO;
import com.app.personalbuddyback.mapper.FollowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    //    맞팔된 유저의 정보
    public List<MemberVO> findMutualFollows(Long memberId) {
        return followMapper.selectMutualFollows(memberId);
    }
    //    해당멤버의 프로필 카드와 함께 팔로우 여부와 즐겨찾기 여부를 가져온다
    public Optional<ProfileCardDTO> findProfileCard(Map<String, Object> map) {
        return followMapper.selectProfileCard(map);
    }
    //    즐겨찾기 토글
    public void update(FollowVO followVO) {
        followMapper.update(followVO);
    }
    //    팔로우 취소
    public void delete(FollowVO followVO) {
        followMapper.delete(followVO);
    }
    //    회원탈퇴용 팔로우 전체삭제
    public void deleteAllByWithdraw(Long memberId) {
        followMapper.deleteAllByWithdraw(memberId);
    }
}
