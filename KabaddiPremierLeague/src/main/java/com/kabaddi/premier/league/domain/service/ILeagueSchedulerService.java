package com.kabaddi.premier.league.domain.service;

import java.util.Date;
import java.util.List;

import com.kabaddi.premier.league.dto.League;
import com.kabaddi.premier.league.dto.Match;
import com.kabaddi.premier.league.dto.Team;

/**
 * @author psanghan
 *
 */
public interface ILeagueSchedulerService {
	
	List<Match> generateSchedule(List<Team> teamList, Date startDate);
	
	public League createLeague(String name, Date startDate);

	League getLeague(Long leagueID);
	
	
	
}
