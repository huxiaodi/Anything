package com.dllg.framework.security;

public enum AuthEnum {
    AUTH_USER("auth_user"),
    AUTH_USER_EXPIRE_HOURS("3"),
    JWT_BASE_64_SECURITY("awesome")
    ;

    private String key;

    AuthEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    @Override
    public String toString() {
        return "AuthEnum{" +
                "key='" + key + '\'' +
                '}';
    }
}
