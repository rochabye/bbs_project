package com.bbs.bbs_pjt.board.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbs.bbs_pjt.board.Board;
import com.bbs.bbs_pjt.board.dao.BoardDao;
import com.bbs.bbs_pjt.commons.paging.Criteria;

@Service
public class BoardService implements IBoardService {
	
	@Autowired
	BoardDao dao;

	@Override
	public int Write( String userID, String boardTitle, String boardContent) {
		int result = dao.boardInsert( userID, boardTitle, boardContent );	
		return result;
	}

	@Override
	public int Update( int boardID, String boardTitle, String boardContent ) {
		return dao.boardUpdate( boardID, boardTitle, boardContent );
	}

	@Override
	public void Delete( int boardID ) {
		if ( boardID < 0 ) {
			return;
		}
		dao.boardDelete( boardID );
	}

	@Override
	public ArrayList<Board> getList( int pageNumber ) {
		if ( pageNumber < 0 ) {
			return null;
		}
		return dao.boardGetList( pageNumber );
	}

	@Override
	public Board getBoard(int boardID) {
		if ( boardID < 0 ) {
			return null;
		}
		return dao.getBoard( boardID );
	}
	
	@Override
	public ArrayList< Board > listCriteria( Criteria criteria ) {
		return dao.selectList( criteria );
	}
	
	@Override
	public int listCountCriteria( Criteria criteria ) {
		
		return dao.countPaging( criteria );
	}
}
