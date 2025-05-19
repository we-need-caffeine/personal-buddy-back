package com.app.personalbuddyback.mapper;

import com.app.personalbuddyback.domain.FollowVO;
import com.app.personalbuddyback.domain.MemberVO;
import com.app.personalbuddyback.domain.ProfileCardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
//    맞팔 여부 확인
    public List<MemberVO> selectMutualFollows(Long memberId);
//    해당멤버의 프로필 카드와 함께 팔로우 여부와 즐겨찾기 여부를 가져온다
    public Optional<ProfileCardDTO> selectProfileCard(Map<String, Object> map);
//    즐겨찾기 토글
    public void update(FollowVO followVO);
//    팔로우 취소
    public void delete(FollowVO followVO);
//    회원탈퇴용 팔로우 전체 삭제
    public void deleteAllByWithdraw(Long memberId);
}
