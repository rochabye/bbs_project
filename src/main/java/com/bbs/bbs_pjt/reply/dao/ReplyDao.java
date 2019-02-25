package com.bbs.bbs_pjt.reply.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.bbs.bbs_pjt.reply.Reply;

@Repository
public class ReplyDao implements IReplyDao{
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public ReplyDao() {
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
	public ArrayList<Reply> list(Integer articleNo) {
		ArrayList< Reply >replies = new ArrayList< Reply >();
		
		return replies;
	}

	public int insert( Reply reply ) {
		String SQL = "INSERT INTO REPLY VALUES ( ?, ?, ?, ?, ?, ? )";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, reply.getArticleNo() );
			pstmt.setInt(2, reply.getReplyNo() );
			pstmt.setString(3, reply.getReplyText() );
			pstmt.setString(4, reply.getReplyWriter() );
			pstmt.setString(5, getDate() );
			pstmt.setString(6, reply.getUpdateDate().toString() );
			return pstmt.executeUpdate();
			
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return -1;
	}
	
    public int update( Reply reply ) {
    	String SQL = "UPDATE REPLY SET replyText = ? WHERE replyNo = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, reply.getReplyText() );
			pstmt.setString(2, getDate() );
			pstmt.setInt(3, reply.getReplyNo() );
			return pstmt.executeUpdate();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return -1;
    	
    }
    
    public int delete( int replyNo ) { 
    	String SQL = "DELETE FROM REPLY WHERE replyNo = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, replyNo );
			return pstmt.executeUpdate();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return -1;
    }
    
    @Override
	public String getDate() {
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
