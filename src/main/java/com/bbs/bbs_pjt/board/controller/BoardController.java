package com.bbs.bbs_pjt.board.controller;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bbs.bbs_pjt.board.Board;
import com.bbs.bbs_pjt.board.dao.BoardDao;
import com.bbs.bbs_pjt.board.service.BoardService;
import com.bbs.bbs_pjt.commons.paging.Criteria;
import com.bbs.bbs_pjt.commons.paging.PageMaker;

@Controller
public class BoardController {

	@Autowired
	BoardService service;
	
	@RequestMapping( "/board" )
	public String moveBoard( Model model ) {
		ArrayList< Board >list = service.getList( 1 ); 
		model.addAttribute( "boardList", list );
		return "board";
	}
	
	@RequestMapping(value = "/posting_view", method = RequestMethod.GET, params = {"boardID"})
	public String view( Model model, @RequestParam(value="boardID", required = true) int boardID) {

		model.addAttribute( "board", service.getBoard( boardID ) );
		return "posting_view";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.GET )
	public String write( Model model, Board boardID, HttpSession session ) {
		
		return "write";
	}
	
	@RequestMapping(value = "/writeAction", method = RequestMethod.POST )
	public String writeAction( Model model, Board board, HttpSession session ) {

		int result = service.Write( (String)session.getAttribute( "userID" ), board.getBoardTitle(), board.getBoardContent() );
		if ( result == -1 ) {
			model.addAttribute("msg", "오류가 있습니다."); 
			model.addAttribute("url", "wirte"); 
			return "redirect";
		}
		return "writeOK";
	}
	
	
	@RequestMapping(value = "/update", method = RequestMethod.GET, params = {"boardID"})
	public String update( Model model, @RequestParam(value="boardID", required = true) int boardID) {

		model.addAttribute( "board", service.getBoard( boardID ) );
		return "update";
	}
	@RequestMapping(value = "/updateAction", method = RequestMethod.POST, params = {"boardID"}) // ?
	public String updateAction( Model model, Board board ) {
		int result = service.Update( board.getBoardID(), board.getBoardTitle(), board.getBoardContent() );
		if ( result == -1 ){
			model.addAttribute("msg", "오류가 있습니다."); 
			model.addAttribute("url", "update"); 
			return "redirect";
		}
		return "updateOK";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET, params = {"boardID"})
	public String delete( Model model, @RequestParam(value="boardID", required = true) int boardID) {

		service.Delete( boardID );
		return "deleteOK";
	}
	
	@RequestMapping( value = "/listPage", method = RequestMethod.GET ) 
		public String listPage( @ModelAttribute( "cri" ) Criteria cri, Model model ) throws Exception{
		
			model.addAttribute( "list", service.listCriteria( cri ) );
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCriteria( cri );
			pageMaker.setTotalCount( service.listCountCriteria( cri ) );
			
			model.addAttribute( "pageMaker", pageMaker );
			
			return "board";
		
	}
	
}
