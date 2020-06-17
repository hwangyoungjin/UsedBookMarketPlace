package com.book.controller.impl;

import com.book.controller.UserController;
import com.book.domain.UserInfo;
import com.book.factory.RepositoryFactory;
import com.book.repository.UserInfoRepository;

public class UserControllerImpl implements UserController {

	@Override
	public UserInfo getUser(String id) {
		UserInfoRepository repository = RepositoryFactory.getInstance().getUserInfoRepository();
		return repository.getUser(id);
	}

}
