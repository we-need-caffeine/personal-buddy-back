package com.app.personalbuddyback.controller;

import com.app.personalbuddyback.domain.*;
import com.app.personalbuddyback.service.MemberService;
import com.app.personalbuddyback.service.MyTreeService;
import com.app.personalbuddyback.service.PointShopService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/point-shop/api")
@Slf4j
public class PointShopAPI {

    private final PointShopService pointShopService;
    private final MemberService memberService;

    @Operation(summary = "아이템 추가", description = "관리자용 아이템 추가")
    @PostMapping("/item/create")
    public void createItem(@RequestBody ItemVO itemVO, @RequestBody Map<String, Object> imgFileData) {
        // 이미지 서비스를 통해서 파일을 업로드 후, 경로를 받아서 추가해야한다.
        String imgFilePath = imgFileData.get("filePath").toString();
        String imgFileName = imgFileData.get("fileName").toString();

        itemVO.setItemImgPath(imgFilePath);
        itemVO.setItemImgName(imgFileName);

        pointShopService.addItem(itemVO);
    }

    @Operation(summary = "아이템 항목 수정", description = "관리자용 아이템 수정")
    @PutMapping("/item/edit")
    public void editItem(@RequestBody ItemVO itemVO) {
        pointShopService.editItem(itemVO);
    }

    @Operation(summary = "아이템 항목 삭제", description = "관리자용 아이템 삭제")
    @DeleteMapping("/item/delete/{id}")
    public void deleteItem(@PathVariable Long id) {
        pointShopService.deleteItem(id);
    }

    @Operation(summary = "포인트 샵 아이템 전체 목록 조회", description = "관리자용 페이지에서 사용할 아이템 전체 목록 조회")
    @GetMapping("/item/list/all")
    public List<ItemVO> getAllItems(String itemType) {
        return pointShopService.getAllItems(itemType);
    }

    @Operation(summary = "개인화 된 포인트샵 조회 화면", description = "소유하고 있는 목록에 대한 내용을 포함한 포인트샵 전체 목록")
    @PostMapping("/item/list")
    public List<PointShopViewDTO> getMemberPointShopView(@RequestBody Map<String, Object> params){
        log.info("params:{}", params);
        List<PointShopViewDTO> items = pointShopService.getPointShopItems(params);
        log.info("items:{}", items);
        return items;
    }

    @Operation(summary = "아이템 구매", description = "사용자 아이템 구매")
    @PostMapping("/item/buy")
    public ResponseEntity<Map<String, Object>> buyItem(Map<String, Object> params) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<BuyingItemDTO> buyingItems = (List<BuyingItemDTO>) params.get("buyingItems");

            Long memberId = buyingItems.get(0).getMemberId();
            int memberPoint = memberService.getMemberInfoById(memberId).get().getMemberPoint();
            boolean deleteCart = (boolean) params.get("deleteCart");
            // boolean deleteCart = buyingItems.get(0).getId() != null;

            int totalPrice = (int) params.get("totalPrice");

            if(memberPoint < totalPrice){
                response.put("result", false);
                response.put("message", "포인트가 부족합니다.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            pointShopService.buyItem(buyingItems, deleteCart, totalPrice);
            response.put("result", true);
            response.put("message", "구매 완료");
        } catch (RuntimeException e) {
            response.put("result", false);
            response.put("message", "구매 실패");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "장바구니 추가", description = "아이템 장바구니에 담기")
    @PostMapping("/cart/item/add")
    public ResponseEntity<Map<String, Object>> addCart(@RequestBody List<BuyingItemDTO> items){
        Map<String, Object> response = new HashMap<>();
        try{
            pointShopService.addCart(items);
            response.put("result", true);
            response.put("message", "장바구니 추가 완료");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("result", false);
            response.put("message", "장바구니 추가 실패");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(summary = "장바구니 목록 조회", description = "회원의 장바구니 아이템 목록 조회")
    @GetMapping("/cart/item-list/{memberId}")
    public List<BuyingItemDTO> getCartItems(@PathVariable Long memberId){
        return pointShopService.getAllCartItems(memberId);
    }

    @Operation(summary = "장바구니 목록 수정", description = "회원의 장바구니 아이템 수정 (수량 수정)")
    @PutMapping("/cart/item/edit")
    public void editCartItem(@RequestBody BuyingItemDTO cartItem){
        Long cartId = cartItem.getId();
        CartVO editingItem = pointShopService.getCartItem(cartId).get();
        pointShopService.editCartItem(editingItem);
    }

    @Operation(summary = "장바구니 아이템 삭제", description = "회원의 장바구니 아이템 수정 (삭제)")
    @DeleteMapping("/cart/item/delete")
    public ResponseEntity<Map<String, Object>> deleteCartItem(@RequestBody BuyingItemDTO cartItem){
        Map<String, Object> response = new HashMap<>();

        Long cartId = cartItem.getId();
        String itemName = cartItem.getItemName();
        pointShopService.deleteCartItem(cartId);

        response.put("result", true);
        response.put("message", "아이템 " + itemName + "이(가) 장바구니에서 제거되었습니다.");
        return ResponseEntity.ok(response);
    }
}
