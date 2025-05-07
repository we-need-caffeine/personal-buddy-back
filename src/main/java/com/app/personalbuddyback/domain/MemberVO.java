package com.app.personalbuddyback.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Data
@Component
@Schema(description = "회원 정보")
public class MemberVO implements Serializable {
    @Schema(description = "회원 번호", required = true, example = "14")
    private Long id;
    @Schema(description = "회원 이메일", required = true, example = "test1234@gmail.com")
    private String memberEmail;
    @Schema(description = "회원 비밀번호", required = true, example = "비공개")
    private String memberPassword;
    @Schema(description = "회원 이름", required = true, example = "홍길동")
    private String memberName;
    @Schema(description = "회원 생일", required = true, example = "1998-01-01")
    private Date memberBirth;
    @Schema(description = "회원 성별", required = true, example = "남자")
    private String memberGender;
    @Schema(description = "회원 전화번호", required = true, example = "010-1234-5678")
    private String memberPhone;
    @Schema(description = "회원 닉네임", required = true, example = "홍길동동")
    private String memberNickName;
    @Schema(description = "회원 상태 메세지", required = true, example = "홍길동 입니다.")
    private String memberStatusMessage;
    @Schema(description = "회원 프로필 이미지 이름", required = true, example = "hong.jpg")
    private String memberImgName;
    @Schema(description = "회원 프로필 이미지 경로", required = true, example = "/images/")
    private String memberImgPath;
    @Schema(description = "회원 포인트", required = true, example = "0")
    private int memberPoint;
    @Schema(hidden = true)
    private int memberAdmin;
    @Schema(hidden = true)
    private Date memberCreateDate;
    @Schema(hidden = true)
    private int memberTermServiceAgree;
    @Schema(hidden = true)
    private int memberTermInformationAgree;
    @Schema(hidden = true)
    private int memberTermLocationAgree;
    @Schema(hidden = true)
    private int memberTermPromotionAgree;
    @Schema(hidden = true)
    private String memberProvider;
}
