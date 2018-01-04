package com.ctrk.michca.adk.wrapper;

import java.util.List;

import com.ctrk.michca.adk.Competition;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CompetitionsWrapper {
	
	private List<Competition> competitions;

	public List<Competition> getCompetitions() {
		return competitions;
	}

	public void setCompetitions(List<Competition> competitions) {
		this.competitions = competitions;
	}
}
