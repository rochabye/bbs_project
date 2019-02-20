package com.bbs.bbs_pjt.article.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbs.bbs_pjt.article.Article;
import com.bbs.bbs_pjt.article.dao.ArticleDao;
import com.bbs.bbs_pjt.commons.paging.Criteria;

@Service
public class ArticleService implements IArticleService {
	
	@Autowired
	ArticleDao dao;

	@Override
	public int Write( String userID, String articleTitle, String articleContent) {
		int result = dao.articleInsert( userID, articleTitle, articleContent );	
		return result;
	}

	@Override
	public int Update( int articleNo, String articleTitle, String articleContent ) {
		return dao.articleUpdate( articleNo, articleTitle, articleContent );
	}

	@Override
	public void Delete( int articleNo ) {
		if ( articleNo < 0 ) {
			return;
		}
		dao.articleDelete( articleNo );
	}

	@Override
	public Article getArticle( int articleNo ) {
		if ( articleNo < 0 ) {
			return null;
		}
		return dao.getArticle( articleNo );
	}
	
	@Override
	public ArrayList< Article > listCriteria( Criteria criteria ) {
		return dao.selectList( criteria );
	}
	
	@Override
	public int listCountCriteria( Criteria criteria ) {
		
		return dao.countPaging( criteria );
	}

}
