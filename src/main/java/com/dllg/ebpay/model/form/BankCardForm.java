package com.dllg.ebpay.model.form;

import com.dllg.ebpay.model.generator.User;
import com.dllg.framework.model.BaseForm;

public class BankCardForm extends BaseForm {

    String phone;         //电话
    String code;          //短信验证码
    String unImgCode;    //图形验证码

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUnImgCode() {
        return unImgCode;
    }

    public void setUnImgCode(String unImgCode) {
        this.unImgCode = unImgCode;
    }
}
