package com.dllg.ebpay.service.freeDayDict.impl;

import com.dllg.common.CommonUtil;
import com.dllg.ebpay.dao.custom.CustomerDictCuMapper;
import com.dllg.ebpay.dao.custom.FreeDayDictCuMapper;
import com.dllg.ebpay.dao.custom.KindDictCuMapper;
import com.dllg.ebpay.dao.generator.UserMapper;
import com.dllg.ebpay.model.generator.CustomerDict;
import com.dllg.ebpay.model.generator.FreeDayDict;
import com.dllg.ebpay.model.generator.KindDict;
import com.dllg.ebpay.model.request.QueryParams;
import com.dllg.ebpay.service.freeDayDict.FreeDayDictService;
import com.dllg.framework.model.AuthUser;
import com.dllg.framework.security.Auth;
import io.swagger.models.auth.In;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class FreeDayDictServiceImpl implements FreeDayDictService {


    @Autowired
    private FreeDayDictCuMapper freeDayDictCuMapper;

    @Autowired
    private KindDictCuMapper kindDictCuMapper;

    @Autowired
    private CustomerDictCuMapper customerDictCuMapper;

    @Autowired
    private Auth auth;

    @Autowired
    private UserMapper userMapper;

    /**
     * 列表
     */
    @Override
    public List<Map<String, Object>> dataList(QueryParams queryParams) {
        List<Map<String, Object>> list = freeDayDictCuMapper.dataList(queryParams);
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


    /**
     * 获取任意时间的下一个月
     * 描述:<描述函数实现的功能>.
     * @param repeatDate
     * @return
     */
    public static String getPreMonth(String repeatDate) {
        String lastMonth = "";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMM");
        int year = Integer.parseInt(repeatDate.substring(0, 4));
        String monthsString = repeatDate.substring(4, 6);
        int month;
        if ("0".equals(monthsString.substring(0, 1))) {
            month = Integer.parseInt(monthsString.substring(1, 2));
        } else {
            month = Integer.parseInt(monthsString.substring(0, 2));
        }
        cal.set(year,month,Calendar.DATE);
        lastMonth = dft.format(cal.getTime());
        return lastMonth;
    }

    /**
     *
     * 得到二个日期间的间隔天数
    */
    public static String getTwoDay(String sj1, String sj2) {
       SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
       long day = 0;
       try {
            java.util.Date date = myFormatter.parse(sj1);
            java.util.Date mydate = myFormatter.parse(sj2);
            day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
           } catch (Exception e) {
            return "";
           }
       return day + "";
    }

    public String save(FreeDayDict freeDayDict, AuthUser users) {

        AuthUser user = auth.getCurrentUser();

        if (freeDayDict.getFreeDayContract() == null) {
            freeDayDict.setFreeDayY(0);
            freeDayDict.setFreeDayYInd("0");
        }

        if (freeDayDict.getFreeDayId() == null) { //新增
//            if("退款额度字典".equals(dict.getDictType()) || "付款申请额度字典".equals(dict.getDictType())){
//                List list = dictService.getDictByType(dict.getDictType());
//                if(list != null && list.size()>= 1){
//                    throw  new AnyException("该类型的字典只能设定一个");
//                }
//            }
            freeDayDict.setFreeDayId(CommonUtil.uuid());
            freeDayDict.setCreateUserId(user.getUserId());
            freeDayDict.setCreateTime(new Date());
            this.add(freeDayDict);
        } else { //修改
            freeDayDict.setUpdateUserId(user.getUserId());
            freeDayDict.setUpdateTime(new Date());
            this.update(freeDayDict);
        }
        return "success";
    }

    @Override
    public String add(FreeDayDict freeDayDict) {
        freeDayDictCuMapper.add(freeDayDict);
        return "success";
    }

    @Override
    public String update(FreeDayDict freeDayDict) {

        freeDayDictCuMapper.update(freeDayDict);
        return "success";
    }

    @Override
    public String deletes(List<String> ids) {
//        List<Dict> list = dictService.getDictByType("退款额度字典");
//        if (list == null || list.size() != 1) {
//            throw new AnyException("退款额度字典设定有误");
//        }
//        for (String i : ids) {
//            if (i.equals(list.get(0).getInterestId())) {
//                throw new AnyException("删除的字典中含有不可删除的字典类型："+list.get(0).getDictType());
//            }
//        }
        freeDayDictCuMapper.deletes(ids);
        return "success";
    }

    @Override
    public Boolean checkKey(String keyValue) {
        List<Map<String, Object>> list = freeDayDictCuMapper.selectByPrimaryKey(keyValue);
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


}
