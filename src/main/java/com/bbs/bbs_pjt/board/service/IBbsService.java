package com.bbs.bbs_pjt.board.service;

import java.util.ArrayList;

import com.bbs.bbs_pjt.board.Bbs;
import com.bbs.bbs_pjt.commons.paging.Criteria;

public interface IBbsService {

	ArrayList< Bbs > getList( int pageNumber );
	Bbs getBbs( int bbsID );
	int Write( String userID, String bbsTitle, String bbsContent);

	int Update( int bbsID, String bbsTitle, String bbsContent );
	void Delete( int bbsID );
	
	public ArrayList< Bbs > listCriteria( Criteria criteria );
	public int listCountCriteria( Criteria criteria );
}
