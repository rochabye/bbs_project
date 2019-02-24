package com.bbs.bbs_pjt.article.controller;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bbs.bbs_pjt.article.Article;
import com.bbs.bbs_pjt.article.service.ArticleService;
import com.bbs.bbs_pjt.commons.paging.Criteria;
import com.bbs.bbs_pjt.commons.paging.PageMaker;

@Controller
public class ArticleController {

	@Autowired
	ArticleService service;
	
	
	@RequestMapping( value = "/list", method = RequestMethod.GET ) 
	public String list( @ModelAttribute( "cri" ) Criteria cri, Model model, 
			@RequestParam(value="page", required = false, defaultValue="0") int page, 
			@RequestParam(value="perPageNum", required = false, defaultValue="0") int perPageNum ) throws Exception{

		System.out.println( cri.toString() );
		model.addAttribute( "articleList", service.listCriteria( cri ) );
		ArrayList< Article > list = service.listCriteria( cri );
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria( cri );
		pageMaker.setTotalCount( service.listCountCriteria( cri ) );
		
		model.addAttribute( "pageMaker", pageMaker );
		
		return "list";
	}
	
	@RequestMapping(value = "/article_view", method = RequestMethod.GET, params = {"articleNo"})
	public String view( Model model, @RequestParam(value="articleNo", required = true) int articleNo) {

		model.addAttribute( "article", service.getArticle( articleNo ) );
		return "article_view";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.GET )
	public String write( Article articleNo, HttpSession session ) {
		
		return "write";
	}
	
	@RequestMapping(value = "/writeAction", method = RequestMethod.POST )
	public String writeAction( Model model, Article article, HttpSession session ) {

		int result = service.Write( (String)session.getAttribute( "userID" ), article.getArticleTitle(), article.getArticleContent() );
		if ( result == -1 ) {
			model.addAttribute("msg", "오류가 있습니다."); 
			model.addAttribute("url", "wirte"); 
			return "redirect";
		}
		return "writeOK";
	}
	
	
	@RequestMapping(value = "/update", method = RequestMethod.GET, params = {"articleNo"})
	public String update( Model model, @RequestParam(value="articleNo", required = true) int articleNo) {

		model.addAttribute( "article", service.getArticle( articleNo ) );
		return "update";
	}
	@RequestMapping(value = "/updateAction", method = RequestMethod.POST, params = {"articleNo"}) // ?
	public String updateAction( Model model, Article article ) {
		int result = service.Update( article.getArticleNo(), article.getArticleTitle(), article.getArticleContent() );
		if ( result == -1 ){
			model.addAttribute("msg", "오류가 있습니다."); 
			model.addAttribute("url", "update"); 
			return "redirect";
		}
		return "updateOK";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET, params = {"articleNo"})
	public String delete( Model model, @RequestParam(value="articleNo", required = true) int articleNo) {

		service.Delete( articleNo );
		return "deleteOK";
	}

}
