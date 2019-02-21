package com.dllg.ebpay.dao.custom;

import com.dllg.ebpay.model.generator.Team;

import java.util.List;

public interface TeamCuMapper {

    // 根据用户id查询分组
    List<Team> getTeamByUserId(String userId);
}
