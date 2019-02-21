package com.bbs.bbs_pjt.reply.dao;

import java.util.List;

import com.bbs.bbs_pjt.reply.dao.ReplyDao;

public interface IReplyDao {
	
	List<ReplyDao> list(Integer articleNo) throws Exception;

	void create(ReplyDao replyDao) throws Exception;
    void update(ReplyDao replyDao) throws Exception;
    void delete(Integer replyNo) throws Exception;
}
