// 이벤트 상세 페이지

package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class EventViewDTO {
    private Long id; // 이벤트 ID
    private String eventTitle; // 이벤트 제목
    private String eventDescription; // 이벤트 설명
    private int eventViews; // 조회수
    private int eventCommentCount; // 댓글수
    private String eventStartDate; // 시작날짜
    private String eventEndDate; // 종료 날짜
    private int eventGetPoint; // 참여시 받을 포인트
}
