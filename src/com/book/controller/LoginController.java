package com.book.controller;

import com.book.domain.UserInfo;

public interface LoginController {
	public UserInfo doLogin(String uid, String pw);
}
