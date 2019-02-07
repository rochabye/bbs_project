package com.bbs.bbs_pjt.bbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbs.bbs_pjt.bbs.dao.BbsDao;

@Service
public class BbsService implements IBbsService {
	
	@Autowired
	BbsDao dao;

	@Override
	public String getDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNext() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int bbsWrite(String bbsTitle, String userID, String bbsContent) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void bbsUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bbsDelete() {
		// TODO Auto-generated method stub
		
	}
}
