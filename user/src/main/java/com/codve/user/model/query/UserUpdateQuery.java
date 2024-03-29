package com.codve.user.model.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

/**
 * @author admin
 * @date 2019/12/2 19:19
 */
@Data
public class UserUpdateQuery {

    @ApiModelProperty("用户名")
    @NotNull(message = "用户名不能为空")
    @Size(min = 2, max = 64, message = "用户名的长度为2 ~ 64个字符")
    private String name;

    @PositiveOrZero(message = "生日必须大于等于 0")
    @ApiModelProperty("生日")
    private Long birthday;
}
