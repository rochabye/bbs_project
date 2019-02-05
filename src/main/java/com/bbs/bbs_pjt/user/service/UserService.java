package com.bbs.bbs_pjt.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbs.bbs_pjt.user.User;
import com.bbs.bbs_pjt.user.dao.UserDao;

@Service
public class UserService implements IUserService {
	
	@Autowired 
	UserDao dao;
	
	@Override
	public void userRegister(String userID, String userPassword, String userName, String userGender, String userEmail) {
		dao.userInsert( userID, userPassword, userName, userGender, userEmail );
	}

	@Override
	public int userSearch(String userID, String userPassword) {
		int result = dao.userSelect( userID, userPassword ); 
		return result;
	}

	@Override
	public void userModify() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void userRemove() {
		dao.userDelete();
	}

}
