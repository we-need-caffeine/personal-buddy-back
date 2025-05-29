// 이벤트 댓글

package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class EventCommentViewDTO {
//    private Long id;
//    private String eventCommentDescription; // 댓글 본문
//    private String commenterNickname; // 작성자 닉네임
//    private String createdDate; // 작성일
//    private int likeCount; // 좋아요 수

        private Long id;
        private String eventCommentDescription;
        private String eventCommentCreateDate;
        private Long memberId;

        private String memberNickName;
        private String memberImgPath;
        private String memberImgName;

        private int eventCommentLikeCount;


}
