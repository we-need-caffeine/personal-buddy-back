package com.app.personalbuddyback.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sms")
@Data
public class SmsConfig {
    private String apiKey;
    private String apiSecret;
    private String from;
}
