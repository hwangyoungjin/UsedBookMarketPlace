package com.book.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.book.constants.SearchCondition;
import com.book.controller.BookController;
import com.book.domain.BookInfo;
import com.book.factory.ControllerFactory;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookControllerTest {

	private BookController bookController = ControllerFactory.getInstance().getBookController();

	@Test
	@Order(1)
	void testCreateBook() { 
		//책 이름이 testCaseBook, 책상태가 "good" sellerID가 test인 책을 만들고 확인 -> 잘 만들어졌으면 1이나와야한다
		BookInfo bookInfo = new BookInfo();
		bookInfo.setName("testCaseBook");
		bookInfo.setState("Good");
		bookInfo.setSellerID("test");
		assertEquals(bookController.createBook(bookInfo), 1);
	}
	
	@Test
	@Order(2)
	void testGetBook() { //책의 이름이 testCaseBook인 책의 주인이 "test"가 맞는지 검사
		BookInfo info = bookController.getBook("testCaseBook", SearchCondition.BOOKNAME);
		assertEquals("test", info.getSellerID());
	}

	@Test
	@Order(3)
	void testUpdateBook() { // 책 이름이 "testCaseBook"인 책의 이름을 testCaseBook1 변경하여 변경 되었는지 확인
		BookInfo info = bookController.getBook("testCaseBook", SearchCondition.BOOKNAME);
		info.setName("testCaseBook1");
		info.setId("65"); // 업데이트시 책 id 필요
		assertEquals(bookController.updateBook(info),1);
		BookInfo changeInfo = bookController.getBook("testCaseBook1", SearchCondition.BOOKNAME);
		assertEquals("testCaseBook1", changeInfo.getName());
	}
	
	@Test
	@Order(4)
	void testDeleteBook() { // 방금만든 이름이 testCaseBook1 인 책(id:43)을 삭제하고 해당책이 없는지 확인
		bookController.deleteBook("65");
		BookInfo deleteInfo = bookController.getBook("testCaseBook1", SearchCondition.BOOKNAME);
		assertTrue(deleteInfo == null);
	}

	@Test
	@Order(5)
	void testAllDeleteUserBook() { 
		// 사용자 id가 test인 책들 모두 삭제 후 사용자 id가 test인 책이 없는지 확인 
		bookController.allDeleteUserBook("test");
		BookInfo info = bookController.getBook("test",SearchCondition.SELLER);
		assertTrue(info == null);
	}
	
	@Test
	@Order(6)
	void testGetBookList() {  // 책이름에 소프트가 들어간 책 리스트를 불러와 존재하는지 테스트
		List<BookInfo> books = bookController.getBookList("소프트", SearchCondition.BOOKNAME);
		assertTrue(!books.isEmpty());
	}
	
	@Test
	@Order(7)
	void testGetAllBooks() {
		//현재 존재하는 책들의 list를 받아와서 존재하는지 확인
		List<BookInfo> books = bookController.getAllBooks();
		assertTrue(!books.isEmpty());
	}
}
