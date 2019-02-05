package com.bbs.bbs_pjt.user.service;

import com.bbs.bbs_pjt.user.User;

public interface IUserService {
	void userRegister( String userID, String userPassword, String userName, String userGender, String userEmail );
	int userSearch( String userID, String userPassword );
	void userModify();
	void userRemove();
}
