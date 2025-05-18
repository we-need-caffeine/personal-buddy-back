package com.app.personalbuddyback.repository;

import com.app.personalbuddyback.domain.ItemVO;
import com.app.personalbuddyback.domain.TreeCustomizingVO;
import com.app.personalbuddyback.domain.TreeVO;
import com.app.personalbuddyback.domain.TreeViewDTO;
import com.app.personalbuddyback.mapper.MyTreeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyTreeDAO {
    private final MyTreeMapper myTreeMapper;

    // 멤버 - 성장나무 연결 테이블 추가 (회원 가입 시 최초 추가)
    public void saveMemberTree(TreeVO treeVO){
        myTreeMapper.insertMemberTree(treeVO);
    }

    // 아이템 구매 시, 멤버의 나무에 추가하기
    public void saveTreeCustomizing(TreeCustomizingVO treeCustomizingVO){
        myTreeMapper.insertTreeCustomizing(treeCustomizingVO);
    }

    // 멤버의 나무 아이디 조회 (멤버의 아이디를 통해서)
    public Long findTreeIdByMemberId(Long memberId){
        return myTreeMapper.selectTreeIdByMemberId(memberId);
    }

    // 멤버의 성장나무 전체 아이템 리스트 조회
    public List<TreeViewDTO> findAllTreeCustomizing(Long memberId){
        return myTreeMapper.selectAllTreeCustomizingByMemberId(memberId);
    }

    // 멤버의 전시된 나무 아이템 목록 조회
    public List<TreeViewDTO> findAppliedTreeCustomizing(Long memberId){
        return myTreeMapper.selectAppliedTreeCustomizingByMemberId(memberId);
    }

    // 멤버 성장나무 아이템 수정
    public void updateTreeCustomizing(TreeViewDTO TreeViewDTO){
        myTreeMapper.updateTreeCustomizing(TreeViewDTO);
    }

    // 멤버 성장나무 목록들 삭제
    public void deleteTreeCustomizing(Long treeId){
        myTreeMapper.deleteTreeCustomizing(treeId);
    }

    // 멤버 성장나무 삭제
    public void deleteTree(Long memberId){
        myTreeMapper.deleteTree(memberId);
    }
}
