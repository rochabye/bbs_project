package com.bbs.bbs_pjt.reply.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbs.bbs_pjt.reply.Reply;
import com.bbs.bbs_pjt.reply.dao.ReplyDao;

@Service
public class ReplyService {

	@Autowired
	ReplyDao dao;
	
	public void addReply( Reply reply ) {
		dao.insert( reply );
		
	}
}
