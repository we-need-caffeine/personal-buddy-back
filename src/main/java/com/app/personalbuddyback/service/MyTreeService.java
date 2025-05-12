package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.ItemVO;
import com.app.personalbuddyback.domain.TreeCustomizingVO;
import com.app.personalbuddyback.domain.TreeVO;
import com.app.personalbuddyback.domain.TreeViewDTO;

import java.util.List;

public interface MyTreeService {

    public void createMemberTree(TreeVO treeVO);

    public List<TreeViewDTO> getMemberTreeItems(Long memberId);

    public void editMemberTreeItem(TreeCustomizingVO treeCustomizingVO);

    public void deleteMemberTreeItem(Long id);
}
