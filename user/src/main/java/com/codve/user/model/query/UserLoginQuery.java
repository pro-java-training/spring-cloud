package com.codve.user.model.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author admin
 * @date 2019/12/9 11:34
 */
@Data
public class UserLoginQuery {

    @ApiModelProperty("用户名")
    @NotBlank
    private String name;

    @ApiModelProperty("密码")
    @NotBlank
    private String password;
}
