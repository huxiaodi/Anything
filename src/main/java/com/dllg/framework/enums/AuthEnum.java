package com.dllg.framework.enums;

public enum AuthEnum {
    AUTH_USER("auth_user"),
    MENUS("menus"),
    ;
    private String value;

    AuthEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
