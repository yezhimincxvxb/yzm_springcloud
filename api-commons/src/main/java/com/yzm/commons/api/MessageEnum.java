package com.yzm.commons.api;

import lombok.Getter;

@Getter
public enum MessageEnum {

    Error("0", "操作失败"),
    SUCCESS("200", "操作成功"),
    BAD_PARAM("400", "请求参数错误"),
    NOT_FIND_URL("404", "请求路径错误"),
    NOT_SUPPORT_REQUEST("405", "请求方式不支持")
    ;

    private final String code;
    private final String message;

    MessageEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
