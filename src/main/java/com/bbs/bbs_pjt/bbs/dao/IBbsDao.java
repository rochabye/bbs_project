package com.bbs.bbs_pjt.bbs.dao;

import java.util.ArrayList;

import com.bbs.bbs_pjt.bbs.Bbs;

public interface IBbsDao {

	int bbsInsert( String userID, String bbsTitle, String bbsContent );
	int bbsUpdate( int bbsID, String bbsTitle, String bbsContent );
	int bbsDelete( int bbsID);
	
	String bbsGetDate();
	int bbsGetNext();
	ArrayList<Bbs> bbsGetList( int pageNumber );
	boolean bbsGetNextPage( int pageNumber );
	Bbs getBbs( int bbsID );
	
}
