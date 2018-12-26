package com.liwy.study.mybatis.bo;

/**
 * <b>名称：</b> 性别枚举类<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2018/11/28 14:54 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public enum SexEnum {
    BOY("男", (byte) 1), GIRL("女", (byte) 2);

    private Byte key;
    private String value;

    private SexEnum(String value, Byte key) {
        this.key = key;
        this.value = value;
    }

    public Byte getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static SexEnum getValueByKey(Byte key) {
        switch (key) {
            case 1:
                return BOY;
            case 2:
                return GIRL;
            default:
                return null;
        }
    }
}
