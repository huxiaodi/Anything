package com.dllg.ebpay.service.interestDict.impl;

import com.dllg.common.CommonUtil;
import com.dllg.ebpay.dao.custom.InterestDictCuMapper;
import com.dllg.ebpay.dao.generator.UserMapper;
import com.dllg.ebpay.model.generator.InterestDict;
import com.dllg.ebpay.model.request.QueryParams;
import com.dllg.ebpay.service.interestDict.InterestDictService;
import com.dllg.framework.exception.AnyException;
import com.dllg.framework.model.ApiResult;
import com.dllg.framework.model.AuthUser;
import com.dllg.framework.security.Auth;
import com.dllg.framework.utils.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.dllg.framework.utils.ApiResultUtil.success;

@Service
@Transactional
public class InterestDictServiceImpl implements InterestDictService {


    @Autowired
    private InterestDictCuMapper interestDictCuMapper;

    @Autowired
    private Auth auth;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FileUploadUtils fileUploadUtils;

    /**
     * 列表
     */
    @Override
    public List<Map<String, Object>> dataList(QueryParams queryParams) {
        List<Map<String, Object>> list = interestDictCuMapper.dataList(queryParams);
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
    public ApiResult save(MultipartFile attachedFile, InterestDict interestDict, AuthUser users) throws Exception {

        AuthUser user = auth.getCurrentUser();
//        String fileUrl = "D:/" + CommonUtil.uuid() + fileName;
//        fileUrl = "http://staticstest.qiatuchina.com/"
//        File file = new File(fileUrl);

        if (attachedFile != null) {
            //attachedFile.transferTo(file);
            String fileName = attachedFile.getOriginalFilename();
            interestDict.setInterestFileUrl(fileUploadUtils.upload(attachedFile, interestDict.getInterestId(), FileUploadUtils.FOLDER_USER));
            interestDict.setInterestFile(fileName);
            //interestDict.setInterestFileUrl(attachedFile.);
        }else {
            interestDict.setInterestFile("");
            interestDict.setInterestFileUrl("");
        }

        if (interestDict.getInterestId() == null) { //新增
            interestDict.setInterestId(CommonUtil.uuid());
            interestDict.setCreateUserId(user.getUserId());
            interestDict.setCreateTime(new Date());
            this.add(interestDict);
        } else { //修改
            interestDict.setUpdateUserId(user.getUserId());
            interestDict.setUpdateTime(new Date());
            this.update(interestDict);
        }

        return success("保存成功");
    }


//    public String save(InterestDict interestDict, AuthUser users) {
//
//        AuthUser user = auth.getCurrentUser();
//
//
//        if (interestDict.getInterestId() == null) { //新增
//            interestDict.setInterestId(CommonUtil.uuid());
//            interestDict.setCreateUserId(user.getUserId());
//            interestDict.setCreateTime(new Date());
//            this.add(interestDict);
//        } else { //修改
//            interestDict.setUpdateUserId(user.getUserId());
//            interestDict.setUpdateTime(new Date());
//            this.update(interestDict);
//        }
//        return "success";
//    }

    @Override
    public String add(InterestDict interestDict) {

        interestDictCuMapper.add(interestDict);
        return "success";
    }

    @Override
    public String update(InterestDict interestDict) {

        interestDictCuMapper.update(interestDict);
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
        interestDictCuMapper.deletes(ids);
        return "success";
    }

    @Override
    public Boolean checkKey(String keyValue) {
        List<Map<String, Object>> list = interestDictCuMapper.selectByPrimaryKey(keyValue);
        if (list.size() != 0) {
            return true;
        }else{
            return false;
        }
    }


}
