package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class InterestRecommendViewDTO {
//    일정 (TBL_SCHEDULE)
    private Long scheduleId;
    private String scheduleCategory;

//    더미 데이터 (TBL_INTEREST_DATA)
    private String interestDataCategory;
    private String interestDataType;
    private String interestDataDetail;
    private String interestDataTitle;
    private String interestDataDescription;
    private String interestDataImgPath;
    private String interestDataImgName;
}
