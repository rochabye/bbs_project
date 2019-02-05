package com.bbs.bbs_pjt.user.dao;

import com.bbs.bbs_pjt.user.User;

public interface IUserDao {
	int userInsert( String userID, String userPassword, String userName, String userGender, String userEmail );
	int userSelect( String userID, String userPassword );
	void userUpdate();
	void userDelete();
	
}
