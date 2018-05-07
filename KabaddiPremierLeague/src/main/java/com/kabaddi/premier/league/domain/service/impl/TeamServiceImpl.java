package com.kabaddi.premier.league.domain.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.kabaddi.premier.league.domain.dao.TeamDao;
import com.kabaddi.premier.league.domain.service.ITeamService;
import com.kabaddi.premier.league.dto.Team;

/**
 * @author psanghan
 *
 */
@Service
public class TeamServiceImpl implements ITeamService {
	
	@Autowired
	private final TeamDao teamDao;
	
	public TeamServiceImpl(TeamDao teamDao) {
		this.teamDao = teamDao;
	}

	@Override
	public Team fetch(int id) {
		// Fetch Team information from DAO
		Team team = teamDao.get(id);
		Assert.notNull(team, "Team does not exist for id -> "+id);
		
		return team;
	}
	
	public Team createTeam(Team team){
		return teamDao.createTeam(team);
	}
	
	public List<Team> createTeams(List<Team> teams){
		List<Team> ts = new ArrayList<Team>();
		for (Team team2 : teams) {
			ts.add(createTeam(team2));
		}
		return ts;
	}
}
