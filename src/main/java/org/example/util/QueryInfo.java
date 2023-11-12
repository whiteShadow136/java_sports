package org.example.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:org.example.util
 * @Date:2023/8/19
 * @Author:谢锦创
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryInfo {
    /**
     * 第几页
     */
    private Integer pageNumber;

    /**
     * 一页多少数据
     */
    private Integer pageSize;
}
