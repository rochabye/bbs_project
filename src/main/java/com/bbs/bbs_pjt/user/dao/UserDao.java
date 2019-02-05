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
			System.out.println( " DB COnnected " );
		} catch ( Exception e ) {
			System.out.println( " DB is not COnnected  " + e.getMessage() + e.getCause()  );
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
	public int userSelect( String userID, String userPassword ) {
		String SQL = "SELECT userPassword FROM USER WHERE userID = ?";

		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID );
			rs = pstmt.executeQuery();
			if ( rs.next() ) {
				if ( rs.getString(1).equals( userPassword ) ) {
					return 1; // login success 
				}
				else
					return -1; // password not correct 
			}
			return -1; // no id 
		} catch( Exception e) {
			e.printStackTrace();
		}
		return -2;
	}
	public void userUpdate() {
		
	}
	public void userDelete() {
		
	}
}
