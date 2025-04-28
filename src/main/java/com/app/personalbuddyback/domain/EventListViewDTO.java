// 이벤트 메인 페이지

package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class EventListViewDTO {
    private Long id; // 이벤트 ID
    private String eventTitle; // 이벤트 제목
    private String eventDescription; // 이벤트 설명
    private String thumbnailUrl; // 썸네일 대표 이미지
    private String eventStartDate; // 시작날짜
    private String eventEndDate; // 종료 날짜
    private int eventViews; // 조회수
    private int joinCount; // 이벤트 참여자 수
    private int commentCount; // 댓글 수
    private int eventLikeCount; // 좋아요 수
    private int eventGetPoint; // 참여시 받을 포인트
};