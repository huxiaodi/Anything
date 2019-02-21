package com.dllg.ebpay.service.team.impl;

import com.dllg.ebpay.dao.generator.TeamMapper;
import com.dllg.ebpay.model.generator.Team;
import com.dllg.ebpay.model.generator.TeamExample;
import com.dllg.ebpay.service.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamMapper teamMapper;

    @Override
    public List<Team> getAllTeams() {
        TeamExample teamExample = new TeamExample();
        teamExample.createCriteria().andTeamIsDeleteEqualTo(false);
        return teamMapper.selectByExample(teamExample);
    }
}
