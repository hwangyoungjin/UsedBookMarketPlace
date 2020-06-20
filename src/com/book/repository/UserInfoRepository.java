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
	public int createUser(UserInfo userInfo);
	
	// 사용자 검색
	public UserInfo getUser(String id);
	
	// 전체 사용자 검색
	public List<UserInfo> getAllUsers();
	
	// 사용자 삭제
	public int deleteUser(UserInfo userInfo);
	
	// 사용자 수정
	public int updateUser(UserInfo userInfo);
	
	// 사용자 id/pw로 조건 검색 후 사용자정보 return 
	public UserInfo getLoginUser(String id, String pw);

	
}
