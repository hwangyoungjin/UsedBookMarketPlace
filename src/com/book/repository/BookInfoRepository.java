package com.book.repository;

import java.util.List;

import com.book.domain.BookInfo;

/**
 * book 관리 Repository interface
 * @author USER
 *
 */
public interface BookInfoRepository {
	
	//책 등록
	public int createBook(BookInfo bookInfo);
	
	//책 검색
	public BookInfo getBook(String param, String serachCondition);
	
	//책 목록 검색
	public List<BookInfo> getBookList(String param, String serachCondition);
	
	//전체 book 검색
	public List<BookInfo> getAllBooks();
	
	//책 삭제 
	public int deleteBook(String name);
	
	//특정 사용자 책 모두 삭제 
	public int allDeleteUserBook(String sellerID);
		
	
	//책 수정
	public int updateBook(BookInfo bookInfo);
	
}
