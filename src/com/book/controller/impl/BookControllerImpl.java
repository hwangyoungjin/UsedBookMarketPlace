package com.book.controller.impl;

import java.util.List;

import com.book.controller.BookController;
import com.book.domain.BookInfo;
import com.book.factory.RepositoryFactory;
import com.book.repository.BookInfoRepository;

public class BookControllerImpl implements BookController {

	@Override
	public BookInfo getBook(String param, String serachCondition) {
		BookInfoRepository repository = RepositoryFactory.getInstance().getBookInfoRepository();
		return repository.getBook(param, serachCondition);
	}

	@Override
	public List<BookInfo> getBookList(String param, String serachCondition) {
		BookInfoRepository repository = RepositoryFactory.getInstance().getBookInfoRepository();
		return repository.getBookList(param, serachCondition);
	}

	@Override
	public int createBook(BookInfo bookInfo) {
		BookInfoRepository repository = RepositoryFactory.getInstance().getBookInfoRepository();
		return repository.createBook(bookInfo);
	}

	@Override
	public int updateBook(BookInfo bookInfo) {
		BookInfoRepository repository = RepositoryFactory.getInstance().getBookInfoRepository();
		return repository.updateBook(bookInfo);
	}

	@Override
	public int deleteBook(String id) {
		BookInfoRepository repository = RepositoryFactory.getInstance().getBookInfoRepository();
		return repository.deleteBook(id);
	}

	@Override
	public int allDeleteUserBook(String sellerID) {
		BookInfoRepository repository = RepositoryFactory.getInstance().getBookInfoRepository();
		return repository.allDeleteUserBook(sellerID);
	}
	
	@Override
	public List<BookInfo> getAllBooks() {
		BookInfoRepository repository = RepositoryFactory.getInstance().getBookInfoRepository();
		return repository.getAllBooks();
	}

}
