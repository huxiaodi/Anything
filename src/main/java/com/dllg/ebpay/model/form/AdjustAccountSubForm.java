package com.dllg.ebpay.model.form;

/**
 * @Description：
 * @Author:hudi
 * @Date: Created in 15:38 2018/4/13
 */
public class AdjustAccountSubForm {

    private String accountSubTradeId; // 交易单id
    private String accountSubId; // 转账子账户id
    private String password; // 密码
    private String remark;  // 备注

    public String getAccountSubTradeId() {
        return accountSubTradeId;
    }

    public void setAccountSubTradeId(String accountSubTradeId) {
        this.accountSubTradeId = accountSubTradeId;
    }

    public String getAccountSubId() {
        return accountSubId;
    }

    public void setAccountSubId(String accountSubId) {
        this.accountSubId = accountSubId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
