package com.dllg.pabank.model.request;

/**
 * 企业客户开户
 */
public class EntpClientOpenAcctRequest extends PABankRequest {
    /**
     * 资金汇总账号
     */
    private String FundSummaryAcctNo;
    /**
     * 单位名称
     */
    private String CompanyName;
    /**
     * 证件类型
     * U-统一社会信用代码
     * K-组织机构代码
     */
    private String GlobalType;
    /**
     * 证件号码
     */
    private String GlobalId;
    /**
     * 证件失效日期
     * 格式为：YYYYMMDD
     * 三证合一填写一样的
     */
    private String GlobalInvalidDate;
    /**
     * 营业执照号
     */
    private String LicenseNo;
    /**
     * 生效-记账日期
     */
    private String EffectDate;
    /**
     * 执照失效日期
     */
    private String LicenseInvalidDate;
    /**
     * 法人代表姓名
     */
    private String ReprClientName;
    /**
     * 法人代表证件类型
     * 仅支持0-身份证
     */
    private String ReprGlobalType;
    /**
     * 法人代表证件号码
     */
    private String ReprGlobalId;
    /**
     * 经办人名称
     */
    private String AgencyClientNamel;
    /**
     * 经办人证件类型
     * 仅支持0-身份证
     */
    private String AgencyClientGlobalType;
    /**
     * 经办人证件号码
     */
    private String AgencyClientGlobalId;
    /**
     * 经办人手机号码
     */
    private String AgencyClientMobile;
    /**
     * 电子邮件地址
     */
    private String Email;
    /**
     * 单位地址
     */
    private String CompanyAddress;
    /**
     * 文件名称
     */
    private String FileName;
    /**
     * 银行账户名称
     */
    private String BankAcctName;
    /**
     * 子账户账号
     */
    private String SubAcctNo;
    /**
     * 银行账号
     */
    private String BankAcctNo;
    /**
     * 开户网点
     */
    private String OpenSubBranchId;
    /**
     * 开户网点名称
     */
    private String AcctOpenSubBranchName;
    /**
     * 附件路径
     */
    private String AffixPath;
    /**
     * 产品代码
     */
    private String ProductCode;
    /**
     * 基金公司代码
     */
    private String FundComCode;
    /**
     * 商户的客户号
     */
    private String TraderClientNo;
    /**
     * 备注
     * 各个基金公司特殊字段:备用字段
     * 存基金公司特殊字段
     * 字段用’|’竖线分割
     */
    private String Remark;
    /**
     * 说明
     */
    private String Desc;
    /**
     * 保留域
     */
    private String ReservedMsg;

    public String getFundSummaryAcctNo() {
        return FundSummaryAcctNo;
    }

    public void setFundSummaryAcctNo(String fundSummaryAcctNo) {
        FundSummaryAcctNo = fundSummaryAcctNo;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getGlobalType() {
        return GlobalType;
    }

    public void setGlobalType(String globalType) {
        GlobalType = globalType;
    }

    public String getGlobalId() {
        return GlobalId;
    }

    public void setGlobalId(String globalId) {
        GlobalId = globalId;
    }

    public String getGlobalInvalidDate() {
        return GlobalInvalidDate;
    }

    public void setGlobalInvalidDate(String globalInvalidDate) {
        GlobalInvalidDate = globalInvalidDate;
    }

    public String getLicenseNo() {
        return LicenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        LicenseNo = licenseNo;
    }

    public String getEffectDate() {
        return EffectDate;
    }

    public void setEffectDate(String effectDate) {
        EffectDate = effectDate;
    }

    public String getLicenseInvalidDate() {
        return LicenseInvalidDate;
    }

    public void setLicenseInvalidDate(String licenseInvalidDate) {
        LicenseInvalidDate = licenseInvalidDate;
    }

    public String getReprClientName() {
        return ReprClientName;
    }

    public void setReprClientName(String reprClientName) {
        ReprClientName = reprClientName;
    }

    public String getReprGlobalType() {
        return ReprGlobalType;
    }

    public void setReprGlobalType(String reprGlobalType) {
        ReprGlobalType = reprGlobalType;
    }

    public String getReprGlobalId() {
        return ReprGlobalId;
    }

    public void setReprGlobalId(String reprGlobalId) {
        ReprGlobalId = reprGlobalId;
    }

    public String getAgencyClientNamel() {
        return AgencyClientNamel;
    }

    public void setAgencyClientNamel(String agencyClientNamel) {
        AgencyClientNamel = agencyClientNamel;
    }

    public String getAgencyClientGlobalType() {
        return AgencyClientGlobalType;
    }

    public void setAgencyClientGlobalType(String agencyClientGlobalType) {
        AgencyClientGlobalType = agencyClientGlobalType;
    }

    public String getAgencyClientGlobalId() {
        return AgencyClientGlobalId;
    }

    public void setAgencyClientGlobalId(String agencyClientGlobalId) {
        AgencyClientGlobalId = agencyClientGlobalId;
    }

    public String getAgencyClientMobile() {
        return AgencyClientMobile;
    }

    public void setAgencyClientMobile(String agencyClientMobile) {
        AgencyClientMobile = agencyClientMobile;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getCompanyAddress() {
        return CompanyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        CompanyAddress = companyAddress;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getBankAcctName() {
        return BankAcctName;
    }

    public void setBankAcctName(String bankAcctName) {
        BankAcctName = bankAcctName;
    }

    public String getSubAcctNo() {
        return SubAcctNo;
    }

    public void setSubAcctNo(String subAcctNo) {
        SubAcctNo = subAcctNo;
    }

    public String getBankAcctNo() {
        return BankAcctNo;
    }

    public void setBankAcctNo(String bankAcctNo) {
        BankAcctNo = bankAcctNo;
    }

    public String getOpenSubBranchId() {
        return OpenSubBranchId;
    }

    public void setOpenSubBranchId(String openSubBranchId) {
        OpenSubBranchId = openSubBranchId;
    }

    public String getAcctOpenSubBranchName() {
        return AcctOpenSubBranchName;
    }

    public void setAcctOpenSubBranchName(String acctOpenSubBranchName) {
        AcctOpenSubBranchName = acctOpenSubBranchName;
    }

    public String getAffixPath() {
        return AffixPath;
    }

    public void setAffixPath(String affixPath) {
        AffixPath = affixPath;
    }

    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String productCode) {
        ProductCode = productCode;
    }

    public String getFundComCode() {
        return FundComCode;
    }

    public void setFundComCode(String fundComCode) {
        FundComCode = fundComCode;
    }

    public String getTraderClientNo() {
        return TraderClientNo;
    }

    public void setTraderClientNo(String traderClientNo) {
        TraderClientNo = traderClientNo;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
    }
}
