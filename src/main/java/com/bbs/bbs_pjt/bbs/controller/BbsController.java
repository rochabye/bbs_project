package com.bbs.bbs_pjt.bbs.controller;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bbs.bbs_pjt.bbs.Bbs;
import com.bbs.bbs_pjt.bbs.dao.BbsDao;
import com.bbs.bbs_pjt.bbs.service.BbsService;

@Controller
public class BbsController {

	@Autowired
	BbsService service;
	
	@RequestMapping( "/bbs" )
	public String moveBbs( Model model ) {
		BbsDao dao = new BbsDao();
		ArrayList< Bbs >list = dao.bbsGetList( 1 ); 
		model.addAttribute( "bbs_list", list );
		return "bbs";
	}
}
