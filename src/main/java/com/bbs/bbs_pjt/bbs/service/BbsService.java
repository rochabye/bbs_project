package com.bbs.bbs_pjt.bbs.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbs.bbs_pjt.bbs.Bbs;
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
	public int Write(String bbsTitle, String userID, String bbsContent) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Bbs> getList( int pageNumber ) {
		if ( pageNumber < 0 ) {
			return null;
		}
		return dao.bbsGetList( pageNumber );
	}

	@Override
	public Bbs getBbs(int bbsID) {
		if ( bbsID < 0 ) {
			return null;
		}
		return dao.getBbs( bbsID );
	}
}
