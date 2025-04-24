package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class InterestDataVO {
    private Long id;
    private String interestDataCategory;
    private String interestDataType;
    private String interestDataDetail;
    private String interestDataTitle;
    private String interestDataDescription;
    private String interestDataImgPath;
    private String interestDataImgName;
    private Long interestDetailId;
}
