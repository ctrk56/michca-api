package com.ctrk.michca.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ctrk.michca.Response;
import com.ctrk.michca.adk.User;
import com.ctrk.michca.data.temp.Temporary;
import com.ctrk.michca.util.SecurityUtil;

@RestController
@RequestMapping("/")
public class HelloWorldController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	private static final String MESSAGE_FORMAT = "Hello %s!";

	@RequestMapping(value = "/data", method = RequestMethod.GET)
	public Response helloWorldGet(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Response(String.format(MESSAGE_FORMAT, name));
	}

	@RequestMapping(value = "/data", method = RequestMethod.POST)
	public Response helloWorldPost(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Response(String.format(MESSAGE_FORMAT, name));
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Response login(@RequestBody User user, HttpServletRequest req, HttpServletResponse res) {
		org.springframework.security.core.userdetails.User userFound = Temporary.USERS.get(user.getName());
		String result = "Login Success";
		boolean isUserLoggedIn = isUserLoggedIn(req, user);
		
		if(null != userFound && !isUserLoggedIn) {
			createSession(req, res, user);
		} else {
			result = "Login Failed";
		}
		return new Response(result);
	}
	
	@RequestMapping(value = "/passwordEncrypr", method = RequestMethod.GET)
	public Response passwordEncrypr(@RequestParam(value = "pass") String pass) {
		return new Response(SecurityUtil.encrypt(pass));
	}

	private boolean isUserLoggedIn(HttpServletRequest req, User user) {
		if(null != req.getSession(false)) {
			HttpSession data = req.getSession(false);//.getAttribute("user").toString();
		}
		boolean result = false;
		Boolean flag = Temporary.USERS_LOGGED_IN.get(user.getName());
		if(flag != null) {
			req.getSession(false).setAttribute("user", user.getName());
			result = (boolean) flag;
		}
		return result;
	}

	private void createSession(HttpServletRequest req, HttpServletResponse res, User user) {
		HttpSession session = req.getSession(true);
		session.setAttribute("user", user.getName());
		session.setMaxInactiveInterval(30*60);
		Temporary.USERS_LOGGED_IN.put(user.getName(), Boolean.TRUE);
	}
}