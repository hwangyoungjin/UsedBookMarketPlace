package com.book.controller;

import java.util.List;

import com.book.domain.BookInfo;
import com.book.domain.UserInfo;
import com.book.factory.RepositoryFactory;
import com.book.repository.UserInfoRepository;

public interface BookController {
	
	// 책 검색
	public BookInfo getBook (String param, String serachCondition);
	// 책 목록 검색
	public List<BookInfo> getBookList(String param, String serachCondition);
	// 책 전체 리스트 검색
	public List<BookInfo> getAllBooks();
	//책 등록
	public int createBook(BookInfo bookInfo);
	//책 수정
	public int updateBook(BookInfo bookInfo);
	//책 삭제
	public int deleteBook(String id);
	//특정 사용자가 등록한 책 모두 삭제
	public int allDeleteUserBook(String sellerID);
}
