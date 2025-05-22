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
//    테스트용이고 실무용으로는 서비스에서 alertDAO 받아서 전송
    @Operation(summary = "알림 전송", description = "해당 유저에게 알림을 보내는 API")
    @ApiResponse(responseCode = "200", description = "알림 전송 성공")
    @PostMapping("/alert/send")
    public void sendAlert(@RequestBody AlertVO alertVO) {
//        alertType : (정의된 컬럼의 타입으로 전송)
//        alertMessage : 알림의 메세지
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
            in = ParameterIn.QUERY,
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
    @GetMapping("/alert/list")
    public List<AlertViewDTO> listAlert(@RequestParam Long memberId, @RequestParam(required = false) String alertType) {
        return alertService.getAlerts(memberId, alertType);
    }

//    해당 멤버가 읽지않은 알림을 전체 조회하는 API
    @Parameter(
            name = "receiverMemberId",
            description = "로그인된 멤버의 아이디",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @ApiResponse(responseCode = "200", description = "읽지않은 알람의 수를 조회 성공")
    @GetMapping("/alert/count/{receiverMemberId}")
    public Integer getCountNotReadAlerts(@PathVariable Long receiverMemberId) {
        return alertService.getNotReadAlerts(receiverMemberId);
    }

//    알림을 읽음처리하는 API
    @Operation(summary = "알림 읽음처리", description = "해당 유저에게 달린 모든 알림을 읽음 처리하는 API")
    @Parameter(
            name = "memberId",
            description = "로그인한 유저의 아이디",
            in = ParameterIn.PATH,
            schema = @Schema(type = "number"),
            required = true
    )
    @ApiResponse(responseCode = "200", description = "알림 읽음처리 성공")
    @PutMapping("/alert/read/{memberId}")
    public void readingAlert(@PathVariable Long memberId) {
        alertService.updateAlertReadByMemberId(memberId);
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
