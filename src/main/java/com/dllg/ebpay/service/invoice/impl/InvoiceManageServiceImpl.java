package com.dllg.ebpay.service.invoice.impl;

import com.dllg.ebpay.dao.custom.InvoiceManageCuMapper;
import com.dllg.ebpay.model.form.FpMainForm;
import com.dllg.ebpay.model.generator.FpDetail;
import com.dllg.ebpay.model.generator.FpMain;
import com.dllg.ebpay.service.invoice.InvoiceManageService;
import com.dllg.framework.model.AuthUser;
import com.dllg.framework.security.Auth;
import com.dllg.framework.utils.Constants;
import com.dllg.framework.utils.ExcelReader;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.dllg.ebpay.service.sequence.impl.SequenceServiceImpl.logger;

@Service
@Transactional
public class InvoiceManageServiceImpl implements InvoiceManageService {


    @Autowired
    private InvoiceManageCuMapper invoiceManageCuMapper;
    @Autowired
    Auth auth;

    public String impFile(MultipartFile[] file){

        ExcelReader<FpMain> excelReader = new ExcelReader<FpMain>();
        ExcelReader<FpDetail> excelDetailReader = new ExcelReader<FpDetail>();
        InputStream input = null;
        InputStream inputDetail = null;
        int impMain = 0,impDetail = 0; //记录成功导入条数与【数据库已包含条数】
        StringBuffer mes = new StringBuffer();
        StringBuffer impYMes = new StringBuffer();

        String djh;
        try {
            input = file[0].getInputStream();
            inputDetail = file[0].getInputStream();
            if (input != null) {
                List<FpMain> list = excelReader.readExcelContent(input, 0, Constants.fpMainImport, FpMain.class.getName());
                List<FpDetail> listDetail = excelDetailReader.readExcelContent(inputDetail, 1, Constants.fpDetailImport, FpDetail.class.getName());

                if (list != null && !list.isEmpty()) {
                    for (FpMain fpMain : list) {
                        //导入时查询已存在项
                        FpMainForm fm = new FpMainForm();
                                   fm.setKeyword(fpMain.getDjh());
                        List<Map<String, Object>> listOne = invoiceManageCuMapper.getFpMainList(fm); //使用djh作为查询调价查一条
                        if(listOne.size()>0){   invoiceManageCuMapper.delete((FpMain)listOne.get(0)); }        //清理已有项
                        this.addFpMain(fpMain);   //新增
                        impMain++;
                        mes.append("发票主表-成功新增导入" + impMain + "条");
                    }
                }

                if (listDetail != null && !listDetail.isEmpty()) {
                    for (FpDetail fpDetail : listDetail) {
                        if(impDetail==0) {   //导入开始时检查原有项,并清理
                            //导入时查询已存在项
                            FpMainForm fm = new FpMainForm();
                            fm.setKeyword(fpDetail.getDjh());
                            List<Map<String, Object>> listDetailOne = invoiceManageCuMapper.getFpDetailList(fm); //使用djh作为查询调价查一条
                            if (listDetailOne.size() > 0) {
                                invoiceManageCuMapper.deleteDetail((FpDetail) listDetailOne.get(0));
                            }  //清理已有子项
                        }
                        this.addFpDetail(fpDetail);
                        impDetail++;
                        mes.append("发票明细表-成功新增导入" + impDetail + "条");
                    }
                }
            }
        } catch (IOException e) {
              logger.error("发票导入失败", e);
        } finally {
            if (input != null) {  try { input.close(); } catch (IOException e) { logger.error("发票主表导入流关闭失败", e); }  }
            if (inputDetail != null) {  try {  inputDetail.close();   } catch (IOException e) {  logger.error("发票明细表导入流关闭失败", e); } }
        }
        return mes.toString();
    }




    /**
     * 添加
     */
    private void addFpMain(FpMain fpMain) {

        AuthUser user = auth.getCurrentUser();
        fpMain.setCreateUser(user.getUserCnName());
        fpMain.setCreateDate(new Date());
        fpMain.setState("0");        //默认待处理
        //fpMain.setBz(fpMain.getBz()==null?" ":fpMain.getBz());   //备注

        invoiceManageCuMapper.add(fpMain);
    }

    /**
     * 更新
     * @param fpMain
     */
    private void updateFpMain(FpMain fpMain) {

        AuthUser user = auth.getCurrentUser();
        //fpMain.setCreateUser(user.getUserCnName());
        fpMain.setCreateDate(new Date());
        fpMain.setState("1");        //更新为处理

        invoiceManageCuMapper.update(fpMain);
    }

    /**
     * 添加详情
     */
    private void addFpDetail(FpDetail fpDetail) {

        AuthUser user = auth.getCurrentUser();
        fpDetail.setCreateUser(user.getUserCnName());
        fpDetail.setCreateDate(new Date());

        //处理较长数值
        fpDetail.setSpbm(new BigDecimal(fpDetail.getSpbm()==null?"0":fpDetail.getSpbm()).toPlainString());     //商品编码
        fpDetail.setQyspbm(new BigDecimal(fpDetail.getQyspbm()==null?"0":fpDetail.getQyspbm()).toPlainString()); //企业商品编码
        fpDetail.setDj(new BigDecimal(fpDetail.getDj()==null?"0":fpDetail.getDj()).toPlainString());         //单价
        fpDetail.setSl(new BigDecimal(fpDetail.getSl()==null?"0":fpDetail.getSl()).toPlainString()); //数量
        fpDetail.setSyyhzcbz(Integer.valueOf(new BigDecimal(fpDetail.getSyyhzcbz()==null?"0":fpDetail.getSyyhzcbz()).intValue()).toString()); //优惠政策标识

        fpDetail.setJe(new BigDecimal(fpDetail.getJe()==null?"0":fpDetail.getJe()).toPlainString());         //金额
        fpDetail.setSlv(new BigDecimal(fpDetail.getSlv()==null?"0":fpDetail.getSlv()).toPlainString());       //税率
        fpDetail.setKce(new BigDecimal(fpDetail.getKce()==null?"0":fpDetail.getKce()).toPlainString());       //扣除额

        fpDetail.setXh(Integer.valueOf(new BigDecimal(fpDetail.getXh()==null?"000000":fpDetail.getXh()).intValue()).toString()); //序号

        //fpDetail.setLslbz(fpDetail.getLslbz()==null?" ":fpDetail.getLslbz());       //零税率标识
        //fpDetail.setYhzcsm(fpDetail.getYhzcsm()==null?" ":fpDetail.getYhzcsm());    //优惠政策说明

        invoiceManageCuMapper.addDetail(fpDetail);
    }

    /**
     * 更新详情
     * @param fpDetail
     */
    private void updateFpDetail(FpDetail fpDetail) {

        AuthUser user = auth.getCurrentUser();
        fpDetail.setCreateUser(user.getUserCnName());
        fpDetail.setCreateDate(new Date());

        invoiceManageCuMapper.updateDetail(fpDetail);
    }

    /**
     * 查询主表
     * @param fpMainForm
     * @return
     */
    public List<Map<String, Object>> getFpMainList(FpMainForm fpMainForm){

         PageHelper.startPage(fpMainForm);
         return invoiceManageCuMapper.getFpMainList(fpMainForm);
    }

    /**
     * 查询详情
     * @param fpMainForm
     * @return
     */
    public List<Map<String, Object>> getFpDetailList(FpMainForm fpMainForm){

        PageHelper.startPage(fpMainForm);
        return invoiceManageCuMapper.getFpDetailList(fpMainForm);
    }


    public String getGenerateXml(FpMainForm fpMainForm){

        String xml= "<Kp>"+
                "<Version>2.0</Version>"+
                "<Fpxx>"+
                "<Zsl>"+fpMainForm.getDjhs().length+"</Zsl>"+
                "<Fpsj>";

        //查询选择主项;
        for(String djh:fpMainForm.getDjhs()){  //循环获取主项

            FpMainForm fp = new FpMainForm();
            fp.setKeyword(djh);

            List<Map<String, Object>> ll = invoiceManageCuMapper.getFpMainList(fp);

            FpMain fpMain = (FpMain)invoiceManageCuMapper.getFpMainList(fp).get(0);

            xml+=   "<Fp>";   //主项start
            xml+=   "<Djh>"+(fpMain.getDjh()==null?"":fpMain.getDjh())+"</Djh>"+
                    "<Gfmc>"+(fpMain.getGfmc()==null?"":fpMain.getGfmc())+"</Gfmc>"+
                    "<Gfsh>"+(fpMain.getGfsh()==null?"":fpMain.getGfsh())+"</Gfsh>"+
                    "<Gfyhzh>"+(fpMain.getGfyhzh()==null?"":fpMain.getGfyhzh())+"</Gfyhzh>"+
                    "<Gfdzdh>"+(fpMain.getGfdzdh()==null?"":fpMain.getGfdzdh())+"</Gfdzdh>"+
                    "<Bz>"+(fpMain.getBz()==null?"":fpMain.getBz())+"</Bz>"+
                    "<Fhr>"+(fpMain.getFhr()==null?"":fpMain.getFhr())+"</Fhr>"+
                    "<Skr>"+(fpMain.getSkr()==null?"":fpMain.getSkr())+"</Skr>"+
                    "<Spbmbbh>"+(fpMain.getSpbmbbh()==null?"":fpMain.getSpbmbbh())+"</Spbmbbh>"+
                    "<Hsbz>"+(fpMain.getHsbz()==null?"":fpMain.getHsbz())+"</Hsbz>";

            this.updateFpMain(fpMain);   //更新状态
            //使用cxh查询子项
            List<Map<String, Object>> listDetail = invoiceManageCuMapper.getFpDetailList(fp);

            xml+= "<Spxx>";   //子项start
            for(int i=0;i<listDetail.size();i++) {
                FpDetail fd = (FpDetail)listDetail.get(i);
                xml += "<Sph>" +
                        "<Xh>"+(fd.getXh()==null?"":fd.getXh())+"</Xh>" +
                        "<Spmc>"+(fd.getSpmc()==null?"":fd.getSpmc())+"</Spmc>" +
                        "<Ggxh>"+(fd.getGgxh()==null?"":fd.getGgxh())+"</Ggxh>" +
                        "<Jldw>"+(fd.getJldw()==null?"":fd.getJldw())+"</Jldw>" +
                        "<Spbm>"+(fd.getSpbm()==null?"":fd.getSpbm())+"</Spbm>" +
                        "<Qyspbm>"+(fd.getQyspbm()==null?"":fd.getQyspbm())+"</Qyspbm>" +
                        "<Syyhzcbz>"+(fd.getSyyhzcbz()==null?"":fd.getSyyhzcbz())+"</Syyhzcbz>" +
                        "<Lslbz>"+(fd.getLslbz()==null?"":fd.getLslbz())+"</Lslbz>" +
                        "<Yhzcsm>"+(fd.getYhzcsm()==null?"":fd.getYhzcsm())+"</Yhzcsm>" +
                        "<Dj>"+(fd.getDj()==null?"":fd.getDj())+"</Dj>" +
                        "<Sl>"+(fd.getSl()==null?"":fd.getSl())+"</Sl>" +
                        "<Je>"+(fd.getJe()==null?"":fd.getJe())+"</Je>" +
                        "<Slv>"+(fd.getSlv()==null?"":fd.getSlv())+"</Slv>" +
                        "<Kce>"+(fd.getKce()==null?"":fd.getKce())+"</Kce>" +
                        "</Sph>";
            }
            xml+= "</Spxx>";     //子项end
            xml +=   "</Fp>";    //主项end
        }

        //拼接xml
        xml +=  "</Fpsj>" +
                "</Fpxx>" +
                "</Kp>";

        return xml;
    }







}
