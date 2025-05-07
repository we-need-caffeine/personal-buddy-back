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

    public void saveTreeItem(ItemVO itemVO) {
        myTreeMapper.insertTreeItem(itemVO);
    }

    public void saveMemberTree(TreeVO treeVO){
        myTreeMapper.insertMemberTree(treeVO);
    }

    public void insertTreeCustomizing(TreeCustomizingVO treeCustomizingVO){
        myTreeMapper.insertTreeCustomizing(treeCustomizingVO);
    }

    public List<TreeViewDTO> findTreeViews(Long memberId){
        return myTreeMapper.selectTreeViewsByMemberId(memberId);
    }

    public void editTreeCustomizing(TreeCustomizingVO treeCustomizingVO){
        myTreeMapper.updateTreeCustomizing(treeCustomizingVO);
    }
}
