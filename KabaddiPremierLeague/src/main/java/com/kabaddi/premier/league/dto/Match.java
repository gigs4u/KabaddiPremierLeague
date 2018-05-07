package com.kabaddi.premier.league.dto;

import java.util.Date;

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
	"teamA", 
	"teamB",	 
	"location", 
	"date" }
		)
public class Match {

	@JsonProperty("teamA")
	private final Team teamA;
	@JsonProperty("teamB")
	private final Team teamB;
	@JsonProperty("location")
	private String location;
	@JsonProperty("date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")//@HH:mm:ss.SSSZ
	private Date date;

	public Match(Team teamA, Team teamB) {
		this.teamA = teamA;
		this.teamB = teamB;
	}
	@JsonProperty("location")
	public String getLocation() {
		return location;
	}
	@JsonProperty("location")
	public void setLocation(String location) {
		this.location = location;
	}
	@JsonProperty("date")
	public Date getDate() {
		return date;
	}
	@JsonProperty("date")
	public void setDate(Date date) {
		this.date = date;
	}
	@JsonProperty("teamA")
	public Team getTeamA() {
		return teamA;
	}
	@JsonProperty("teamB")
	public Team getTeamB() {
		return teamB;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Match [teamA=");
		builder.append(teamA);
		builder.append(", teamB=");
		builder.append(teamB);
		builder.append(", location=");
		builder.append(location);
		builder.append(", date=");
		builder.append(date);
		builder.append("]");
		return builder.toString();
	}

}
