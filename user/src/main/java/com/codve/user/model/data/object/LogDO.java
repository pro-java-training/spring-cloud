package com.codve.user.model.data.object;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @author admin
 * @date 2019/12/17 13:54
 */
@Data
public class LogDO {

    @Id
    private String id;

    private Long userId;

    private Integer deviceType;

    private String deviceCode;

    private Integer appType;

    private String ip;

    private String token;

    private Long createTime;

    private Long expireTime;
}
