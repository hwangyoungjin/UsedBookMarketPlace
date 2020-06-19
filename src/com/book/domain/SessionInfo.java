package com.book.domain;

import java.util.HashMap;

public class SessionInfo {
	
	private static SessionInfo instance = new SessionInfo();
	private SessionInfo() {}
	
	public static SessionInfo getInstance() {
		return instance;
	}
	
	// 로그인한 사용자정보 저장
	private HashMap<String, UserInfo> sessionInfo = new HashMap<>();
	
	public boolean isSessionUser(String uid) {
		return sessionInfo.get(uid) != null ? true : false;
	}
	
	public void setSession(String uid, UserInfo info) {
		this.sessionInfo.put(uid, info);
	}
	
	public UserInfo getSession(String uid) {
		return this.sessionInfo.get(uid);
	}

}
