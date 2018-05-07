package com.kabaddi.premier.league.domain.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kabaddi.premier.league.domain.dao.impl.TeamDaoImpl;
import com.kabaddi.premier.league.domain.service.ILeagueSchedulerService;
import com.kabaddi.premier.league.domain.service.ITeamService;
import com.kabaddi.premier.league.dto.League;
import com.kabaddi.premier.league.dto.Match;
import com.kabaddi.premier.league.dto.Team;

/**
 * @author psanghan
 *
 */
@Service
public class LeagueSchedulerServiceImpl implements ILeagueSchedulerService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LeagueSchedulerServiceImpl.class);
	
	private Map<Long, League> productMap = new ConcurrentHashMap<Long, League>();
	
	@Override
	public League createLeague(String name, Date startDate) {
		League l = new League();
		l.setLeagueId(System.currentTimeMillis());//It will be always unique for now. not writing DAO layer.
		l.setName(name);
		l.setScheduleStartDate(startDate);
		productMap.put(l.getLeagueId(), l);
		return l;
	}


	@Override
	public League getLeague(Long leagueID) {
		// TODO Not writing DAO Layer
		return productMap.get(leagueID);
	}
	
	private List<Match> initializeMatches(List<Team> teams) {
		// Define matches to be return
		List<Match> matches = new ArrayList<Match>();

		// Initialize matches
		for (Team teamA : teams) {
			for (Team teamB : teams) {
				if (teamA.getId() != teamB.getId()) {
					Match match = new Match(teamA, teamB);
					match.setLocation(teamA.getCity());
					
					matches.add(match);
				}
			}
		}

		// Return list of matches
		return matches;
	}
	
	private boolean isMatchScheduleRemaining(List<Match> matches) {
		for (Match match : matches) {
			if (match.getDate() == null) {
				return true;
			}
		}
		return false;
	}
	
	private List<Integer> prepareParticipatedTeamList(List<Match> matches, Date date) {
		List<Integer> teamIds = new ArrayList<Integer>();
		for (Match match : matches) {
			if (match.getDate() != null) {
				if (date.equals(match.getDate())) {
					teamIds.add(match.getTeamA().getId());
					teamIds.add(match.getTeamB().getId());
				}
			}
		}
		return teamIds;
	}
	
	@Override
	public List<Match> generateSchedule(List<Team> teams, Date startDate) {
		// Generate matches from combination of teams
		List<Match> matches = initializeMatches(teams);
		

		// Define current date
		DateTime currentDate = new DateTime(startDate.getTime());
		
		// Loop till one of the match date has not been scheduled
		while (isMatchScheduleRemaining(matches)) {
			
			// Prepare already participated team id list
			List<Integer> participatedTeamIds = prepareParticipatedTeamList(matches, currentDate.minus(Period.days(1)).toDate());

			// Swap object in list 
			Random random = new Random();
			Collections.swap(matches, random.nextInt(matches.size()), random.nextInt(matches.size()));
			
			for (Match match : matches) {
				if (match.getDate() == null &&
						!participatedTeamIds.contains(match.getTeamA().getId()) &&
						!participatedTeamIds.contains(match.getTeamB().getId())) {
					match.setDate(currentDate.toDate());
					participatedTeamIds.add(match.getTeamA().getId());
					participatedTeamIds.add(match.getTeamB().getId());
				}
			}
			
			// Increment current date by 2 days
			currentDate = currentDate.plus(Period.days(1));
		}

		return matches;
	}
	
	public static void main(String[] args) {
		ITeamService teamRepo = new TeamServiceImpl(new TeamDaoImpl());
		
		List<Team> teamList = new ArrayList<Team>();
		teamList.add(teamRepo.fetch(1));
		teamList.add(teamRepo.fetch(2));
		teamList.add(teamRepo.fetch(3));
		teamList.add(teamRepo.fetch(4));
		teamList.add(teamRepo.fetch(5));
		
		
		/*ObjectMapper mapper = new ObjectMapper();

		//Object to JSON in file
		try {
			mapper.writeValue(new File("D:\\PDSWorkspace\\user.json"), teamList);
			String jsonInString = mapper.writeValueAsString(teamList);
			System.out.println("jsonInString="+jsonInString);
			
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Object to JSON in String
*/		
		List<Match> matches = new LeagueSchedulerServiceImpl().generateSchedule(teamList, new Date());
		LOGGER.debug(" --------------------------------------------------------------------------------------------------------------------------------------------");
		LOGGER.debug(" |{}|{}|{}|{}|", formatString("TEAM-A"), formatString("TEAM-B"), formatString("Location"), formatString("Date"));
		LOGGER.debug(" --------------------------------------------------------------------------------------------------------------------------------------------");
		for (Match match : matches) {
			LOGGER.debug(" |{}|{}|{}|{}|", formatString(match.getTeamA().getName()), formatString(match.getTeamB().getName()), formatString(match.getLocation()), formatString(formatDate(match.getDate())));
		}
		LOGGER.debug(" --------------------------------------------------------------------------------------------------------------------------------------------");
	}
	
	private static String formatDate(Date actualDate) {
		return new SimpleDateFormat("dd-MM-yyyy").format(actualDate);
	}
	
	private static String formatString(String actualString) {
		return StringUtils.rightPad(actualString, 20, "");
	}



	


}
