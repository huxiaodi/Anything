package com.dllg.ebpay.dao.custom;

import com.dllg.ebpay.dao.generator.DictMapper;
import com.dllg.ebpay.model.generator.Dict;
import com.dllg.ebpay.model.generator.Team;
import com.dllg.ebpay.model.generator.TeamDict;
import com.dllg.ebpay.model.request.QueryParams;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataGroupManageCuMapper extends DictMapper {

    /**
     * 列表
     */
    List manageList(QueryParams queryParams);
    int deletes(Team Team);

    int addGroup(Team team);
    int updateGroup(Team team);

    int addTeamDict(@Param("list") List<TeamDict> list);
    int deleteTeamDict(@Param("list") List<TeamDict> list);

    List getGroup(Team team);
    List<TeamDict> getTeamDict(Team team);


}
