package com.dllg.ebpay.model.generator;

import java.util.Date;

/**
 * Created by hp on 2018/10/19.
 */
public class FpMain {

    private String id;
    private String djh;      //单据号
    private String gfmc;     //购方名称
    private String gfsh;     //购方税号
    private String gfyhzh;   //购方银行账号
    private String gfdzdh;   //购方地址电话
    private String bz;       //备注
    private String fhr;      //复核人
    private String skr;      //收款人
    private String spbmbbh;  //商品编码版本号
    private String hsbz;     //含税标志

    private String createUser; //创建人
    private Date createDate;  //创建时间
    private String state;     //状态

    public String getId() {
        return id==null?"":id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDjh() {
        return djh==null?"":djh;
    }

    public void setDjh(String djh) {
        this.djh = djh;
    }

    public String getGfmc() {  return gfmc==null?"":gfmc;  }

    public void setGfmc(String gfmc) {
        this.gfmc = gfmc;
    }

    public String getGfsh() {
        return gfsh==null?"":gfsh;
    }

    public void setGfsh(String gfsh) {
        this.gfsh = gfsh;
    }

    public String getGfyhzh() {
        return gfyhzh==null?"":gfyhzh;
    }

    public void setGfyhzh(String gfyhzh) {
        this.gfyhzh = gfyhzh;
    }

    public String getGfdzdh() {
        return gfdzdh==null?"":gfdzdh;
    }

    public void setGfdzdh(String gfdzdh) {
        this.gfdzdh = gfdzdh;
    }

    public String getBz() {
        return bz==null?"":bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getFhr() {
        return fhr==null?"":fhr;
    }

    public void setFhr(String fhr) {
        this.fhr = fhr;
    }

    public String getSkr() {
        return skr==null?"":skr;
    }

    public void setSkr(String skr) {
        this.skr = skr;
    }

    public String getSpbmbbh() {
        return spbmbbh==null?"":spbmbbh;
    }

    public void setSpbmbbh(String spbmbbh) {
        this.spbmbbh = spbmbbh;
    }

    public String getHsbz() {
        return hsbz==null?"":hsbz;
    }

    public void setHsbz(String hsbz) {
        this.hsbz = hsbz;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
