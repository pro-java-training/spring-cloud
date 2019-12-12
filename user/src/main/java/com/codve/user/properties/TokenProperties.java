package com.codve.user.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

/**
 * @author admin
 * @date 2019/12/10 19:27
 */
@ConfigurationProperties(prefix = "token")
public class TokenProperties {

    private String secret;

    private Duration expire;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Duration getExpire() {
        return expire;
    }

    public void setExpire(Duration expire) {
        this.expire = expire;
    }
}
