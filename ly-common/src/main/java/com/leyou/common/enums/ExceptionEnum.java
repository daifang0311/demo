package com.leyou.common.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum {
    CATEGORY_NOT_FOUND(400, "价格不能为空！");
    private int status;
    private String msg;

    public int status() {
        return this.status;
    }

    public String msg() {
        return this.msg;
    }
}
