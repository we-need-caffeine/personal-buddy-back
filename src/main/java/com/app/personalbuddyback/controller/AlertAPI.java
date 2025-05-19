package com.app.personalbuddyback.controller;


import com.app.personalbuddyback.domain.AlertVO;
import com.app.personalbuddyback.domain.AlertViewDTO;
import com.app.personalbuddyback.service.AlertService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/alerts/api/*")
public class AlertAPI {

//    알림 서비스 생성자 주입
    private final AlertService alertService;


//    알림을 보내는 API
    @Operation(summary = "알림 전송", description = "해당 유저에게 알림을 보내는 API")
    @ApiResponse(responseCode = "200", description = "알림 전송 성공")
    @PostMapping("/alert/send")
    public void sendAlert(@RequestBody AlertVO alertVO) {
//        alertType : (정의된 컬럼의 타입으로 전송)
//        alertParam : 해당 알림의 타입에 따른 id값 없으면 null
//        receiverMemberId : 해당 알림을 받는 유저
//        senderMemberId : 해당 알림을 보내는 유저(운영자 혹은 이벤트면 Null)
        alertService.registerAlert(alertVO);
    }


//    해당 유저의 모든 알림을 조회하는 API
    @Operation(summary = "전체 알림 조회", description = "해당 유저의 전체 알림을 조회하는 API")
    @Parameter(
            name = "memberId",
            description = "멤버 아이디",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @Parameter(
            name = "alertType",
            description = "알림 타입",
            schema = @Schema(type = "string"),
            in = ParameterIn.QUERY,
            required = false
    )
    @ApiResponse(responseCode = "200", description = "알림 전체 조회 성공")
    @GetMapping("/alert/list/{memberId}")
    public ResponseEntity<Map<String,Object>> listAlert(@PathVariable Long memberId, @RequestParam(required = false) String alertType) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> response = new HashMap<>();
        List<AlertViewDTO> alerts = new ArrayList<>();

        map.put("receiverMemberId", memberId);
        map.put("alertType", alertType);
        alerts = alertService.getAlerts(map);

        if (!alerts.isEmpty()) {
            response.put("message", "알림 조회 성공");
            response.put("data", alerts);
            return ResponseEntity.ok().body(response);
        } else {
            response.put("message", "알림 조회 실패");
            response.put("data", alerts);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }


//    알림을 읽음처리하는 API
    @Operation(summary = "알림 읽음처리", description = "클릭한 알림의 ID를 받아 읽음처리하는 API")
    @Parameter(
            name = "id",
            description = "알림 아이디",
            in = ParameterIn.PATH,
            schema = @Schema(type = "number"),
            required = true
    )
    @ApiResponse(responseCode = "200", description = "알림 읽음처리 성공")
    @PutMapping("/alert/read/{id}")
    public void readingAlert(@PathVariable Long id) {
        alertService.updateAlertReadById(id);
    }

//    알림을 삭제하는 API
    @Operation(summary = "알림 삭제", description = "알림의 ID를 받아 삭제하는 API")
    @Parameter(
            name = "id",
            description = "알림 아이디",
            in = ParameterIn.PATH,
            schema = @Schema(type = "number"),
            required = true
    )
    @ApiResponse(responseCode = "200", description = "알림 삭제  성공")
    @DeleteMapping("/alert/delete/{id}")
    public void deleteAlert(@PathVariable Long id) {
        alertService.deleteAlertById(id);
    }

//    알림을 전체 삭제하는 API
    @Operation(summary = "알림 전체 삭제", description = "유저의 ID를 받아 알림을 전체 삭제하는 API")
    @Parameter(
            name = "memberId",
            description = "멤버 아이디",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @ApiResponse(responseCode = "200", description = "알림 삭제 성공")
    @DeleteMapping("/alert/delete-all/{memberId}")
    public void deleteAllAlert(@PathVariable Long memberId) {
        alertService.deleteAllAlertByReceiverMemberId(memberId);
    }
}
