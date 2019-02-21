package com.dllg.ebpay.service.user.impl;

import com.dllg.common.CommonUtil;
import com.dllg.ebpay.configurer.EbpayProperties;
import com.dllg.ebpay.dao.custom.RoleCuMapper;
import com.dllg.ebpay.dao.custom.TeamCuMapper;
import com.dllg.ebpay.dao.custom.UserCuMapper;
import com.dllg.ebpay.dao.custom.UserLogCuMapper;
import com.dllg.ebpay.dao.generator.*;
import com.dllg.ebpay.model.form.UserForm;
import com.dllg.ebpay.model.form.UserLogForm;
import com.dllg.ebpay.model.generator.*;
import com.dllg.ebpay.model.request.LoginRequest;
import com.dllg.ebpay.model.request.QueryParams;
import com.dllg.ebpay.model.request.UserRequest;
import com.dllg.ebpay.service.user.UserLogService;
import com.dllg.ebpay.service.user.UserService;
import com.dllg.framework.exception.AnyException;
import com.dllg.framework.model.ApiResult;
import com.dllg.framework.model.AuthUser;
import com.dllg.framework.security.Auth;
import com.dllg.framework.utils.ApiResultUtil;
import com.dllg.framework.utils.Constant;
import com.dllg.framework.utils.FileUploadUtils;
import com.dllg.framework.utils.SecurityUtils;
import com.dllg.pabank.model.request.OpenCustAcctIdRequest;
import com.dllg.pabank.model.response.OpenCustAcctIdResponse;
import com.dllg.pabank.service.PABankService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.dllg.framework.utils.ApiResultUtil.error;
import static com.dllg.framework.utils.ApiResultUtil.success;

@Service
@Transactional
public class UserLogServiceImpl implements UserLogService {

    @Autowired
    private Auth auth;

    @Autowired
    private UserLogCuMapper userLogCuMapper;
    @Autowired
    private HttpServletRequest request;

    @Override
    public List<Map<String, Object>> getLogList(UserLogForm userLogForm) {
        PageHelper.startPage(userLogForm);
        List<Map<String, Object>> list = userLogCuMapper.getLogList(userLogForm);
        return list;
    }

    @Override
    public List<Map<String, Object>> getUserLogList(UserLogForm userLogForm) {
        PageHelper.startPage(userLogForm);
        List<Map<String, Object>> list = userLogCuMapper.getUserLogList(userLogForm);
        return list;
    }

    @Override
    public void addUser(AuthUser authUser) {

        UserLog ul = new UserLog();
        try {
            BeanUtils.copyProperties(ul,authUser);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        ul.setId(CommonUtil.uuid());
        ul.setLoginDate(new Date());
        ul.setLoginIp(getIpAddr(request));

        if(ul.getUserCnName()==null){ ul.setUserCnName("-"); }
        if(ul.getRemark()==null){ ul.setRemark("-"); }
        ul.setLoginMode("账号登录");
        ul.setLoginDevice("PC端");

        userLogCuMapper.addLog(ul);
    }



    /**
     * @Description: 获取客户端IP地址
     */
    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if(ip.equals("127.0.0.1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ip= inet.getHostAddress();
            }
        }
        // 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ip != null && ip.length() > 15){
            if(ip.indexOf(",")>0){
                ip = ip.substring(0,ip.indexOf(","));
            }
        }
        return ip;
    }

}
