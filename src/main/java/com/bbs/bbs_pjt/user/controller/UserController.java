package com.bbs.bbs_pjt.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bbs.bbs_pjt.user.User;
import com.bbs.bbs_pjt.user.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService service;
	
	@RequestMapping( "/join" )
	public String userJoin( Model model, HttpServletRequest request ) {
		return "join";
	}
	@RequestMapping( value="/joinAction", method = RequestMethod.POST )
	public String userJoinAction( User user ) {
		System.out.print( "join action" ) ;
		service.userRegister( user.getUserID(), user.getUserPassword(), user.getUserName(), 
				user.getUserGender(), user.getUserEmail() );
		
		return "joinOk";
	}
	
	@RequestMapping( "/login" )
	public String login( Model model, HttpServletRequest request ) {
		System.out.print( "login!!" ) ;
		return "login";
	}
	
	@RequestMapping( value="/loginAction", method = RequestMethod.POST )
	public String userLoginAction( User user ) {
		System.out.print( "login action " + user.getUserID() + user.getUserPassword()  ) ;
		int result = service.userSearch( user.getUserID(), user.getUserPassword() );
		System.out.print( "!!result " + result ) ;
		if ( result == 1 ){
			return "loginOk";
 			// login success. set session. go main 
 			//session.setAttribute("userID", user.getUserID());
 		}
 		else if ( result == 0 ) {
 			// wrong password 
 		}
 		else if ( result == -1 ) {
 			// no id 
 		}
 		else if ( result == -2 ) {
 			// database error 
 		}
		return "loginOK";
	}
}
