package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.TreeCustomizingVO;
import com.app.personalbuddyback.domain.TreeVO;
import com.app.personalbuddyback.domain.TreeViewDTO;
import com.app.personalbuddyback.repository.MyTreeDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class MyTreeServiceImpl implements MyTreeService {
    private final MyTreeDAO myTreeDAO;

    @Override
    public void createMemberTree(TreeVO treeVO) {
        myTreeDAO.saveMemberTree(treeVO);
    }

    @Override
    public void addMemberTreeItem(TreeCustomizingVO treeCustomizingVO) {
        myTreeDAO.saveTreeCustomizing(treeCustomizingVO);
    }

    @Override
    public List<TreeViewDTO> getMemberTreeItems(Long memberId) {
        return myTreeDAO.findTreeViews(memberId);
    }

    @Override
    public void editMemberTreeItem(TreeCustomizingVO treeCustomizingVO) {
        myTreeDAO.editTreeCustomizing(treeCustomizingVO);
    }

    @Override
    public void deleteMemberTreeItem(Long id) {
        myTreeDAO.deleteTreeCustomizing(id);
    }
}
