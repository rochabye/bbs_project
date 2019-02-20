package com.bbs.bbs_pjt.article.dao;

import java.util.ArrayList;

import com.bbs.bbs_pjt.article.Article;
import com.bbs.bbs_pjt.commons.paging.Criteria;

public interface IArticleDao {

	int articleInsert( String userID, String boardTitle, String boardContent );
	int articleUpdate( int boardID, String boardTitle, String boardContent );
	int articleDelete( int boardID);
	
	String getArticleDate();
	int getNextArticleNo();
	ArrayList<Article> selectList( Criteria cri );
	boolean hasNextPage( int pageNumber );
	Article getArticle( int articleNo );
	
	int countPaging( Criteria cri );
	
}
