package com.codve.user.model.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @date 2019/11/26 18:39
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserQuery extends PageQuery{

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("生日起始时间")
    @Min(value = 1)
    private Long start;

    @ApiModelProperty("生日结束时间")
    @Min(value = 1)
    private Long end;

    @ApiModelProperty("用户 id 列表")
    private List<@Min(value = 1) Long> userIds = new ArrayList<>();

    @ApiModelProperty(value = "排序方式", notes = "1 升序, 2 降序")
    @Range(min = 1, max = 4)
    private Integer orderBy;
}
