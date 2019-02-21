package com.dllg.ebpay.service.invoice;

import com.dllg.ebpay.model.form.FpMainForm;
import com.dllg.ebpay.model.generator.BankCard;
import com.dllg.ebpay.model.generator.User;
import com.dllg.pabank.model.request.Params;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface InvoiceManageService {

    public String impFile(MultipartFile[] file);

    public List<Map<String, Object>> getFpMainList(FpMainForm fpMainForm);
    public List<Map<String, Object>> getFpDetailList(FpMainForm fpMainForm);

    public String getGenerateXml(FpMainForm fpMainForm);
}
