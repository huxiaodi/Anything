package com.dllg.ebpay.service.interestManager.impl;

import com.dllg.common.CommonUtil;
import com.dllg.ebpay.dao.custom.*;
import com.dllg.ebpay.dao.generator.UserMapper;
import com.dllg.ebpay.model.generator.*;
import com.dllg.ebpay.model.request.QueryParams;
import com.dllg.ebpay.service.interestManager.InterestManagerService;
import com.dllg.framework.model.AuthUser;
import com.dllg.framework.security.Auth;
import com.dllg.framework.utils.Constants;
import com.dllg.framework.utils.ExcelReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dllg.ebpay.service.sequence.impl.SequenceServiceImpl.logger;

@Service
@Transactional
public class InterestManagerServiceImpl implements InterestManagerService {


    @Autowired
    private InterestManagerCuMapper interestManagerCuMapper;

    @Autowired
    private KindDictCuMapper kindDictCuMapper;

    @Autowired
    private CustomerDictCuMapper customerDictCuMapper;

    @Autowired
    private InterestDictCuMapper interestDictCuMapper;

    @Autowired
    private FreeDayDictCuMapper freeDayDictCuMapper;

    @Autowired
    private Auth auth;

    @Autowired
    private UserMapper userMapper;

    /**
     * 列表
     */
    @Override
    public List<Map<String, Object>> dataList(QueryParams queryParams) {
        List<Map<String, Object>> list = interestManagerCuMapper.dataList(queryParams);
        String createUserId;
        String updateUserId;
        for (Map<String, Object> map : list) {
            createUserId = map.get("createUserId").toString();
            if (!"".equals(createUserId)) {
                map.put("createUserId", userMapper.selectByPrimaryKey(createUserId).getUserName());
            }
            updateUserId = map.get("updateUserId").toString();
            if (!"".equals(updateUserId)) {
                map.put("updateUserId", userMapper.selectByPrimaryKey(updateUserId).getUserName());
            }
        }
        return list;
    }
    @Override
    public List<Map<String, Object>> dataListK(QueryParams queryParams) {
        List<Map<String, Object>> listK = interestManagerCuMapper.dataListK(queryParams);
        String createUserId;
        String updateUserId;
        for (Map<String, Object> map : listK) {
            createUserId = map.get("createUserId").toString();
            if (!"".equals(createUserId)) {
                map.put("createUserId", userMapper.selectByPrimaryKey(createUserId).getUserName());
            }
            updateUserId = map.get("updateUserId").toString();
            if (!"".equals(updateUserId)) {
                map.put("updateUserId", userMapper.selectByPrimaryKey(updateUserId).getUserName());
            }
        }
        return listK;
    }


    public String save(InterestManager interestManager, AuthUser users) {

        //判断使用金额
        BigDecimal moneyUse = interestManager.getInterestManagerMoneyUse(); //使用金额
        BigDecimal moneyAll = interestManager.getInterestManagerMoneyAll(); //票面金额
        int a = moneyAll.compareTo(moneyUse);
        if (a < 0 ) {
            return "使用金额 大于 汇票金额！";
        }

        AuthUser user = auth.getCurrentUser();

        if (interestManager.getInterestManagerId() == null) { //新增
            interestManager.setInterestManagerId(CommonUtil.uuid());
            interestManager.setCreateUserId(user.getUserId());
            interestManager.setCreateTime(new Date());
            interestManager.setInterestManagerInd("A"); //状态=新增 =================================================
            this.add(interestManager);
        } else { //修改
            interestManager.setUpdateUserId(user.getUserId());
            interestManager.setUpdateTime(new Date());
            interestManager.setInterestManagerInd("E"); //状态=已修改 =================================================
            this.update(interestManager);
        }
        return "保存成功！";
    }

    @Override
    public String add(InterestManager interestManager) {
        //interestManager.setInterestManagerTicketNoFact(interestManager.getInterestManagerTicketNo() + "-01");
        interestManagerCuMapper.add(interestManager);
        return "success";
    }



    @Override
    public String copyAdd(InterestManager interestManager, AuthUser users) {

        String idOld = interestManager.getInterestManagerId();
        String ind2  = interestManager.getInterestManagerInd2(); //原记录 是否计算 状态

        //剩余可 使用金额
        String moneyUse = interestManagerCuMapper.getMoneyUse( interestManager.getInterestManagerTicketNo() );
        BigDecimal a = new BigDecimal(moneyUse);
        BigDecimal a0 = new BigDecimal(0);
        int a1 = a.compareTo(a0);
        if (a1 == 0 || a1 < 0) {
            return "汇票金额已经全部使用！";
        }

        //票面金额 = 剩余可 使用金额，不用拆分
        BigDecimal all = interestManager.getInterestManagerMoneyAll();
        int a2 = all.compareTo(a);
        if (a2 == 0) {
            return "汇票金额未使用，不用拆分！";
        }

        interestManager.setInterestManagerMoneyUse(new BigDecimal(0)); //使用金额
        interestManager.setInterestManagerMoneySurplus(a); //剩余金额

        String newNo = interestManagerCuMapper.getNewNo(interestManager.getInterestManagerTicketNo());
        if (newNo.length() == 1){
            newNo = '0' + newNo;
        }
        interestManager.setInterestManagerTicketNoFact( interestManager.getInterestManagerTicketNo() + "-" + newNo); //新票号

        interestManager.setInterestManagerMoney(null); //贴息金额
        interestManager.setInterestManagerDate(""); //合同月份
        interestManager.setInterestManagerMemo(""); //备注

        //设置拆分后新记录状态
        interestManager.setInterestManagerInd("1"); //状态=已分配 =================================================
        interestManager.setInterestManagerInd2("1"); //是否计算=计算 =================================================

        interestManager.setInterestManagerId(CommonUtil.uuid());
        interestManager.setCreateUserId(users.getUserId());
        interestManager.setCreateTime(new Date());
        this.add(interestManager);


        //修改原票号状态 = 已拆分
        InterestManager interestManagerUpdateStatus = new InterestManager();
        interestManagerUpdateStatus.setInterestManagerId(idOld);  //主键
        interestManagerUpdateStatus.setInterestManagerInd("C");  //状态=已拆分 =================================================
        interestManagerUpdateStatus.setInterestManagerInd2(ind2);  //是否计算 =================================================
        interestManagerUpdateStatus.setUpdateUserId(users.getUserId());
        interestManagerUpdateStatus.setUpdateTime(new Date());
        interestManagerUpdateStatus.setInterestManagerMoneySurplus(new BigDecimal(0)); //原拆分记录 剩余金额 = 0
        interestManagerCuMapper.updateStatus(interestManagerUpdateStatus);

        return "拆分完成！";
    }



    @Override
    public String update(InterestManager interestManager) {

        interestManagerCuMapper.update(interestManager);
        return "success";
    }

    @Override
    public String editAgain(InterestManager interestManager, AuthUser user) {

        //根据合同月份查找利率
        String interest_value = interestDictCuMapper.selectInterest(interestManager.getInterestManagerDate());

        interestManager.setInterestManagerInterest(new BigDecimal(interest_value));

        interestManager.setUpdateUserId(user.getUserId());
        interestManager.setUpdateTime(new Date());

        for (int i = 0; i<interestManager.getIdArray().length; i++) {
            interestManager.setInterestManagerId(interestManager.getIdArray()[i]);

            /**
             *  判断状态。已结算不处理
            */

            //获取免息天数
            FreeDayDict freeDayDict = new FreeDayDict();
            freeDayDict.setFreeDayKind(interestManager.getInterestManagerKind()); //品种
            freeDayDict.setFreeDayDate(interestManager.getInterestManagerDate()); //合同月份

            if (interestManager.getInterestManagerKind().equals("热轧")) {
                freeDayDict.setFreeDayCustomer(interestManager.getInterestManagerCustomerName()); //客户名称
                freeDayDict.setFreeDayContract(interestManager.getInterestManagerContract()); //合同签订日期
            } else {
                freeDayDict.setFreeDayCustomer(null); //客户名称
                freeDayDict.setFreeDayContract(null); //合同签订日期
            }

            freeDayDict = freeDayDictCuMapper.getFreeDay(freeDayDict);

            if (freeDayDict == null) {
                if ( interestManager.getInterestManagerKind().equals("热轧") ) {
                    return "按【品种、合同月份、客户、合同签订日期】未找到对应的免息天数！";
                }else {
                    return "按【品种、合同月份】未找到对应的免息天数！";
                }
            }

            interestManager.setInterestManagerFreeDay(freeDayDict.getFreeDay());   //免息天数
            interestManager.setInterestManagerFreeDayY(freeDayDict.getFreeDayY()); //免息天数Y

            //修改状态
            interestManager.setInterestManagerInd("E"); //状态=已修改 =================================================

            interestManagerCuMapper.editAgain(interestManager);
        }

        return "更新完成！";
    }

    @Override
    public String setFinish(InterestManager interestManager, AuthUser user) {
        interestManager.setUpdateUserId(user.getUserId());
        interestManager.setUpdateTime(new Date());

        //修改状态
        interestManager.setInterestManagerInd("F"); //状态=完成 =================================================
        interestManager.setInterestManagerInd2("0"); //是否计算=否 =================================================

        for (int i = 0; i < interestManager.getIdArray().length; i++) {
            interestManager.setInterestManagerId(interestManager.getIdArray()[i]);
            interestManagerCuMapper.updateStatus(interestManager);
        }
        return "结算完成！";
    }


    @Override
    public String useMoneyAvg(InterestManager interestManager, AuthUser user) {
        /*
            分配总(使用/贴息)金额
        */
        interestManager.setUpdateUserId(user.getUserId());
        interestManager.setUpdateTime(new Date());

        BigDecimal b_all = interestManager.getUseMoneyAll();  //总分配金额
        BigDecimal b_surPlus   = new BigDecimal(0);      //分配后剩余金额
        BigDecimal b_ticket; //票面金额
        BigDecimal b_ticket_use = new BigDecimal(0);
        for (int i = 0; i < interestManager.getIdArray().length; i++){

            interestManager.setInterestManagerId(interestManager.getIdArray()[i]);

            //b_ticket = interestManager.getInterestManagerMoneyAllArray()[i]; //每张 票面金额
            b_ticket = interestManager.getInterestManagerMoneySurplusArray()[i]; //用剩余金额代替票面金额计算


            //分配后剩余金额 = 总分配金额 - 票面金额 bignum1.subtract(bignum2);
            if (i == 0) {
                b_surPlus = b_all.subtract(b_ticket);
            } else {
                b_surPlus = b_surPlus.subtract(b_ticket);
            }

            //更新使用金额
            int ii = b_surPlus.compareTo(new BigDecimal(0));
            if ( ii > 0  ){
                interestManager.setInterestManagerMoneyUse(b_ticket); //待分配 剩余金额>0，使用金额 = 票面金额
                interestManager.setInterestManagerMoneySurplus(new BigDecimal(0)); //待分配 剩余金额>0，剩余金额 = 0
                interestManager.setInterestManagerInd("0"); //状态=锁定 =================================================
                interestManagerCuMapper.useMoneyAvg(interestManager);
            } else {
                interestManager.setInterestManagerMoneyUse(b_ticket.add(b_surPlus) ); //待分配 剩余金额<=0，使用金额 = 票面金额 - 剩余金额(负数)
                interestManager.setInterestManagerMoneySurplus(b_surPlus.abs()); //待分配 剩余金额<=0，剩余金额 = 剩余金额(绝对值)
                interestManager.setInterestManagerInd("1"); //状态=已分配 =================================================
                interestManagerCuMapper.useMoneyAvg(interestManager);
                break;
            }

        }

        //更新待扣除贴息单据状态=已扣除
        InterestManager interestManagerUpdateStatus = new InterestManager();
        interestManagerUpdateStatus.setUpdateUserId(user.getUserId());
        interestManagerUpdateStatus.setUpdateTime(new Date());
        for (int j = 0; j < interestManager.getIdOldArray().length; j++) {
            interestManagerUpdateStatus.setInterestManagerId(interestManager.getIdOldArray()[j]); //主键
            interestManagerUpdateStatus.setInterestManagerInd("K"); //状态=已扣除 =================================================
            interestManagerUpdateStatus.setInterestManagerInd2("0"); //是否计算=否 =================================================
            interestManagerCuMapper.updateStatus(interestManagerUpdateStatus);
        }


        return "金额分配完成";
    }


    //算两个日期间隔多少天
    public int getDays(Date start, Date end) {
        return (int) ((end.getTime() - start.getTime()) / 1000 / 60 / 60 / 24);
    }

    @Override
    public String interestCompute(InterestManager interestManager, AuthUser user) {
        /*
            贴息金额=（到期日期 — 交票日期 — X）* 利率 * 票面实际使用金额
                其中X为（免息天数+免息天数Y）。
                系统根据客户名称、品种、合同月份、合同签订日期从免息天数字典中查找免息天数、免息天数Y值，自动计算X值。
                如果（到期日期 — 交票日期 — X）≤ 0，则贴息金额 = 0。
        */

        interestManager.setUpdateUserId(user.getUserId());
        interestManager.setUpdateTime(new Date());

        for (int i = 0; i<interestManager.getIdArray().length; i++) {
            if (interestManager.getInterestManagerKindArray()[i] == null){
                continue;
                //return "未设置品种！";
            }
            if (interestManager.getInterestManagerDateArray()[i] == null){
                continue;
                //return "未设置合同月份！";
            }

            interestManager.setInterestManagerId(interestManager.getIdArray()[i]);
            interestManager.setInterestManagerDateStart(interestManager.getInterestManagerDateStartArray()[i]);
            interestManager.setInterestManagerDateEnd(interestManager.getInterestManagerDateEndArray()[i]);
            interestManager.setInterestManagerInterest(interestManager.getInterestManagerInterestArray()[i]);
            interestManager.setInterestManagerMoneyUse(interestManager.getInterestManagerMoneyUseArray()[i]);
            interestManager.setInterestManagerKind(interestManager.getInterestManagerKindArray()[i]);
            interestManager.setInterestManagerCustomerName(interestManager.getInterestManagerCustomerNameArray()[i]);
            interestManager.setInterestManagerDate(interestManager.getInterestManagerDateArray()[i]);
            interestManager.setInterestManagerContract(interestManager.getInterestManagerContractArray()[i]);

            //算两个日期间隔多少天
            int days = getDays(interestManager.getInterestManagerDateStart(),interestManager.getInterestManagerDateEnd());

            //获取免息天数
            FreeDayDict freeDayDict = new FreeDayDict();
            freeDayDict.setFreeDayKind(interestManager.getInterestManagerKind()); //品种
            freeDayDict.setFreeDayDate(interestManager.getInterestManagerDate()); //合同月份

            if (interestManager.getInterestManagerKind().equals("热轧")) {
                freeDayDict.setFreeDayCustomer(interestManager.getInterestManagerCustomerName()); //客户名称
                freeDayDict.setFreeDayContract(interestManager.getInterestManagerContract()); //合同签订日期
            } else {
                freeDayDict.setFreeDayCustomer(null); //客户名称
                freeDayDict.setFreeDayContract(null); //合同签订日期
            }

            freeDayDict = freeDayDictCuMapper.getFreeDay(freeDayDict);

            if (freeDayDict == null) {
                if ( interestManager.getInterestManagerKind().equals("热轧") ) {
                    return "按【品种、合同月份、客户、合同签订日期】未找到对应的免息天数！";
                }else {
                    return "按【品种、合同月份】未找到对应的免息天数！";
                }
            }

            interestManager.setInterestManagerFreeDay(freeDayDict.getFreeDay());
            interestManager.setInterestManagerFreeDayY(freeDayDict.getFreeDayY());

            int daysX = interestManager.getInterestManagerFreeDay() + interestManager.getInterestManagerFreeDayY() ;

            BigDecimal interestValue = interestManager.getInterestManagerInterest();
            BigDecimal moneyUse = interestManager.getInterestManagerMoneyUse();

            //贴息金额
            BigDecimal interestMoney;
            if ((days - daysX ) == 0 || (days - daysX ) < 0){
                interestMoney = new BigDecimal(0);
            } else {
                interestMoney = new BigDecimal( (days - daysX ) ).multiply(interestValue).multiply(moneyUse) ;
            }

            interestManager.setInterestManagerMoney(interestMoney);


//            //标识=1时计算贴息金额
//            if (interestManager.getInterestManagerIndArray()[i].equals("1")) {
//                interestManagerCuMapper.interestCompute(interestManager);
//            }

            //更新状态
            interestManager.setInterestManagerInd("9"); //状态=已计算 =================================================
            interestManagerCuMapper.interestCompute(interestManager);

        }
        return "贴息计算完成！";
    }

    @Override
    public String deletes(List<String> ids) {

        interestManagerCuMapper.deletes(ids);
        return "success";
    }

    @Override
    public Boolean checkKey(String keyValue) {
        List<Map<String, Object>> list = interestManagerCuMapper.selectByPrimaryKey(keyValue);
        if (list.size() != 0) {
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<KindDict> getKindDictSelect(){
        return kindDictCuMapper.selectByExample();
    }


    @Override
    public List<CustomerDict> getCustomerDictSelect(){
        return customerDictCuMapper.selectByExample();
    }


    @Override
    public List<InterestDict> getInterestDictSelect() { return interestDictCuMapper.selectByExample(); }



    public String impFile(MultipartFile[] file, AuthUser user){

        ExcelReader<InterestManager> excelReader = new ExcelReader<InterestManager>();
        InputStream input = null;
        int impMain = 0; //记录成功导入条数与【数据库已包含条数】
        int checkKeyNum = 0; //验证重复数据，根据票号
        StringBuffer mes = new StringBuffer();
        StringBuffer impYMes = new StringBuffer();

        try{
            input = file[0].getInputStream();

            if (input != null) {
                List<InterestManager> list = excelReader.readExcelContent(input, 0, Constants.interestImport, InterestManager.class.getName());

                if (list != null && !list.isEmpty()) {
                    for (InterestManager interestManager : list) {
                        /*
                        * 导入时是否判断票号已经存在，如存在如何处理
                        */
                        List<Map<String, Object>> listCheckKey = interestManagerCuMapper.selectByPrimaryKey(interestManager.getInterestManagerTicketNo());
                        if (listCheckKey.size() != 0) { //验证导入数据，票号重复不导入
                            checkKeyNum++;
                            continue;
                        }

                        interestManager.setInterestManagerId(CommonUtil.uuid());
                        interestManager.setCreateUserId(user.getUserId());
                        interestManager.setCreateTime(new Date());
                        interestManager.setInterestManagerTicketNoFact(interestManager.getInterestManagerTicketNo() + "-01");
                        interestManager.setInterestManagerMoneyUse(new BigDecimal(0));

                        //剩余金额 = 票面金额（导入时）
                        interestManager.setInterestManagerMoneySurplus(interestManager.getInterestManagerMoneyAll());

                        interestManager.setInterestManagerInd("A"); //状态=新增 =================================================
                        this.add(interestManager);
                        impMain++;
                        //mes.append("贴息基础数据-成功新增导入" + impMain + "条");
                    }
                }

                mes.append("贴息基础数据-成功新增导入" + impMain + "条\n");
                mes.append("验证重复数据" + checkKeyNum + "条");
            }

        } catch (IOException e) {
            logger.error("贴息基础数据导入失败", e);
        } finally {
            if (input != null) {  try { input.close(); } catch (IOException e) { logger.error("贴息基础数据导入流关闭失败", e); }  }
        }
        return mes.toString();

    }




}
