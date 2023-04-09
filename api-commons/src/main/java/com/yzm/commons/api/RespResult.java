package com.yzm.commons.api;

import lombok.Data;

import java.io.Serializable;

/**
 * 响应结果
 */
@Data
public class RespResult<T> implements Serializable {
    private static final long serialVersionUID = 7374073564792172654L;

    // 状态码
    private String code;
    // 提示信息
    private String msg;
    // 返回数据
    private T data;

    public RespResult() {
        this(null);
    }

    public RespResult(T data) {
        this(MessageEnum.SUCCESS, data);
    }

    public RespResult(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public RespResult(MessageEnum messageEnum, T data) {
        this.code = messageEnum.getCode();
        this.msg = messageEnum.getMessage();
        this.data = data;
    }

    public static RespResult<?> success() {
        return new RespResult<>();
    }

    public static RespResult<?> fail() {
        return new RespResult<>(MessageEnum.Error, null);
    }

    public static <T> RespResult<T> success(T data) {
        return new RespResult<>(data);
    }

    public static <T> RespResult<T> fail(T data) {
        return new RespResult<>(MessageEnum.Error, data);
    }
}
