package org.example.util;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description:org.example.util
 * @Date:2023/8/19
 * @Author:谢锦创
 */
@Data
@NoArgsConstructor
public class PageResult<T> extends Result{
    /**
     * 总记录数
     */
    private long total;

    /**
     * 分页的数据
     */
    private List<T> rows;

    public PageResult(long total, List<T> rows) {
        this.setFlag(true);
        this.setMessage("分页查询成功");
        this.total = total;
        this.rows = rows;
    }
}
