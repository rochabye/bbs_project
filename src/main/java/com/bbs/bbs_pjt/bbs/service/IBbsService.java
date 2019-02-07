package com.bbs.bbs_pjt.bbs.service;

public interface IBbsService {
	
	String getDate();
	int getNext();
	int bbsWrite( String bbsTitle, String userID, String bbsContent );

	void bbsUpdate();
	void bbsDelete();
}
