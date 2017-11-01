package com.ctrk.michca.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ctrk.michca.Response;
import com.ctrk.michca.adk.UserWrapper;
import com.ctrk.michca.util.RestDBBroker;

@RestController
@RequestMapping("/michca")
public class MichcaController extends HttpServlet{

	@Autowired
	private RestDBBroker firebase;
	
	private static final long serialVersionUID = 2L;
	
	@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
	public List<UserWrapper> getUser() {
		return firebase.getUsers();
	}
	
	@RequestMapping(value = "/createUser", method = RequestMethod.GET)
	public Response createUser() {
		firebase.createUser();
		return new Response("Created User.");
	}

}
