package com.bbs.bbs_pjt.board.dao;

import java.util.ArrayList;

import com.bbs.bbs_pjt.board.Board;
import com.bbs.bbs_pjt.commons.paging.Criteria;

public interface IBoardDao {

	int boardInsert( String userID, String boardTitle, String boardContent );
	int boardUpdate( int boardID, String boardTitle, String boardContent );
	int boardDelete( int boardID);
	
	String boardGetDate();
	int boardGetNext();
	ArrayList<Board> boardGetList( int pageNumber );
	ArrayList<Board> selectList( Criteria cri );
	int selectOne( Criteria cri );
	boolean boardGetNextPage( int pageNumber );
	Board getBoard( int boardID );
	
	int countPaging();
	
}
