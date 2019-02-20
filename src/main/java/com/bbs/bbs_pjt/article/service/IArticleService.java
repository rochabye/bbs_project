package com.bbs.bbs_pjt.article.service;

import java.util.ArrayList;

import com.bbs.bbs_pjt.article.Article;
import com.bbs.bbs_pjt.commons.paging.Criteria;

public interface IArticleService {

	Article getArticle( int articleNo );
	int Write( String userID, String articleTitle, String articleContent);

	int Update( int articleNo, String articleTitle, String articleContent );
	void Delete( int articleNo );
	
	public ArrayList< Article > listCriteria( Criteria criteria );
	public int listCountCriteria( Criteria criteria );
}
