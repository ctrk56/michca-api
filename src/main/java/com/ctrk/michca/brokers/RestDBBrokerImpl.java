package com.ctrk.michca.brokers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ctrk.michca.adk.Club;
import com.ctrk.michca.adk.Competition;
import com.ctrk.michca.adk.CompetitionDetails;
import com.ctrk.michca.adk.Draw;
import com.ctrk.michca.adk.User;
import com.ctrk.michca.util.RestTemplateUtil;
import com.ctrk.michca.util.SSLUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RestDBBrokerImpl {

	private final static Logger log = LoggerFactory.getLogger(RestDBBrokerImpl.class);

	private static final String RESTDB_URL = "https://michca-114b.restdb.io/";
	private static final String USERS_URL = RESTDB_URL+"rest/michcausers";
	private static final String RESTDB_REST_URL = RESTDB_URL+"rest/";
//	private static final String MEDIA_URL = "https://michca-114b.restdb.io/media";
	private RestTemplate restTemplate = null;

	RestDBBrokerImpl() {
		restTemplate = RestTemplateUtil.getRESTDBIORestTemplate(); 
		
		try {
			SSLUtil.turnOffSslChecking();
		} catch (Exception e) {
			log.error("Error turning off SSL checking", e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public List<User> getUsers() {
		List<User> result = restTemplate.getForObject(USERS_URL, List.class);
		return new ObjectMapper().convertValue(result, new TypeReference<List<User>>() {});
	}


	public void createNewUser() {
		User user2 = new User();
		user2.setName("name2");
		user2.setUserId("userId2");
		user2.setPassword("password2");
		user2.setRoles(new String[]{"USER"});

		HttpEntity<User> request = new HttpEntity<User>(user2);
		restTemplate.postForObject(USERS_URL, request, User.class);
	}

	@SuppressWarnings("unchecked")
	public List<Competition> getCompetitions() {
		List<User> result = restTemplate.getForObject("https://michca-114b.restdb.io/rest/competition", List.class);
		return new ObjectMapper().convertValue(result, new TypeReference<List<Competition>>() {});
	}
	
	public void competitionsSync(List<Competition> competitions) {
		for(Competition c : competitions) {
			HttpEntity<Competition> request = new HttpEntity<Competition>(c);
			restTemplate.postForObject(RESTDB_REST_URL+"competition", request, Competition.class);
		}
	}

	public void competitionDetailsSync(CompetitionDetails competitionDetails) {
		HttpEntity<CompetitionDetails> request = new HttpEntity<CompetitionDetails>(competitionDetails);
		restTemplate.postForObject(RESTDB_REST_URL+"competitiondetails", request, CompetitionDetails.class);
	}

	public void drawsSync(List<Draw> draws) {
		for(Draw d : draws) {
			HttpEntity<Draw> request = new HttpEntity<Draw>(d);
			restTemplate.postForObject(RESTDB_REST_URL+"draw", request, Draw.class);
		}
	}
	
	public void clubsSync(List<Club> clubs) {
		for(Club c : clubs) {
			HttpEntity<Club> request = new HttpEntity<Club>(c);
			restTemplate.postForObject(RESTDB_REST_URL+"club", request, Club.class);
		}
	}
}
