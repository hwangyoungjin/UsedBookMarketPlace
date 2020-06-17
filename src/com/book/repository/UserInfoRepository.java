package com.book.repository;

import java.util.List;

import com.book.domain.UserInfo;

/**
 * 사용자 관리 Reposity interface
 * @author USER
 *
 */
public interface UserInfoRepository {

	// 사용자 등록
	public void createUser(UserInfo userInfo);
	
	// 사용자 검색
	public UserInfo getUser(String id);
	
	// 전체 사용자 검색
	public List<UserInfo> getAllUsers();
	
	// 사용자 삭제
	public int deleteUser(String id);
	
	// 사용자 수정
	public int updateUser(UserInfo userInfo);
	
}
