package com.aws.codestar.projecttemplates.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aws.codestar.SecurityUtil;
import com.aws.codestar.adk.User;
import com.aws.codestar.data.temp.Temporary;
import com.aws.codestar.projecttemplates.Response;

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
	
	private boolean isUserLoggedIn(HttpServletRequest req, User user) {
		if(null != req.getSession(false)) {
			HttpSession data = req.getSession(false);//.getAttribute("user").toString();
			System.out.println("1111111111::::" + data.getAttributeNames());
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

	@RequestMapping(value = "/passwordEncrypr", method = RequestMethod.GET)
	public Response passwordEncrypr(@RequestParam(value = "pass") String pass) {
		return new Response(SecurityUtil.encrypt(pass));
	}

	@RequestMapping(value = "/isValidSession", method = RequestMethod.GET)
	public Response isValidSession(HttpServletRequest req) {
		Object userLoggedInName = req.getSession().getAttribute("user");
		if(null == userLoggedInName) {
			userLoggedInName = "No User Logged In";
		} else {
			userLoggedInName = "User logged in is " + (String) userLoggedInName;
		}
		return new Response(userLoggedInName.toString());
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public Response logout() {
//		Object logOutMessage = req.getSession().getAttribute("user");
//		Temporary.USERS_LOGGED_IN.remove(logOutMessage.toString());
		return new Response("hi"); 
	}
}