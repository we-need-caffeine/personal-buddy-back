package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.TreeItemListDTO;
import com.app.personalbuddyback.domain.TreeVO;
import com.app.personalbuddyback.domain.TreeViewDTO;
import com.app.personalbuddyback.repository.MyTreeDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public List<TreeItemListDTO> getAllTreeCustomizing(Map<String, Object> params) {
        return myTreeDAO.findAllTreeCustomizing(params);
    }

    @Override
    public List<TreeViewDTO> getAppliedTreeCustomizing(Long memberId) {
        return myTreeDAO.findAppliedTreeCustomizing(memberId);
    }

    @Override
    public Optional<TreeViewDTO> getNotAppliedItemId(Map<String, Object> params){
        return myTreeDAO.findNotAppliedItem(params);
    }

    @Override
    public Optional<TreeViewDTO> getAppliedItemId(Map<String, Object> params){
        return myTreeDAO.findAppliedItem(params);
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
