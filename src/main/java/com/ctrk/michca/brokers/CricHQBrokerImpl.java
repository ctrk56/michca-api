package com.ctrk.michca.brokers;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ctrk.michca.adk.Club;
import com.ctrk.michca.adk.Competition;
import com.ctrk.michca.adk.CompetitionDetails;
import com.ctrk.michca.adk.Draw;
import com.ctrk.michca.adk.wrapper.ClubsWrapper;
import com.ctrk.michca.adk.wrapper.CompetitionsWrapper;
import com.ctrk.michca.adk.wrapper.DrawsWrapper;
import com.ctrk.michca.util.RestTemplateUtil;
import com.ctrk.michca.util.SSLUtil;

@Component
public class CricHQBrokerImpl {

	private static final String COMPETITIONS_PATH = "api/v2/private/competitions/";
	private static final String CLUBS_PATH = "api/v2/private/clubs?per_page=70";
	private final static Logger log = LoggerFactory.getLogger(CricHQBrokerImpl.class);
	private RestTemplate restTemplate = null;
	private static final String URL = "https://www.crichq.com/";
	private HttpHeaders headers = new HttpHeaders();
	
	CricHQBrokerImpl() {
		restTemplate = RestTemplateUtil.getBasicRestTemplate(); 
		try {
			SSLUtil.turnOffSslChecking();
		} catch (Exception e) {
			log.error("Error turning off SSL checking", e.getMessage());
		}
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	}
	
	public String login() {
		String path = "users/sign_in";
		String body = "{\"email\":\"micricketstats@gmail.com\",\"password\":\"michca2017!\",\"remember_me\":[],\"user\":{\"email\":\"micricketstats@gmail.com\",\"password\":\"michca2017!\",\"remember_me\":[]}}\n";
		
		HttpEntity<String> request = new HttpEntity<String>(body, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(URL+path, request, String.class);
		headers.set("Cookie", response.getHeaders().get("Set-Cookie").toString());
		return response.getBody();
	}

	public List<Competition> getCompetitions() {
		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<CompetitionsWrapper> response = restTemplate.exchange(URL + COMPETITIONS_PATH, HttpMethod.GET, request, CompetitionsWrapper.class);
		return response.getBody().getCompetitions();
	}
	
	public CompetitionDetails getCompetitionDetails(String competitionId) {
		String path = COMPETITIONS_PATH + competitionId;
		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<CompetitionDetails> response = restTemplate.exchange(URL + path, HttpMethod.GET, request, CompetitionDetails.class);
		return response.getBody();
	}

	public List<Draw> getDraws(String competitionId) {
		String path = COMPETITIONS_PATH + competitionId + "/draws";
		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<DrawsWrapper> response = restTemplate.exchange(URL + path, HttpMethod.GET, request, DrawsWrapper.class);
		return response.getBody().getDraws();
	}
	
	public List<Club> getClubs() {
		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<ClubsWrapper> response = restTemplate.exchange(URL + CLUBS_PATH, HttpMethod.GET, request, ClubsWrapper.class);
		return response.getBody().getClubs();
	}

}
