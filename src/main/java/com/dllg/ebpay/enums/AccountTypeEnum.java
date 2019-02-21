package com.dllg.ebpay.enums;

/**
 * 总账户/银行账户类型枚举
 */
public enum AccountTypeEnum {

    PA(1,"平安银行");

    private Integer code;

    private String bankName;

    AccountTypeEnum(Integer code, String bankName) {
        this.code = code;
        this.bankName = bankName;
    }

    public Integer getCode() {
        return code;
    }

    public String getBankName() {
        return bankName;
    }
}
