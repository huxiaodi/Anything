package com.dllg.ebpay.model.generator;

/**
 *   行号信息表
 *   zjjz_cnaps_bankinfo
 * @mbg.generated do_not_delete_during_merge
 */
public class ZjjzCnapsBankinfo {
    /**
     *   支付行号
     *   zjjz_cnaps_bankinfo.bankno
     * @mbg.generated
     */
    private String bankno;

    /**
     *   行号状态
     *   zjjz_cnaps_bankinfo.status
     * @mbg.generated
     */
    private String status;

    /**
     *   行别代码
     *   zjjz_cnaps_bankinfo.bankclscode
     * @mbg.generated
     */
    private String bankclscode;

    /**
     *   城市代码
     *   zjjz_cnaps_bankinfo.citycode
     * @mbg.generated
     */
    private String citycode;

    /**
     *   行名全称
     *   zjjz_cnaps_bankinfo.bankname
     * @mbg.generated
     */
    private String bankname;

    /**
     * 支付行号
     * @return the value of zjjz_cnaps_bankinfo.bankno
     * @mbg.generated
     */
    public String getBankno() {
        return bankno;
    }

    /**
     * 支付行号
     * @param bankno the value for zjjz_cnaps_bankinfo.bankno
     * @mbg.generated
     */
    public void setBankno(String bankno) {
        this.bankno = bankno == null ? null : bankno.trim();
    }

    /**
     * 行号状态
     * @return the value of zjjz_cnaps_bankinfo.status
     * @mbg.generated
     */
    public String getStatus() {
        return status;
    }

    /**
     * 行号状态
     * @param status the value for zjjz_cnaps_bankinfo.status
     * @mbg.generated
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 行别代码
     * @return the value of zjjz_cnaps_bankinfo.bankclscode
     * @mbg.generated
     */
    public String getBankclscode() {
        return bankclscode;
    }

    /**
     * 行别代码
     * @param bankclscode the value for zjjz_cnaps_bankinfo.bankclscode
     * @mbg.generated
     */
    public void setBankclscode(String bankclscode) {
        this.bankclscode = bankclscode == null ? null : bankclscode.trim();
    }

    /**
     * 城市代码
     * @return the value of zjjz_cnaps_bankinfo.citycode
     * @mbg.generated
     */
    public String getCitycode() {
        return citycode;
    }

    /**
     * 城市代码
     * @param citycode the value for zjjz_cnaps_bankinfo.citycode
     * @mbg.generated
     */
    public void setCitycode(String citycode) {
        this.citycode = citycode == null ? null : citycode.trim();
    }

    /**
     * 行名全称
     * @return the value of zjjz_cnaps_bankinfo.bankname
     * @mbg.generated
     */
    public String getBankname() {
        return bankname;
    }

    /**
     * 行名全称
     * @param bankname the value for zjjz_cnaps_bankinfo.bankname
     * @mbg.generated
     */
    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
    }
}