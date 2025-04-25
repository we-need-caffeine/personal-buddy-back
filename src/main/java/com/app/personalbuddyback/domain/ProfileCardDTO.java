package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class ProfileCardDTO {
    private Long id;
    private String memberNickname;
    private String memberStatusMessage;
    private String memberImgName;
    private String memberImgPath;
    private Long followerCount;
    private Long followingCount;
    private int favorite;
}
