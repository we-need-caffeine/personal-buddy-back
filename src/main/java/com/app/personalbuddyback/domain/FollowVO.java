package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class FollowVO {
    private Long id;
    private int followFavorite;
    private Date followingDate;
    private Long followerMemberId;
    private Long followingMemberId;
}
