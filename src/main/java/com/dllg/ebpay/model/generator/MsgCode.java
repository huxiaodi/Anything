package com.dllg.ebpay.model.generator;

/**
 *   短信验证码
 *   msg_code
 * @mbg.generated do_not_delete_during_merge
 */
public class MsgCode {
    /**
     *   短信验证码id
     *   msg_code.code_id
     * @mbg.generated
     */
    private String codeId;

    /**
     *   手机号
     *   msg_code.code_tel
     * @mbg.generated
     */
    private String codeTel;

    /**
     *   验证码
     *   msg_code.code_value
     * @mbg.generated
     */
    private String codeValue;

    /**
     *   超时时间
     *   msg_code.code_expire_time
     * @mbg.generated
     */
    private String codeExpireTime;

    /**
     * 短信验证码id
     * @return the value of msg_code.code_id
     * @mbg.generated
     */
    public String getCodeId() {
        return codeId;
    }

    /**
     * 短信验证码id
     * @param codeId the value for msg_code.code_id
     * @mbg.generated
     */
    public void setCodeId(String codeId) {
        this.codeId = codeId == null ? null : codeId.trim();
    }

    /**
     * 手机号
     * @return the value of msg_code.code_tel
     * @mbg.generated
     */
    public String getCodeTel() {
        return codeTel;
    }

    /**
     * 手机号
     * @param codeTel the value for msg_code.code_tel
     * @mbg.generated
     */
    public void setCodeTel(String codeTel) {
        this.codeTel = codeTel == null ? null : codeTel.trim();
    }

    /**
     * 验证码
     * @return the value of msg_code.code_value
     * @mbg.generated
     */
    public String getCodeValue() {
        return codeValue;
    }

    /**
     * 验证码
     * @param codeValue the value for msg_code.code_value
     * @mbg.generated
     */
    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue == null ? null : codeValue.trim();
    }

    /**
     * 超时时间
     * @return the value of msg_code.code_expire_time
     * @mbg.generated
     */
    public String getCodeExpireTime() {
        return codeExpireTime;
    }

    /**
     * 超时时间
     * @param codeExpireTime the value for msg_code.code_expire_time
     * @mbg.generated
     */
    public void setCodeExpireTime(String codeExpireTime) {
        this.codeExpireTime = codeExpireTime == null ? null : codeExpireTime.trim();
    }
}