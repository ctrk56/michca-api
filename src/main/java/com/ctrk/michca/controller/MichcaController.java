package com.ctrk.michca.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ctrk.michca.Response;
import com.ctrk.michca.adk.Club;
import com.ctrk.michca.adk.Competition;
import com.ctrk.michca.adk.CompetitionDetails;
import com.ctrk.michca.adk.Draw;
import com.ctrk.michca.adk.User;
import com.ctrk.michca.brokers.CricHQBrokerImpl;
import com.ctrk.michca.brokers.RestDBBrokerImpl;

@RestController
@RequestMapping("/michca")
public class MichcaController extends HttpServlet{

	@Autowired
	private RestDBBrokerImpl firebase;

	@Autowired
	private CricHQBrokerImpl cricHQ;
	
	private static final long serialVersionUID = 2L;
	
	@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
	public List<User> getUsers() {
		return firebase.getUsers();
	}
	
	@RequestMapping(value = "/createNewUser", method = RequestMethod.GET)
	public Response createNewUser() {
		firebase.createNewUser();
		return new Response("Created User.");
	}

	@RequestMapping(value = "/cricHQLogin", method = RequestMethod.GET)
	public Response cricHQLogin() {
		String message = cricHQ.login();
		return new Response("CricHQ Login Successfull::" + message);
	}

	@RequestMapping(value = "/syncCompetitions", method = RequestMethod.GET)
	public List<Competition> syncCompetitions() {
		List<Competition> competitions = cricHQ.getCompetitions();
		firebase.competitionsSync(competitions);
		return competitions;
	}

	@RequestMapping(value = "/syncCompetitionDetails", method = RequestMethod.GET)
	public Response syncCompetitionDetails() {
		for(Competition c : firebase.getCompetitions()) {
			CompetitionDetails competitionDetails = cricHQ.getCompetitionDetails(c.getMichcaId());
			firebase.competitionDetailsSync(competitionDetails);
		}
		return new Response("Successfully synced competition details");
	}

	@RequestMapping(value = "/syncDraws", method = RequestMethod.GET)
	public Response syncDraws() {
		for(Competition c : firebase.getCompetitions()) {
			List<Draw> draws = cricHQ.getDraws(c.getMichcaId());
			firebase.drawsSync(draws);
		}
		return new Response("Successfully synced competition draws");
	}

	@RequestMapping(value = "/syncClubs", method = RequestMethod.GET)
	public List<Club> syncClubs() {
		List<Club> clubs = cricHQ.getClubs();
		firebase.clubsSync(clubs);
		return clubs;
	}

}
