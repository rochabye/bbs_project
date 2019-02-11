package com.bbs.bbs_pjt.bbs.service;

import java.util.ArrayList;

import com.bbs.bbs_pjt.bbs.Bbs;

public interface IBbsService {

	ArrayList< Bbs > getList( int pageNumber );
	Bbs getBbs( int bbsID );
	int Write( String bbsTitle, String userID, String bbsContent );

	int Update( int bbsID, String bbsTitle, String bbsContent );
	void Delete( int bbsID );
}
