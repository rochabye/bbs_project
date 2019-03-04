package com.bbs.bbs_pjt.reply.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bbs.bbs_pjt.article.Article;
import com.bbs.bbs_pjt.commons.paging.Criteria;
import com.bbs.bbs_pjt.reply.Reply;
import com.bbs.bbs_pjt.reply.service.ReplyService;

@Controller
public class ReplyController {

	@Autowired
	ReplyService service;
	
	@RequestMapping( value="/replyAction", method = RequestMethod.POST )
	public String register( Reply reply, HttpSession session,
			@RequestParam(value="articleNo", required = false, defaultValue="0") int articleNo ) {
		reply.setReplyWriter( (String)session.getAttribute( "userID" ) );
		reply.setArticleNo( articleNo );
		service.addReply( reply );
		return "";
	}
}
