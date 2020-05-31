package com.nancy.shirojwtdemo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author chen
 * @date 2020/5/31 23:31
 */
@Data
@ToString
@NoArgsConstructor
public class Result {
    /**
     * 响应的状态码
     */
    private int code;

    /**
     * 响应的提示语
     */
    private String msg;

    /**
     * 响应的对象数据
     */
    private Object object;

    /**
     * 响应的集合数据
     */
    private List<Object> list;

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result object(Object object) {
        this.object = object;
        return this;
    }

    public Result list(List<Object> list) {
        this.list = list;
        return this;
    }
}
