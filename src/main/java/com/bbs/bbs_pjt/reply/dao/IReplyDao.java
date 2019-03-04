package com.bbs.bbs_pjt.reply.dao;

import java.util.ArrayList;

import com.bbs.bbs_pjt.reply.Reply;

public interface IReplyDao {
	
	ArrayList< Reply > list(Integer articleNo) throws Exception;

	int insert( Reply reply ) throws Exception;
	int update( Reply reply ) throws Exception;
    int delete( int replyNo ) throws Exception;
    int getNextReplyNo();
    String getDate() throws Exception;
}
