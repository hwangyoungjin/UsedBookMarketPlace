package com.book.factory;

import com.book.repository.BookInfoRepository;
import com.book.repository.UserInfoRepository;
import com.book.repository.impl.BookInfoRepositoryImpl;
import com.book.repository.impl.UserInfoRepositoryImpl;

public class RepositoryFactory {

	private static RepositoryFactory instance = new RepositoryFactory();
	private RepositoryFactory() {}
	
	public static RepositoryFactory getInstance() {
		return instance;
	}
	
	public static UserInfoRepository getUserInfoRepository() {
		return new UserInfoRepositoryImpl();
	}
	public static BookInfoRepository getBookInfoRepository() {
		return new BookInfoRepositoryImpl();
	}
	
}
