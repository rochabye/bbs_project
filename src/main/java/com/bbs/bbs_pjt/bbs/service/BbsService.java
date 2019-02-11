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
	public int Write(String bbsTitle, String userID, String bbsContent) {
		int result = dao.bbsInsert( bbsTitle, userID, bbsContent );	
		return result;
	}

	@Override
	public int Update( int bbsID, String bbsTitle, String bbsContent ) {
		return dao.bbsUpdate( bbsID, bbsTitle, bbsContent );
	}

	@Override
	public void Delete( int bbsID ) {
		if ( bbsID < 0 ) {
			return;
		}
		dao.bbsDelete( bbsID );
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
