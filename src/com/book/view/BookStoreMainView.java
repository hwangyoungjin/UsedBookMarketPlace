package com.book.view;

import java.util.List;
import java.util.Scanner;

import com.book.constants.CommandConstants;
import com.book.constants.SearchCondition;
import com.book.controller.BookController;
import com.book.controller.LoginController;
import com.book.controller.UserController;
import com.book.domain.BookInfo;
import com.book.domain.UserInfo;
import com.book.factory.ControllerFactory;

public class BookStoreMainView {

	public static void main(String[] args) { //로그인 화면

		UserController controller = ControllerFactory.getInstance().getUserController();
		LoginController loginController = ControllerFactory.getInstance().getLoginController();
		
		BookStoreMainView mainView = new BookStoreMainView();
		
		/*
		UserInfo info = controller.getUser("test");
		BookInfo binfo = controllerBook.getBook("book1", SearchCondition.BOOK);
		System.out.println(binfo);
		System.out.println(info);
		*/
		
//
//		Scanner sc = null;
//		String id = null;
//		String pw = null; 
//		System.out.println("==============");
//		System.out.println("로그인을 해주시기 바랍니다");
//		
//		System.out.print("ID 입력 : "); sc = new Scanner(System.in);
//		String input = sc.nextLine(); // 개행 문자(\n)나오기 전까지 입력 받음
//		if(input!=null && !input.equals("")) id = input;
//		System.out.print("Password 입력 : ");
//		input = sc.nextLine(); // 개행 문자(\n)나오기 전까지 입력 받음
//		if(input!=null && !input.equals("")) pw = input;
//		System.out.println("id->"+id + " / pw->"+pw);
		
		String id = "test";
		String pw = "1234";
		//String id = "admin"; // 관리자
		//String pw = "nayana"; // 관리자 패스워드

		UserInfo useInfo = loginController.doLogin(id, pw);
		if(useInfo == null ) {
			System.out.println("유효하지 않은 사용자입니다. 다시 입력해주시기 바랍니다.");
		} else {
			//System.out.println("useInfo 11111 ->"+SessionInfo.getInstance().getSession(id));
			// 관리자/사용자 여부 체크하여 화면 메뉴를 분기한다
			if(useInfo.getManagerYn().equals("Y")) { //관리자라면
				mainView.runManagerView(id);
			}else { // 일반 User 라면
				mainView.runUserView(id);
			}
		}
	
	}
	
	
	// 관리자 메인 View
	public void runManagerView(String uid) {
		
		Scanner sc = null;
		String input = null;

		while(true) {
			System.out.println("==============");
			System.out.println("명령어를 입력하세요:");
			System.out.println("0:사용자 관리");
			System.out.println("1:도서 조회");
			System.out.println("3:도서 수정");
			System.out.println("4:도서 삭제");
			System.out.print(">> "); sc = new Scanner(System.in);
			input = sc.nextLine();
			if( input.equals(CommandConstants.APP_EXIT) ) {
				BookStoreMainView.appExit();
			} else if( input.equals(CommandConstants.BOOK_CREATE) ) {
				bookCreate(uid);
			} else if(input!=null && input.equals(CommandConstants.BOOK_SEARCH)) {
				bookSearch(uid);
			} // if 
		}
	}
	

	// 사용자 메인 View
	public void runUserView(String uid) {
		
		BookController controllerBook = ControllerFactory.getInstance().getBookController();

		Scanner sc = null;
		String input = null;

		while(true) {
			System.out.println("==============");
			System.out.println("명령어를 입력하세요:");
			System.out.println("1:도서 조회");
			System.out.println("2:도서 등록");
			System.out.println("3:도서 수정");
			System.out.println("4:도서 삭제");
			System.out.println("99:프로그램 종료");
			System.out.print(">> "); sc = new Scanner(System.in);
			
			input = sc.nextLine();
			if( input.equals(CommandConstants.APP_EXIT) ) {
				BookStoreMainView.appExit();
			} else if( input.equals(CommandConstants.BOOK_CREATE) ) {
				bookCreate(uid);
			} else if( input.equals(CommandConstants.BOOK_UPDATE) ) {
				bookUpate(uid);
			} else if(input!=null && input.equals(CommandConstants.BOOK_SEARCH)) {
				bookSearch(uid);
			} // if 
			
			
		} // while
	}
	
	public static void appExit() {
		System.out.println("프로그램이 종료됩니다.");				
		System.exit(0);	
	}
	
	public void printBookList(List<BookInfo> list) {

		if(list == null) {
			System.out.println("책 정보가 없습니다.");
			return;
		}
        System.out.println("ID\t제목\t\tISBN\t\t저자\t\t출판사\t\t년도\t판매자ID\t\t가격\t책 상태");
        System.out.println("--------------------------------------------------------------------------------------");
       
        for(BookInfo info:list) {
        	System.out.println(info.getPrtInfo());
        }
        
	}
	
	/**
	 * 도서 조회
	 * @param uid
	 */
	public void bookSearch(String uid) {
		
		String inputCondition = null;
		String inputVaue = null;
		
		BookController controllerBook = ControllerFactory.getInstance().getBookController();
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("검색 조건과 조건값 입력해주세요");
			System.out.println("[책 제목의 입력 예 ) 1 book1 ");
			System.out.println("[상위메뉴로가기 예 ) 00 0 ");
			System.out.println("[프로그램 종료 예 ) 99 9 ");
			System.out.println("1:책 제목");
			System.out.println("2:책 ISBN");
			System.out.println("3:저자");
			System.out.println("4:출판사");
			System.out.println("5:출판년도");
			System.out.println("6:판매자ID");
			System.out.println("00:상위메뉴로 가기");
			System.out.println("99:프로그램 종료");				
			System.out.print(">> "); 
			inputCondition = sc.next();
			inputVaue = sc.next();
			
			if ( inputCondition.equals(CommandConstants.TOP_MENU) ) {
				break;
			} else if ( inputCondition.equals(CommandConstants.APP_EXIT) ) {
				BookStoreMainView.appExit();
			} else if(inputCondition!=null && inputVaue!=null && 
			(inputCondition.equals(SearchCondition.BOOKNAME) || inputCondition.equals(SearchCondition.AUTHOR))
			 ||inputCondition.equals(SearchCondition.ISBN) || inputCondition.equals(SearchCondition.YEAR)
			 ||inputCondition.equals(SearchCondition.PUBLISHER)||inputCondition.equals(SearchCondition.SELLER)) {
				List<BookInfo> bookInfoList = controllerBook.getBookList(inputVaue, inputCondition);
				printBookList(bookInfoList);
			}// if
		}// while
	}

	
	/**
	 * 도서 등록
	 * @param uid
	 */
	public void bookCreate(String uid) {
		Scanner sc = new Scanner(System.in);
		String input = null;
		BookInfo book = new BookInfo();
		System.out.println("등록할 책 정보를 입력해주세요");
		System.out.println("* 책 상태는 A/B/C 등급으로 입력해주세요.");

		while(true) {		
			book.setSellerID(uid);
			System.out.print("제목:");
			input = sc.next();
			if(input!=null && !input.equals("")) {
				book.setName(input);		
			} else {
				System.out.println("책 제목을 필수 입력입니다.");
				continue;
			}
			System.out.print("ISBN:"); book.setISBN(sc.next());
			System.out.print("저자:"); book.setAuthor(sc.next());
			System.out.print("출판사:"); book.setPublisher(sc.next());
			System.out.print("년도:"); book.setYear(sc.next());
			
			System.out.print("가격:"); book.setPrice(sc.next());
			System.out.print("책 상태:"); book.setState(sc.next());
			
			BookController controllerBook = ControllerFactory.getInstance().getBookController();
			int result = controllerBook.createBook(book);
			if(result > 0) {
				System.out.println("책이 정상적으로 동록되었습니다.");
			} else {
				System.out.println("책이 정상적으로 등록되지 않았습니다.\n관리자에게 문의하세요.");
			}
			
			break;
		}
		
	}
	
	/**
	 * 도서 등록
	 * @param uid
	 */
	public void bookUpate(String uid) {
		Scanner sc = new Scanner(System.in);
		String input = null;
		BookInfo book = new BookInfo();
		System.out.println(uid + "님께서 등록한 도서 목록 입니다.");
		// 도서 보여주기
		BookController controllerBook = ControllerFactory.getInstance().getBookController();
		List<BookInfo> list = controllerBook.getBookList(uid, SearchCondition.SELLER);
		printBookList(list);
		
		while(true) {		
			book.setSellerID(uid);
			System.out.println("수정할 도서 ID를 입력해주세요.");
			input = sc.next();
			if(input!=null && !input.equals("")) {
				book.setId(input);		
			} else {
				System.out.println("수정할 도서 ID는 필수 입력입니다.");
				continue;
			}
			
			System.out.println("수정할 책 제목을 입력해주세요.");
			input = sc.next();
			if(input!=null && !input.equals("")) {
				book.setName(input);		
			} else {
				System.out.println("책 제목은 필수 입력입니다.");
				continue;
			}
			System.out.print("ISBN:"); book.setISBN(sc.next());
			System.out.print("저자:"); book.setAuthor(sc.next());
			System.out.print("출판사:"); book.setPublisher(sc.next());
			System.out.print("년도:"); book.setYear(sc.next());
			System.out.print("가격:"); book.setPrice(sc.next());
			System.out.print("책 상태:"); book.setState(sc.next());
			
			int result = controllerBook.updateBook(book);
			if(result > 0) {
				System.out.println("책이 정상적으로  수정되었습니다.");
			} else {
				System.out.println("책이 정상적으로 수정되지 않았습니다.\n관리자에게 문의하세요.");
			}
			break;
		}
		
	}
}

