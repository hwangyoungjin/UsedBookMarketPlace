package com.book.controller.impl;

import com.book.controller.SignUpController;
import com.book.domain.UserInfo;
import com.book.factory.RepositoryFactory;
import com.book.repository.UserInfoRepository;

public class SignUpControllerImpl implements SignUpController {

	@Override
	public int signUp(UserInfo userInfo) {
		UserInfoRepository repository = RepositoryFactory.getInstance().getUserInfoRepository();
		return repository.createUser(userInfo);
	}

}
