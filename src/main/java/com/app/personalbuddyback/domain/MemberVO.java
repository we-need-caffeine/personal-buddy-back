package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class MemberVO {
    private Long id;
    private String memberName;
    private String memberEmail;
    private String memberPassword;
    private String memberPhone;
    private Date memberBirth;
    private String memberGender;
    private String memberImgPath;
    private String memberImgName;
    private String memberNickName;
    private String memberStatusMessage;
    private int memberAdmin;
    private int memberTermServiceAgree;
    private int memberTermInformationAgree;
    private int memberTermLocationAgree;
    private int memberTermPromotionAgree;
    private int memberPoint;
    private Date memberCreateDate;
}
