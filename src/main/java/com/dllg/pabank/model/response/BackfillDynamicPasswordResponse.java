package com.dllg.pabank.model.response;

/**
 * 回填动态码-修改手机
 */
public class BackfillDynamicPasswordResponse extends PABankResponse {
    /**
     * 保留域
     */
    private String ReservedMsg;

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
    }
}
