/**
 * 
 */
package com.kabaddi.premier.league.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author psanghan
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
		{ 
	"leagueId",
	"name",
	"scheduleStartDate",	 
	"participatingTeams", 
	"matches" }
		)
public class League {
	
	@JsonProperty("leagueId")	
	private long leagueId;
	@JsonProperty("name")
	private String name;
	@JsonProperty("scheduleStartDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date scheduleStartDate;
	@JsonProperty("participatingTeams")
	private List<Team> participatingTeams = new ArrayList<Team>();
	@JsonProperty("matches")
	private List<Match> matches;
	/**
	 * @return the leagueId
	 */
	@JsonProperty("leagueId")
	public long getLeagueId() {
		return leagueId;
	}
	/**
	 * @param leagueId the leagueId to set
	 */
	@JsonProperty("leagueId")
	public void setLeagueId(long leagueId) {
		this.leagueId = leagueId;
	}
	/**
	 * @return the scheduleStartDate
	 */
	@JsonProperty("scheduleStartDate")
	public Date getScheduleStartDate() {
		return scheduleStartDate;
	}
	/**
	 * @param scheduleStartDate the scheduleStartDate to set
	 */
	@JsonProperty("scheduleStartDate")
	public void setScheduleStartDate(Date scheduleStartDate) {
		this.scheduleStartDate = scheduleStartDate;
	}
	/**
	 * @return the participatingTeams
	 */
	@JsonProperty("participatingTeams")
	public List<Team> getParticipatingTeams() {
		return participatingTeams;
	}
	/**
	 * @param participatingTeams the participatingTeams to set
	 */
	@JsonProperty("participatingTeams")
	public void setParticipatingTeams(List<Team> participatingTeams) {
		this.participatingTeams = participatingTeams;
	}
	/**
	 * @return the matches
	 */
	@JsonProperty("matches")
	public List<Match> getMatches() {
		return matches;
	}
	/**
	 * @param matches the matches to set
	 */
	@JsonProperty("matches")
	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}
	/**
	 * @return the name
	 */
	@JsonProperty("name")
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	
}
