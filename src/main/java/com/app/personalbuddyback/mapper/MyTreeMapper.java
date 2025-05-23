package com.app.personalbuddyback.mapper;

import com.app.personalbuddyback.domain.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface MyTreeMapper {

    // 멤버 - 성장나무 연결 테이블 추가 (회원 가입 시 최초 추가)
    public void insertMemberTree(TreeVO treeVO);

    // 아이템 구매 시, 멤버의 나무에 추가하기
    public void insertTreeCustomizing(TreeCustomizingVO treeCustomizingVO);

    // 멤버의 나무 아이디 조회 (멤버의 아이디를 통해서)
    public Long selectTreeIdByMemberId(Long memberId);
    
    //멤버의 성장나무 전체 아이템 리스트 조회
    public List<TreeItemListDTO> selectAllTreeCustomizingByMemberId(Map<String, Object> params);

    // 멤버의 전시된 나무 아이템 목록 조회
    public List<TreeViewDTO> selectAppliedTreeCustomizingByMemberId(Long memberId);

    // 멤버 성장나무 아이템 수정
    public void updateTreeCustomizing(TreeViewDTO treeViewDTO);

    // 멤버 성장나무 목록들 삭제
    public void deleteTreeCustomizing(Long treeId);

    // 멤버 성장나무 삭제
    public void deleteTree(Long memberId);

}
