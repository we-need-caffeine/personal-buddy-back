package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class BoardcommentDetailDTO {
    private BoardViewDTO board; // 게시글 상세 정보
    private List<BoardCommentViewDTO> comments; // 일반 댓글 목록
    private List<BoardCommentViewDTO> bestComments; // 베스트 댓글 목록

}
