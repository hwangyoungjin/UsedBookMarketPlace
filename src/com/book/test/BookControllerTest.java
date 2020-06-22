package com.book.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.book.constants.SearchCondition;
import com.book.controller.BookController;
import com.book.domain.BookInfo;
import com.book.factory.ControllerFactory;

class BookControllerTest {

	private BookController bookController = ControllerFactory.getInstance().getBookController();

	@Test
	void testGetBook() {
		BookInfo info = bookController.getBook("9", SearchCondition.BOOKUID);
		assertEquals("KIM", info.getAuthor());
	}

	@Test
	void testGetBookList() {
		//fail("Not yet implemented");
	}
	
	@Test
	void testCreateBook() {
		BookInfo bookInfo = new BookInfo();
		bookInfo.setName("testCaseBook");
		bookInfo.setSellerID("test");
		assertEquals(bookController.createBook(bookInfo), 1);
	}

	@Test
	void testGetAllBooks() {
		List<BookInfo> books = bookController.getAllBooks();
		assertTrue(!books.isEmpty());
	}

}
