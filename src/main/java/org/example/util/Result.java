package org.example.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description:org.example.util
 * @Date:2023/8/19
 * @Author:谢锦创
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result implements Serializable {
    /**
     * 响应给前端是否成功的标识
     */
    private boolean flag;

    /**
     * 响应的消息
     */
    @JsonIgnore
    private transient  String message;

    /**
     * 响应数据
     */
    private Object data;

    /**
     * 响应成功
     * @param message
     * @param data
     * @return
     */
    public static Result success(String message, Object data) {
        return new Result(true, message, data);
    }

    /**
     * 响应失败
     * @param message
     * @param data
     * @return
     */
    public static Result fail(String message, Object data) {
        return new Result(false, message, data);
    }

    public static Result fail(String message) {
        return fail(message, null);
    }

}
