package com.kabaddi.premier.league.domain.dao;

import com.kabaddi.premier.league.dto.Team;

/**
 * @author psanghan
 *
 */
public interface TeamDao {
	
	Team get(int id);

	Team createTeam(Team team);
}
