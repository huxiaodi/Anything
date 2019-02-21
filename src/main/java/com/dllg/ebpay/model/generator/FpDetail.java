package com.dllg.ebpay.model.generator;

import java.util.Date;

/**
 * Created by hp on 2018/10/19.
 */
public class FpDetail {

    private String id;
    private String djh;     //单据号
    private String xh;      //序号
    private String spmc;    //商品名称
    private String ggxh;    //规格型号
    private String jldw;    //计量单位
    private String spbm;    //商品编码
    private String qyspbm;  //企业商品编码
    private String syyhzcbz;//优惠政策标识
    private String lslbz;   //零税率标识
    private String yhzcsm;  //优惠政策说明
    private String dj;      //单价
    private String sl;      //数量
    private String je;      //金额
    private String slv;     //税率
    private String kce;     //扣除额

    private String createUser; //创建人
    private Date createDate;  //创建时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getSpmc() {
        return spmc;
    }

    public void setSpmc(String spmc) {
        this.spmc = spmc;
    }

    public String getGgxh() {
        return ggxh;
    }

    public void setGgxh(String ggxh) {
        this.ggxh = ggxh;
    }

    public String getJldw() {
        return jldw;
    }

    public void setJldw(String jldw) {
        this.jldw = jldw;
    }

    public String getSpbm() {
        return spbm;
    }

    public void setSpbm(String spbm) {
        this.spbm = spbm;
    }

    public String getQyspbm() {
        return qyspbm;
    }

    public void setQyspbm(String qyspbm) {
        this.qyspbm = qyspbm;
    }

    public String getSyyhzcbz() {
        return syyhzcbz;
    }

    public void setSyyhzcbz(String syyhzcbz) {
        this.syyhzcbz = syyhzcbz;
    }

    public String getLslbz() {
        return lslbz;
    }

    public void setLslbz(String lslbz) {
        this.lslbz = lslbz;
    }

    public String getYhzcsm() {
        return yhzcsm;
    }

    public void setYhzcsm(String yhzcsm) {
        this.yhzcsm = yhzcsm;
    }

    public String getDj() {
        return dj;
    }

    public void setDj(String dj) {
        this.dj = dj;
    }

    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public String getJe() {
        return je;
    }

    public void setJe(String je) {
        this.je = je;
    }

    public String getSlv() {
        return slv;
    }

    public void setSlv(String slv) {
        this.slv = slv;
    }

    public String getKce() {
        return kce;
    }

    public void setKce(String kce) {
        this.kce = kce;
    }

    public String getDjh() {
        return djh;
    }

    public void setDjh(String djh) {
        this.djh = djh;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
