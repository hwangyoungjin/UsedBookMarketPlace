package com.book.controller;

import java.util.List;

import com.book.domain.BookInfo;

public interface BookController {
	
	// 책 검색
	public BookInfo getBook (String param, String serachCondition);
	// 책 목록 검색
	public List<BookInfo> getBookList(String param, String serachCondition);
	//책 등록
	public int createBook(BookInfo bookInfo);
	//책 수정
	public int updateBook(BookInfo bookInfo);
}
