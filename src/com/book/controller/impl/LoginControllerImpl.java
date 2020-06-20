package com.book.controller.impl;

import com.book.controller.LoginController;
import com.book.domain.SessionInfo;
import com.book.domain.UserInfo;
import com.book.factory.RepositoryFactory;
import com.book.repository.UserInfoRepository;

public class LoginControllerImpl implements LoginController {

	@Override
	public UserInfo doLogin(String uid, String pw) {
		UserInfoRepository repository = RepositoryFactory.getInstance().getUserInfoRepository();
		UserInfo userInfo = repository.getLoginUser(uid,pw);
		if(userInfo != null) {
			SessionInfo.getInstance().setSession(uid, userInfo);
		}
		return userInfo;
	}

}
