package com.codve.user.model.query;

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

    private String name;

    @Min(value = 1)
    private Long start;

    @Min(value = 1)
    private Long end;

    private List<@Min(value = 1) Long> userIds = new ArrayList<>();

    @Range(min = 1, max = 4)
    private Integer orderBy;
}
