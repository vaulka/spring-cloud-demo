package com.pongsky.cloud.entity.user.dto;

import com.pongsky.cloud.validator.SearchGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

/**
 * @author pengsenhao
 * @create 2021-02-11
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class SearchUserDto {

    /**
     * 搜索（用户名、名称、手机号）
     */
    @Length(max = 30, groups = {SearchGroup.class})
    private String search;

    public void setSearch(String search) {
        this.search = StringUtils.isNotBlank(search) ? search : null;
    }

}
