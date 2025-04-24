package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class BoardImgVO {
    private Long id;
    private String boardImgPath;
    private String boardImgName;
    private Long boardId;
}
