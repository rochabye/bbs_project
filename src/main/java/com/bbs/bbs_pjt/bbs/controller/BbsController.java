package com.bbs.bbs_pjt.bbs.controller;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bbs.bbs_pjt.bbs.Bbs;
import com.bbs.bbs_pjt.bbs.dao.BbsDao;
import com.bbs.bbs_pjt.bbs.service.BbsService;

@Controller
public class BbsController {

	@Autowired
	BbsService service;
	
	@RequestMapping( "/bbs" )
	public String moveBbs( Model model ) {
		ArrayList< Bbs >list = service.getList( 1 ); 
		model.addAttribute( "bbsList", list );
		return "bbs";
	}
	
	@RequestMapping(value = "/bbs_view", method = RequestMethod.GET, params = {"bbsID"})
	public String view( Model model, @RequestParam(value="bbsID", required = true) int bbsID) {

		model.addAttribute( "bbs", service.getBbs( bbsID ) );
		return "bbs_view";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET, params = {"bbsID"})
	public String update( Model model, @RequestParam(value="bbsID", required = true) int bbsID) {

		model.addAttribute( "bbs", service.getBbs( bbsID ) );
		return "update";
	}
	@RequestMapping(value = "/updateAction", method = RequestMethod.POST, params = {"bbsID"})
	public String updateAction( Model model, Bbs bbs ) {
		int result = service.Update( bbs.getBbsID(), bbs.getBbsTitle(), bbs.getBbsContent() );
		if ( result == -1 ){
			model.addAttribute("msg", "오류가 있습니다."); 
			model.addAttribute("url", "update"); 
			return "redirect";
		}
		return "updateOK";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET, params = {"bbsID"})
	public String delete( Model model, @RequestParam(value="bbsID", required = true) int bbsID) {

		service.Delete( bbsID );
		return "deleteOK";
	}
	
}
