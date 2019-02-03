package com.bbs.bbs_pjt.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.bbs.bbs_pjt.user.User;

@Repository
public class UserDao implements IUserDao{
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public UserDao() {
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
	
	public int userInsert( String userID, String userPassword, String userName, String userGender, String userEmail ){
		String SQL = "INSERT INTO USER VALUES( ?, ?, ?, ?, ? )";
		try {
			pstmt = conn.prepareStatement( SQL );
			pstmt.setString( 1,  userID);
			pstmt.setString( 2,  userPassword );
			pstmt.setString( 3,  userName );
			pstmt.setString( 4,  userGender );
			pstmt.setString( 5,  userEmail );
			return pstmt.executeUpdate();
		} catch( Exception e ) {
			e.printStackTrace();
		}
		return -1;
	}
	public User userSelect( String userID, String userPassword ) {
		User user = null;
		return user;
	}
	public void userUpdate() {
		
	}
	public void userDelete() {
		
	}
}
