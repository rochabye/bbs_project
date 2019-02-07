package com.bbs.bbs_pjt.bbs.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.bbs.bbs_pjt.bbs.Bbs;

@Repository
public class BbsDao implements IBbsDao {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	public BbsDao() {
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
	public int bbsInsert( String bbsTitle, String userID, String bbsContent ) {
		String SQL = "INSERT INTO BBS VALUES ( ?, ?, ?, ?, ?, ? )";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsGetNext() );
			pstmt.setString(2, bbsTitle );
			pstmt.setString(3, userID );
			pstmt.setString(4, bbsGetDate() );
			pstmt.setString(5, bbsContent );
			pstmt.setInt(6, 1 );
			return pstmt.executeUpdate();
			
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return -1;
	}



	@Override
	public int bbsGetNext() {
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
	public ArrayList<Bbs> bbsGetList(int pageNumber) {
		String SQL = "SELECT * FROM BBS WHERE bbsID < ? AND bbsAvailable = 1 ORDER BY bbsID DESC LIMIT 10";
		ArrayList<Bbs> list = new ArrayList<Bbs>();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt( 1,  bbsGetNext() - ( pageNumber - 1 ) * 10 );
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				Bbs bbs = new Bbs();
				bbs.setBbsID(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setUserID(rs.getString(3));
				bbs.setBbsDate(rs.getString(4));
				bbs.setBbsContent(rs.getString(5));
				bbs.setBbsAvailable(rs.getInt(6));
				list.add(bbs);
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return list; 
	}

	@Override
	public boolean bbsGetNextPage(int pageNumber) {
		String SQL = "SELECT * FROM BBS WHERE bbsID < ? AND bbsAvailable = 1";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt( 1,  bbsGetNext() - ( pageNumber - 1 ) * 10 );
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
	public Bbs getBbs(int bbsID) {
		String SQL = "SELECT * FROM BBS WHERE bbsID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt( 1, bbsID );
			rs = pstmt.executeQuery();
			if ( rs.next() ) {
				Bbs bbs = new Bbs();
				bbs.setBbsID(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setUserID(rs.getString(3));
				bbs.setBbsDate(rs.getString(4));
				bbs.setBbsContent(rs.getString(5));
				bbs.setBbsAvailable(rs.getInt(6));
				return bbs;
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return null; 
	}

	@Override
	public int bbsUpdate( int bbsID, String bbsTitle, String bbsContent ) {
		String SQL = "UPDATE BBS SET bbsTitle = ?, bbsContent = ? WHERE bbsID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, bbsTitle );
			pstmt.setString(2, bbsContent );
			pstmt.setInt(3, bbsID );
			return pstmt.executeUpdate();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int bbsDelete( int bbsID ) {
		String SQL = "UPDATE BBS SET bbsAvailable = 0 WHERE bbsID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsID );
			return pstmt.executeUpdate();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return -1;
	}
	
	@Override
	public String bbsGetDate() {
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
	
}
