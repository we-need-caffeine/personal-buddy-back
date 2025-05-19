package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.TreeVO;
import com.app.personalbuddyback.domain.TreeViewDTO;
import com.app.personalbuddyback.repository.MyTreeDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class MyTreeServiceImpl implements MyTreeService {
    private final MyTreeDAO myTreeDAO;

    @Override
    public void registerMemberTree(TreeVO treeVO) {
        myTreeDAO.saveMemberTree(treeVO);
    }

    @Override
    public List<TreeViewDTO> getAllTreeCustomizing(Long memberId) {
        return myTreeDAO.findAllTreeCustomizing(memberId);
    }

    @Override
    public List<TreeViewDTO> getAppliedTreeCustomizing(Long memberId) {
        return myTreeDAO.findAppliedTreeCustomizing(memberId);
    }

    @Override
    public void updateTreeCustomizing(TreeViewDTO TreeViewDTO) {
        myTreeDAO.updateTreeCustomizing(TreeViewDTO);
    }

    @Override
    public void deleteTreeCustomizing(Long treeId) {
        myTreeDAO.deleteTreeCustomizing(treeId);
    }

    @Override
    public void deleteTree(Long memberId) {
        myTreeDAO.deleteTree(memberId);
    }
}
