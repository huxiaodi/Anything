package com.dllg.ebpay.service.dataGroup;

import com.dllg.ebpay.model.form.TeamForm;
import com.dllg.ebpay.model.generator.Dict;
import com.dllg.ebpay.model.generator.Team;
import com.dllg.ebpay.model.generator.TeamDict;
import com.dllg.ebpay.model.request.QueryParams;
import com.dllg.framework.model.AuthUser;

import java.util.List;

public interface DataGroupManageService {

    List manageList(QueryParams queryParams);

    String save(TeamForm team, AuthUser user);
    //String add(TeamForm team);
    //String update(TeamForm team);

    String deletes(List<String> ids);

    List getGroup(Team team);
    List<TeamDict> getTeamDict(Team team);

}
