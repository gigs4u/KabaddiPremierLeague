package com.kabaddi.premier.league.domain.dao.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Repository;

import com.kabaddi.premier.league.domain.dao.TeamDao;
import com.kabaddi.premier.league.dto.Team;

/**
 * @author psanghan
 *
 */
@Repository
public class TeamDaoImpl implements TeamDao {
	
	private Map<Integer, Team> teamMap = new ConcurrentHashMap<Integer, Team>();
	AtomicInteger seqId = new AtomicInteger(5);
	public TeamDaoImpl() {
		initializeTeam();
	}
	
	// TODO : As soon as DB supports get added below method should get removed
	private void initializeTeam() {
		teamMap.put(1, prepareTeam(1, "Pune - Warrior", "Pune"));
		teamMap.put(2, prepareTeam(2, "Mumbai - Maratha",  "Mumbai"));
		teamMap.put(3, prepareTeam(3, "Delhi - Devill",  "Delhi"));
		teamMap.put(4, prepareTeam(4, "Chandigadh - King", "Chandigadh"));
		teamMap.put(5, prepareTeam(5, "Patna - Bihari",  "Patna"));
	}
	
	private Team prepareTeam(int id, String name, String city) {
		Team team = new Team(id);
		team.setName(name);
		team.setCity(city);
		
		return team;
	}

	@Override
	public Team get(int id) {
		// TODO : As soon as DB supports get added team information should get fetched from DB i.e. TEAM
		return teamMap.get(id);
	}

	@Override
	public Team createTeam(Team team) {
		team.setId(seqId.incrementAndGet());
		teamMap.put(team.getId(), team);
		return team;
	}

}
