package com.book.controller.impl;

import com.book.controller.LoginController;
import com.book.domain.UserInfo;
import com.book.factory.RepositoryFactory;
import com.book.repository.UserInfoRepository;

public class LoginControllerImpl implements LoginController {

	@Override
	public UserInfo doLogin(String uid, String pw) {
		UserInfoRepository repository = RepositoryFactory.getInstance().getUserInfoRepository();
		UserInfo userInfo = repository.getLoginUser(uid,pw);
		
		if(userInfo == null) {
			System.out.println("존재하지 않는 유저입니다. ID/PW를 다시 확인해주시거나 가입해주시길 바랍니다.");
			return null;
		}
		else if(userInfo.getState().equals("deactivated")) {
			System.out.println("deactivated 상태인 유저입니다. 관리자에게 문의해주시길 바랍니다.");
			return null;
		}	
		return userInfo;
	}

}
