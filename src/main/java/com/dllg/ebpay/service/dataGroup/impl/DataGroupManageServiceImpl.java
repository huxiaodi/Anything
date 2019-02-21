package com.dllg.ebpay.service.dataGroup.impl;

import com.dllg.common.CommonUtil;
import com.dllg.ebpay.dao.custom.DataGroupManageCuMapper;
import com.dllg.ebpay.dao.custom.DictionaryManageCuMapper;
import com.dllg.ebpay.model.form.TeamForm;
import com.dllg.ebpay.model.generator.Dict;
import com.dllg.ebpay.model.generator.Team;
import com.dllg.ebpay.model.generator.TeamDict;
import com.dllg.ebpay.model.request.QueryParams;
import com.dllg.ebpay.service.dataGroup.DataGroupManageService;
import com.dllg.ebpay.service.dictionary.DictionaryManageService;
import com.dllg.framework.model.AuthUser;
import com.dllg.framework.security.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DataGroupManageServiceImpl implements DataGroupManageService {


    @Autowired
    private DataGroupManageCuMapper dataGroupManageCuMapper;

    @Autowired
    private Auth auth;

    /**
     * 列表
     */
    @Override
    public List manageList(QueryParams queryParams) {
        return dataGroupManageCuMapper.manageList(queryParams);
    }

    public String save(TeamForm teamForm, AuthUser users){

        AuthUser user = auth.getCurrentUser();

        Team team = null;
        if(teamForm.getTeamId()==null) {//新增

            //添加组
            team = new Team();
            team.setTeamId(CommonUtil.uuid());     //生产组id
            team.setTeamName(teamForm.getTeamName());
            team.setTeamCompanyId(user.getUserCompanyId()==null?"":user.getUserCompanyId());
            team.setTeamType(1);                   //自建
            team.setTeamRemark(teamForm.getTeamRemark()==null?"":teamForm.getTeamRemark());
            team.setTeamCreateUserId(user.getUserId());
            team.setTeamCreateUser(user.getUserCnName());
            team.setTeamCreateTime(new Date());
            team.setTeamUpdateUserId("");
            team.setTeamUpdateUser("");
            team.setTeamUpdateTime(new Date());
            dataGroupManageCuMapper.addGroup(team);           //保存组信息
        }else{
            //更新组
            team = new Team();
            team.setTeamId(teamForm.getTeamId());                //获取组id
            team.setTeamName(teamForm.getTeamName());
            team.setTeamCompanyId(user.getUserCompanyId()==null?"":user.getUserCompanyId());
            team.setTeamType(1);                                 //自建
            team.setTeamRemark(teamForm.getTeamRemark()==null?"":teamForm.getTeamRemark());
            team.setTeamUpdateUserId(user.getUserId());
            team.setTeamUpdateUser(user.getUserCnName());
            team.setTeamUpdateTime(new Date());
            dataGroupManageCuMapper.updateGroup(team);        //更新组信息

            //清理原有关联信息,重新添加选择
            List<TeamDict> li = new ArrayList<TeamDict>();
            TeamDict teamDict = new TeamDict();
            teamDict.setTeamDictTeamId(team.getTeamId());
            li.add(teamDict);
            dataGroupManageCuMapper.deleteTeamDict(li);
        }

        //批量添加业务系统(公共)
        List<TeamDict> list = new ArrayList<TeamDict>();
        for (String id : teamForm.getSource()) {
            TeamDict teamDict = new TeamDict();
            teamDict.setTeamDictTeamId(team.getTeamId());       //关联组id
            teamDict.setTeamDictDictId(id);                     //关联字典id
            teamDict.setTeamDictCreateUserId(user.getUserId());
            teamDict.setTeamDictCreateTime(new Date());
            list.add(teamDict);
        }
        dataGroupManageCuMapper.addTeamDict(list);           //保存关联信息是


        return "success";
    }


    public List getGroup(Team team){
        return dataGroupManageCuMapper.getGroup(team);
    }
    public List<TeamDict> getTeamDict(Team team){
        return dataGroupManageCuMapper.getTeamDict(team);
    }



    @Override
    public String deletes(List<String> ids) {
        for(String s:ids){
            Team team = new Team();
            team.setTeamId(s);
            dataGroupManageCuMapper.deletes(team);
        }
        return "success";
    }


}
