package com.ctrk.michca.controller;

import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ctrk.michca.Response;
import com.ctrk.michca.adk.User;
import com.ctrk.michca.util.Firebase;

@RestController
@RequestMapping("/michca")
public class MichcaController extends HttpServlet{

	@Autowired
	private Firebase firebase;
	
	private static final long serialVersionUID = 2L;
	
	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	public Response getUser() {
		User user = firebase.getData("http://api.myjson.com/bins/7fgwv");
		String message = "Cannot retrieve data.";
		if(null != user) {
			message = "Data retrieved Successfully.";
		} 
		return new Response(message);
	}
	
	@RequestMapping(value = "/createUser", method = RequestMethod.GET)
	public Response createUser() {
		firebase.createUser();
		return new Response("Created User.");
	}

}
