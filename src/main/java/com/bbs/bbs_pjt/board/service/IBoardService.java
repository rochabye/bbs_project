package com.bbs.bbs_pjt.board.service;

import java.util.ArrayList;

import com.bbs.bbs_pjt.board.Board;
import com.bbs.bbs_pjt.commons.paging.Criteria;

public interface IBoardService {

	ArrayList< Board > getList( int pageNumber );
	Board getBoard( int boardID );
	int Write( String userID, String boardTitle, String boardContent);

	int Update( int boardID, String boardTitle, String boardContent );
	void Delete( int boardID );
	
	public ArrayList< Board > listCriteria( Criteria criteria );
	public int listCountCriteria( Criteria criteria );
}
