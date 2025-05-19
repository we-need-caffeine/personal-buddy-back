package com.app.personalbuddyback.controller;

import com.app.personalbuddyback.domain.TreeVO;
import com.app.personalbuddyback.domain.TreeViewDTO;
import com.app.personalbuddyback.service.MyTreeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.HTTP;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/my-tree/api")
public class MyTreeAPI {
    private final MyTreeService myTreeService;

    @Operation(summary = "성장나무 - 회원 간 연결 추가", description = "회원 가입 시 최초로 한 번만 추가")
    @PostMapping("/tree/create")
    public void createTree(@RequestBody TreeVO treeVO) {
        myTreeService.registerMemberTree(treeVO);
    }

    @Operation(summary = "회원의 성장나무 아이템 전체 목록 조회", description = "아이템 목록으로 뿌려줄 데이터")
    @GetMapping("/tree/list/{memberId}")
    public ResponseEntity<Map<String, Object>> getAllTrees(@PathVariable Long memberId) {
        Map<String, Object> response = new HashMap<>();
        try{
            List<TreeViewDTO> memberTrees = myTreeService.getAllTreeCustomizing(memberId);
            response.put("result", true);
            response.put("trees", memberTrees);
            response.put("message", "회원 성장나무 조회 완료");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("result", false);
            response.put("message", "회원 성장나무 조회 실패");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(summary = "회원의 꾸며놓은 (전시된) 성장나무 목록", description = "회원이 꾸며놓은 성장나무 아이템 목록(전시된 데이터)을 보기 위한 데이터 조회")
    @GetMapping("/tree/list/applied/{memberId}")
    public ResponseEntity<Map<String, Object>> getAllAppliedTrees(@PathVariable Long memberId) {
        Map<String, Object> response = new HashMap<>();
        try{
            List<TreeViewDTO> memberAppliedTrees = myTreeService.getAppliedTreeCustomizing(memberId);
            response.put("result", true);
            response.put("trees", memberAppliedTrees);
            response.put("message", "회원의 커스텀 성장나무 조회 완료");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("result", false);
            response.put("message", "회원의 커스텀 성장나무 조회 실패");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(summary = "성장나무 꾸미기 기능 (위치 수정 및 적용 여부 변경)", description = "회원이 꾸민 성장나무 변경사항을 적용")
    @PutMapping("/tree/edit")
    public ResponseEntity<Map<String, Object>> editTreeCustomizing(@RequestBody List<TreeViewDTO> editingTrees) {
        Map<String, Object> response = new HashMap<>();
        try {
            for(TreeViewDTO editingTree : editingTrees){
                myTreeService.updateTreeCustomizing(editingTree);
            }

            response.put("result", true);
            response.put("message", "성장나무 변경사항 저장 완료");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("result", false);
            response.put("message", "성장나무 변경사항 저장 실패");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
