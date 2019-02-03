package com.bbs.bbs_pjt.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bbs.bbs_pjt.user.service.UserService;

@Controller
public class UserController {
	UserService service;
	
	@RequestMapping( "/join" )
	public String userJoin( Model model, HttpServletRequest request ) {
		String userID = request.getParameter( "userID" );
		String userPassword = request.getParameter( "userPassword" );
		String userName = request.getParameter( "userName" );
		String userGender = request.getParameter( "userGender" );
		String userEmail = request.getParameter( "userEmail" );
		
		return "join";
	}
	
	@RequestMapping( "/login" )
	public String login( Model model ) {
		
		model.addAttribute( "loginKey", "loginValue" );
		
		return "login";
	}
	

}
