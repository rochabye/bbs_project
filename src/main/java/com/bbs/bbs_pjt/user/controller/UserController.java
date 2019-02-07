package com.bbs.bbs_pjt.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	public String userJoin() {
		return "join";
	}
	@RequestMapping( value="/joinAction", method = RequestMethod.POST )
	public String userJoinAction( User user ) {
		service.userRegister( user.getUserID(), user.getUserPassword(), user.getUserName(), 
				user.getUserGender(), user.getUserEmail() );
		
		return "joinOk";
	}
	
	@RequestMapping( "/login" )
	public String login() {
		return "login";
	}
	
	@RequestMapping( value="/loginAction", method = RequestMethod.POST )
	public String userLoginAction( Model model, User user, HttpSession session ) {
		
		int result = service.userSearch( user.getUserID(), user.getUserPassword() );
		if ( result == 1 ){
			session.setAttribute( "userID", user.getUserID() );
			return "loginOk";
 		}
 		else if ( result == 0 ) {
 			model.addAttribute("msg", "비밀번호가 틀렸습니다."); 
 			model.addAttribute("url", "login"); 
 			return "redirect";
 			// wrong password 
 		}
 		else if ( result == -1 ) {
 			model.addAttribute("msg", "아이디가 존재하지 않습니다."); 
 			model.addAttribute("url", "login"); 
 			return "redirect";
 			// no id 
 		}
 		else if ( result == -2 ) {
 			model.addAttribute("msg", "데이터베이스 오류가 발생했습니다."); 
 			model.addAttribute("url", "login"); 
 			return "redirect";
 			// database error 
 		}
		model.addAttribute("msg", "알 수 없는 오류가 발생했습니다."); 
		model.addAttribute("url", "login"); 
		return "redirect";
	}
	
	@RequestMapping( "/logout" )
	public String logout( HttpSession session ) {
		session.invalidate();
		return "main";
	}
	
}
