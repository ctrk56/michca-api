package com.ctrk.michca.adk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Draw extends Base {
	private String competition_id;
	private int teams;
	private String season;
	private boolean archived;
	private boolean published;
	private int rounds;
	private String draw_type;
	private int grounds;

	public int getTeams() {
		return teams;
	}

	public void setTeams(int teams) {
		this.teams = teams;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public boolean isArchived() {
		return archived;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public int getRounds() {
		return rounds;
	}

	public void setRounds(int rounds) {
		this.rounds = rounds;
	}

	public String getDraw_type() {
		return draw_type;
	}

	public void setDraw_type(String draw_type) {
		this.draw_type = draw_type;
	}

	public int getGrounds() {
		return grounds;
	}

	public void setGrounds(int grounds) {
		this.grounds = grounds;
	}

	public String getCompetition_id() {
		return competition_id;
	}

	public void setCompetition_id(String competition_id) {
		this.competition_id = competition_id;
	}
}
