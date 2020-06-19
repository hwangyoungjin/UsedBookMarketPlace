package com.book.controller;

import com.book.domain.UserInfo;

public interface UserController {
	// 사용자 검색
	public UserInfo getUser(String id);
}
