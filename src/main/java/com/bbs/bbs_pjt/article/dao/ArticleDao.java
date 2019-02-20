package com.bbs.bbs_pjt.article.dao;

import java.sql.Connection;

import com.bbs.bbs_pjt.article.Article;
import com.bbs.bbs_pjt.commons.paging.Criteria;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

@Repository
public class ArticleDao implements IArticleDao {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	public ArticleDao() {
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
	public int articleInsert( String userID, String articleTitle, String articleContent ) {
		String SQL = "INSERT INTO BBS VALUES ( ?, ?, ?, ?, ?, ? )";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNextArticleNo() );
			pstmt.setString(2, articleTitle );
			pstmt.setString(3, userID );
			pstmt.setString(4, getArticleDate() );
			pstmt.setString(5, articleContent );
			pstmt.setInt(6, 1 );
			return pstmt.executeUpdate();
			
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int getNextArticleNo() {
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
	public boolean hasNextPage( int pageNumber ) {
		String SQL = "SELECT * FROM BBS WHERE bbsID < ? AND bbsAvailable = 1";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt( 1,  getNextArticleNo() - ( pageNumber - 1 ) * 10 );
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
	public Article getArticle(int acticleNo ) {
		String SQL = "SELECT * FROM BBS WHERE bbsID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt( 1, acticleNo );
			rs = pstmt.executeQuery();
			if ( rs.next() ) {
				Article acticle = new Article();
				acticle.setArticleNo(rs.getInt(1));
				acticle.setArticleTitle(rs.getString(2));
				acticle.setUserID(rs.getString(3));
				acticle.setArticleDate(rs.getString(4));
				acticle.setArticleContent(rs.getString(5));
				acticle.setAvailable(rs.getInt(6));
				return acticle;
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return null; 
	}

	@Override
	public int articleUpdate( int acticleNo, String acticleTitle, String acticleContent ) {
		String SQL = "UPDATE BBS SET bbsTitle = ?, bbsContent = ? WHERE bbsID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, acticleTitle );
			pstmt.setString(2, acticleContent );
			pstmt.setInt(3, acticleNo );
			return pstmt.executeUpdate();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int articleDelete( int articleNo ) {
		String SQL = "UPDATE BBS SET bbsAvailable = 0 WHERE bbsID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, articleNo );
			return pstmt.executeUpdate();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return -1;
	}
	
	@Override
	public String getArticleDate() {
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
	public ArrayList< Article > selectList( Criteria cri ) {

		String SQL = "SELECT * FROM BBS WHERE 1=1 AND bbsID > 0 AND bbsAvailable = 1 ORDER BY bbsID DESC LIMIT ?, ?";
		ArrayList< Article > list = new ArrayList< Article >();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt( 1, cri.getPageStart() );
			pstmt.setInt( 2, cri.getPerPageNum() );
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				
				Article article = new Article();
				article.setArticleNo(rs.getInt(1));
				article.setArticleTitle(rs.getString(2));
				article.setUserID(rs.getString(3));
				article.setArticleDate(rs.getString(4));
				article.setArticleContent(rs.getString(5));
				article.setAvailable(rs.getInt(6));
				list.add(article);
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
