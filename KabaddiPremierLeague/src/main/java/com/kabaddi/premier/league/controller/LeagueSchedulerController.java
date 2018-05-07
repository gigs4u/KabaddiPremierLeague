package com.kabaddi.premier.league.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kabaddi.premier.league.domain.service.ILeagueSchedulerService;
import com.kabaddi.premier.league.domain.service.ITeamService;
import com.kabaddi.premier.league.dto.League;
import com.kabaddi.premier.league.dto.Match;
import com.kabaddi.premier.league.dto.Team;

@RestController
public class LeagueSchedulerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LeagueSchedulerController.class);

	private ITeamService teamServiceImpl;
	private ILeagueSchedulerService leagueSchedulerServiceImpl;

	@Autowired
	public LeagueSchedulerController(ITeamService teamServiceImpl, ILeagueSchedulerService leagueSchedulerServiceImpl) {
		this.teamServiceImpl = teamServiceImpl;
		this.leagueSchedulerServiceImpl = leagueSchedulerServiceImpl;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/league/{leagueID}/schedule", produces = { MediaType.APPLICATION_JSON_VALUE })
	// http://localhost:8080/schedule?teamIds=1,2,3,4,5&startDate=20160218
	List<Match> generateSchedule(@PathVariable(value = "leagueID") Long leagueID){
		/*(@RequestParam(value = "teamIds", defaultValue = "1,2,3,4,5") String teamIds,
				@RequestParam(value = "startDate", defaultValue = "20160218") String startDate) {*/
		LOGGER.info("Test Generate Schedule");
		/*
		// Validation of query parameter
		Assert.notNull(teamIds, "teamIds Query Parameter is missing");
		Assert.notNull(startDate, "startDate Query Parameter is missing");

		// Split teamIds using comma
		String[] teamIdArray = StringUtils.split(teamIds, ",");

		// Format start date
		Date scheduleStartDate = parseDate(startDate);

		// Prepare temId list
		List<Team> teams = new ArrayList<Team>();
		for (String teamId : teamIdArray) {

			if (StringUtils.isNumeric(teamId)) {
				teams.add(teamServiceImpl.fetch(Integer.valueOf(teamId)));
			} else {
				throw new RuntimeException("Team Id should be numeric");
			}
		}*/
		
		League l = leagueSchedulerServiceImpl.getLeague(leagueID);
		if(l!=null){
			return leagueSchedulerServiceImpl.generateSchedule(l.getParticipatingTeams(), l.getScheduleStartDate());
		}else{
			throw new RuntimeException("No Such League present check League Id");
		}
		
		
	}

	private Date parseDate(String startDate) {
		DateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
		try {
			return dateFormatter.parse(startDate);
		} catch (ParseException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/league", produces = { MediaType.APPLICATION_JSON_VALUE })
	public League createLeague(@RequestParam(value = "name", defaultValue = "ProKabaddiLeague") String name,
			@RequestParam(value = "startDate", defaultValue = "20200218") String startDate){
		LOGGER.info("createLeague with name "+name+" Starting from"+startDate);
		// Format start date
		Date scheduleStartDate = parseDate(startDate);
		if(scheduleStartDate.before(new Date())){
			throw new RuntimeException("Start Date cannot be past date");
		}
		return leagueSchedulerServiceImpl.createLeague(name, scheduleStartDate);
	}
	
	
	@RequestMapping(method = RequestMethod.POST, path = "/league/{leagueID}/registerTeamToLeague", produces = MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public League registerTeamToLeague(@PathVariable(value = "leagueID") Long leagueID, @RequestBody List<Team> teams){
		LOGGER.info("registerTeamToLeague with name "+teams.size());
		
		League l = leagueSchedulerServiceImpl.getLeague(leagueID);
		if(l!=null){
			if(l.getScheduleStartDate().after(new Date())){
				teamServiceImpl.createTeams(teams);
				l.getParticipatingTeams().addAll(teams);
				return l;
			}else{
				throw new RuntimeException("League already started");	
			}
		}else{
			throw new RuntimeException("No Such League present check League Id");
		}
		
	}
	
}
