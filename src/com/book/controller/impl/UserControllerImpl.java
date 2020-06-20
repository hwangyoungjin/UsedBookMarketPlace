package com.book.controller.impl;

import java.util.List;

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

	@Override
	public List<UserInfo> getAllUsers() {
		UserInfoRepository repository = RepositoryFactory.getInstance().getUserInfoRepository();
		return repository.getAllUsers();
	}

	@Override
	public int updateUser (UserInfo userInfo) {
		UserInfoRepository repository = RepositoryFactory.getInstance().getUserInfoRepository();
		return repository.updateUser(userInfo);
	}

	@Override
	public int deleteUser(UserInfo userInfo) {
		UserInfoRepository repository = RepositoryFactory.getInstance().getUserInfoRepository();
		return repository.deleteUser(userInfo);
	}

	
	
	
}
