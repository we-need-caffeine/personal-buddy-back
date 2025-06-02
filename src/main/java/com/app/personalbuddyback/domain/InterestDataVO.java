package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class InterestDataVO {
    private Long id;
    private String interestDataCategory;
    private String interestDataType;
    private String interestDataSection;
    private String interestDataValue;
    private String interestDataContent;
    private String interestDataImgName;
    private String interestDataImgPath;
    private String interestDataLink;
    private Long interestDetailId;
}
