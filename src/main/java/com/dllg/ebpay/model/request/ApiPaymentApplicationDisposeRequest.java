package com.dllg.ebpay.model.request;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Description：
 * @Author:hudi
 * @Date: Created in 11:14 2018/8/3
 */
public class ApiPaymentApplicationDisposeRequest extends ApiPaymentDisposeRequest{

    @NotBlank(message = "申请单号必填")
    private String applicationNo;
    @NotBlank(message = "申请人姓名必填")
    private String proposer;

    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer;
    }
}
