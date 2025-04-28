package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ScheduleMemberGroupMember {
    private Long  id;
    private Long  memberId;
    private Long  scheduleMemberGroupId;
}
