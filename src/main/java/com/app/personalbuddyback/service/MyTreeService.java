package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.*;

import java.util.List;
import java.util.Map;

public interface MyTreeService {
    // 멤버 - 성장나무 연결 테이블 추가 (회원 가입 시 최초 추가)
    public void registerMemberTree(TreeVO treeVO);

    // 멤버의 성장나무 전체 아이템 리스트 조회
    public List<TreeItemListDTO> getAllTreeCustomizing(Map<String, Object> params);

    // 멤버의 전시된 나무 아이템 목록 조회
    public List<TreeViewDTO> getAppliedTreeCustomizing(Long memberId);

    // 멤버 성장나무 아이템 수정
    public void updateTreeCustomizing(TreeViewDTO TreeViewDTO);

    // 멤버 성장나무 목록들 삭제
    public void deleteTreeCustomizing(Long treeId);

    // 멤버 성장나무 삭제
    public void deleteTree(Long memberId);
}
