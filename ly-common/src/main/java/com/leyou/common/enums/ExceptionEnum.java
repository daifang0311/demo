package com.leyou.common.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum {
    CATEGORY_NOT_FOUND(400, "价格不能为空！"), BRAND_NOT_FOUND(400,"找不到品牌" ),
    INVALID_FILE_TYPE(400,"文件上传失败"), UPLOAD_FILE_ERROR(400,"文件上传失败"),
    SPEC_GROUP_NOT_FOUND(204,"分组没有类型"), SPEC_PARAM_NOT_FOUND,
    GOODS_NOT_FOUND(204,"找不到商品"), ;
//    BRAND_NOT_FOUND(400,"品牌不能为空");
    private int status;
    private String msg;

    public int status() {
        return this.status;
    }

    public String msg() {
        return this.msg;
    }
}
