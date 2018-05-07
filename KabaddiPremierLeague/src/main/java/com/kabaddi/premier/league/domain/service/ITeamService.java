package com.kabaddi.premier.league.domain.service;

import java.util.List;

import com.kabaddi.premier.league.dto.Team;

/**
 * @author psanghan
 *
 */
public interface ITeamService {
	
	Team fetch(int id);
	public Team createTeam(Team team);
	public List<Team> createTeams(List<Team> teams);
}
