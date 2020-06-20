package com.book.controller;

import java.util.List;

import com.book.domain.UserInfo;

public interface UserController {
	// 사용자 검색
	public UserInfo getUser(String id);
	
	// 모든 사용자 출력
	public List<UserInfo> getAllUsers();

	// 사용자 state 변경
	public int updateUser(UserInfo userInfo);
	
	// 사용자 삭제
	public int deleteUser(UserInfo userInfo);
}
