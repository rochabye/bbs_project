package com.bbs.bbs_pjt.board.dao;

import java.sql.Connection;

import com.bbs.bbs_pjt.board.Board;
import com.bbs.bbs_pjt.commons.paging.Criteria;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

@Repository
public class BoardDao implements IBoardDao {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	public BoardDao() {
		try {
			String dbURL="jdbc:mysql://localhost:3306/BBS?serverTimezone=UTC";
			String dbID="root";
			String dbPassword="root";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}

	@Override
	public int boardInsert( String userID, String boardTitle, String boardContent ) {
		String SQL = "INSERT INTO BBS VALUES ( ?, ?, ?, ?, ?, ? )";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, boardGetNext() );
			pstmt.setString(2, boardTitle );
			pstmt.setString(3, userID );
			pstmt.setString(4, boardGetDate() );
			pstmt.setString(5, boardContent );
			pstmt.setInt(6, 1 );
			return pstmt.executeUpdate();
			
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return -1;
	}



	@Override
	public int boardGetNext() {
		String SQL = "SELECT bbsID FROM BBS ORDER BY bbsID DESC";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if ( rs.next() ) {
				return rs.getInt(1) + 1;
			}
			return 1; // if this first
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return -1; // error 
	}

	@Override
	public ArrayList<Board> boardGetList(int pageNumber) {
		String SQL = "SELECT * FROM BBS WHERE bbsID < ? AND bbsAvailable = 1 ORDER BY bbsID DESC LIMIT 10";
		ArrayList<Board> list = new ArrayList<Board>();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt( 1,  boardGetNext() - ( pageNumber - 1 ) * 10 );
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				Board board = new Board();
				board.setBoardID(rs.getInt(1));
				board.setBoardTitle(rs.getString(2));
				board.setUserID(rs.getString(3));
				board.setBoardDate(rs.getString(4));
				board.setBoardContent(rs.getString(5));
				board.setAvailable(rs.getInt(6));
				list.add(board);
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return list; 
	}

	@Override
	public boolean boardGetNextPage(int pageNumber) {
		String SQL = "SELECT * FROM BBS WHERE bbsID < ? AND bbsAvailable = 1";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt( 1,  boardGetNext() - ( pageNumber - 1 ) * 10 );
			rs = pstmt.executeQuery();
			if ( rs.next() ) {
				return true;
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return false; 
	}
	@Override
	public Board getBoard(int boardID ) {
		String SQL = "SELECT * FROM BBS WHERE bbsID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt( 1, boardID );
			rs = pstmt.executeQuery();
			if ( rs.next() ) {
				Board board = new Board();
				board.setBoardID(rs.getInt(1));
				board.setBoardTitle(rs.getString(2));
				board.setUserID(rs.getString(3));
				board.setBoardDate(rs.getString(4));
				board.setBoardContent(rs.getString(5));
				board.setAvailable(rs.getInt(6));
				return board;
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return null; 
	}

	@Override
	public int boardUpdate( int boardID, String boardTitle, String boardContent ) {
		String SQL = "UPDATE BBS SET bbsTitle = ?, bbsContent = ? WHERE bbsID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, boardTitle );
			pstmt.setString(2, boardContent );
			pstmt.setInt(3, boardID );
			return pstmt.executeUpdate();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int boardDelete( int boardID ) {
		String SQL = "UPDATE BBS SET bbsAvailable = 0 WHERE bbsID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, boardID );
			return pstmt.executeUpdate();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return -1;
	}
	
	@Override
	public String boardGetDate() {
		String SQL = "SELECT NOW()";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if ( rs.next() ) {
				return rs.getString(1);
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return ""; // error 
	}

	@Override
	public ArrayList<Board> selectList( Criteria cri ) {

		String SQL = "SELECT * FROM BBS WHERE 1=1 AND bbsID > 0 AND bbsAvailable = 1 ORDER BY bbsID DESC LIMIT ?, ?";
		ArrayList<Board> list = new ArrayList<Board>();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt( 1, cri.getPageStart() );
			pstmt.setInt( 2, cri.getPerPageNum() );
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				
				Board board = new Board();
				board.setBoardID(rs.getInt(1));
				board.setBoardTitle(rs.getString(2));
				board.setUserID(rs.getString(3));
				board.setBoardDate(rs.getString(4));
				board.setBoardContent(rs.getString(5));
				board.setAvailable(rs.getInt(6));
				System.out.println( "board: " + board.getBoardID() );
				list.add(board);
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return list; 
	}
	
	@Override
	public int countPaging( Criteria cri ) {
		int count = -1;
		String SQL = "SELECT COUNT(*) FROM BBS WHERE bbsID > 0;";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				count = rs.getInt(1);
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		}		
		return count;
	}
}
