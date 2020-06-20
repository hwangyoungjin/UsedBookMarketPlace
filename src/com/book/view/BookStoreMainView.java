package com.book.view;

import java.util.List;
import java.util.Scanner;

import com.book.constants.CommandConstants;
import com.book.constants.SearchCondition;
import com.book.controller.BookController;
import com.book.controller.EMailController;
import com.book.controller.LoginController;
import com.book.controller.SignUpController;
import com.book.controller.UserController;
import com.book.domain.BookInfo;
import com.book.domain.UserInfo;
import com.book.factory.ControllerFactory;

public class BookStoreMainView {

	/**
	 * Login 화면
	 * @param args
	 */
	public static void main(String[] args) { //로그인 화면

		UserController userController = ControllerFactory.getInstance().getUserController();
		LoginController loginController = ControllerFactory.getInstance().getLoginController();
		
		BookStoreMainView mainView = new BookStoreMainView();
		
		/*
		UserInfo info = controller.getUser("test");
		BookInfo binfo = controllerBook.getBook("book1", SearchCondition.BOOK);
		System.out.println(binfo);
		System.out.println(info);
		*/
		

		Scanner sc = null;
		String input = null;
		String id = null;
		String pw = null; 
		
		while(true) {
			System.out.println("\n==========로그인==========");
			System.out.println("명령어를 입력하세요:");
			System.out.println("11.LogIn");
			System.out.println("22.SignUp");
			System.out.println("99:프로그램 종료");	
			System.out.print(">> "); sc = new Scanner(System.in);
			input = sc.nextLine();
			if( input.equals(CommandConstants.APP_EXIT) ) {
				BookStoreMainView.appExit();
			} else if( input.equals(CommandConstants.LOGIN) ) {
				System.out.print("ID 입력 : "); sc = new Scanner(System.in);
				input = sc.nextLine(); // 개행 문자(\n)나오기 전까지 입력 받음
				if(input!=null && !input.equals("")) id = input;
				System.out.print("Password 입력 : ");
				input = sc.nextLine(); // 개행 문자(\n)나오기 전까지 입력 받음
				if(input!=null && !input.equals("")) pw = input;
				UserInfo useInfo = loginController.doLogin(id, pw);
				if(useInfo == null ) {
					System.out.println("유효하지 않은 사용자입니다. 다시 입력해주시기 바랍니다.");
				} else {
					System.out.println(id + "님 환영합니다.");
					//System.out.println("useInfo 11111 ->"+SessionInfo.getInstance().getSession(id));
					// 관리자/사용자 여부 체크하여 화면 메뉴를 분기한다
					if(useInfo.getManagerYn().equals("Y")) { //관리자라면
						mainView.runManagerView(id);
					} else { // 일반 User 라면
						mainView.runUserView(id);
					}
				}
			} else if(input!=null && input.equals(CommandConstants.SIGNUP)) {
				System.out.println("\n==========회원가입==========");
				System.out.println("명령어를 입력하세요:");
				SignUpController signUpController = ControllerFactory.getInstance().getSignUpController();
				UserInfo userInfo = new UserInfo();
				System.out.print("ID 입력 : "); sc = new Scanner(System.in);
				input = sc.nextLine(); // 개행 문자(\n)나오기 전까지 입력 받음
				if(input!=null && !input.equals("")) userInfo.setUid(input);
				System.out.print("Password 입력 : ");
				input = sc.nextLine(); // 개행 문자(\n)나오기 전까지 입력 받음
				if(input!=null && !input.equals("")) userInfo.setPw(input);
				System.out.print("Name (ex. 황영진 ) 입력 : ");
				input = sc.nextLine(); // 개행 문자(\n)나오기 전까지 입력 받음
				if(input!=null && !input.equals("")) userInfo.setName(input);
				System.out.print("Phone Number (ex.010-1234-5678) 입력 : ");
				input = sc.nextLine(); // 개행 문자(\n)나오기 전까지 입력 받음
				if(input!=null && !input.equals("")) userInfo.setPhone(input);
				System.out.print("E-Mail (ex. 20186757@gmail.com 입력 : ");
				input = sc.nextLine(); // 개행 문자(\n)나오기 전까지 입력 받음
				if(input!=null && !input.equals("")) userInfo.setEmail(input);
				userInfo.setState("activate");
				userInfo.setManagerYn("N");
				int result = signUpController.signUp(userInfo);
				if(result>0) {
					System.out.println("등록이 정상적으로 완료되었습니다.");
				}
				else {
					System.out.println("등록이 정상적으로 되지 않았습니다.\n 관리자에게 문의해주시길 바랍니다.");
				}
			}
		}
	}
	
	
	/**
	 * 관리자 화면
	 * @param uid
	 */
	public void runManagerView(String uid) {
		
		Scanner sc = null;
		String input = null;

		while(true) {
			System.out.println("\n==========관리자==========");
			System.out.println("명령어를 입력하세요:");
			System.out.println("0:사용자 관리");
			System.out.println("8:사용자 도서관리");
			System.out.println("33:LogOut");
			System.out.println("99:프로그램 종료");	
			System.out.print(">> "); sc = new Scanner(System.in);
			input = sc.nextLine();
			if( input.equals(CommandConstants.LOGOUT) ) {
				return;
			} else if( input.equals(CommandConstants.APP_EXIT) ) {
				BookStoreMainView.appExit();
			} else if(input!=null && input.equals(CommandConstants.USER_MANAGING)) {
				userManaging();
			} else if( input.equals(CommandConstants.USER_BOOK_MANAGING) ) {
				userBookManaging();
			} // if 
		}
	}
	

	/**
	 * 관리자 -> 사용자 관리 화면 
	 */
	public void userManaging() {
		Scanner sc = null;
		String input = null;
		UserInfo userInfo = null;
		int count =0;
		while(true) {
			//현재 userlist 출력
			UserController allUserController = ControllerFactory.getInstance().getUserController();
			List<UserInfo> list = allUserController.getAllUsers();
			printUserList(list);
			
			System.out.println("\n==========사용자 관리==========");
			System.out.println("명령어를 입력하세요:");
			System.out.println("00:상위메뉴로 가기");
			System.out.println("99:프로그램 종료");	
			System.out.println("state변경 또는 삭제 할 사용자 ID 입력 : ");
			System.out.print(">> "); sc = new Scanner(System.in);
			input = sc.nextLine();
			if ( input.equals(CommandConstants.TOP_MENU) ) {
				break;
			} else if ( input.equals(CommandConstants.APP_EXIT) ) {
				BookStoreMainView.appExit();
			} else if(input != null && input !="") {
				//userlist에 사용자 있는지검사
				UserController userController = ControllerFactory.getInstance().getUserController();
				userInfo = userController.getUser(input);
			
				if(userInfo != null) { //유저존재
					System.out.println("\n"+userInfo.getUid()+" 사용자가 선택 되었습니다.");
					System.out.println(userInfo);
				}
				else { //유저 없음
					System.out.println("사용자 목록에 없습니다. 다시 입력하세요\n");
					continue;
				}
			}
			
			System.out.println("\n==========선택된 사용자 관리==========");
			System.out.println("명령어를 입력하세요:");
			System.out.println("6:사용자 상태변경");
			System.out.println("7:사용자 삭제");
			System.out.println("00:상위메뉴로 가기");
			System.out.println("99:프로그램 종료");	
			System.out.print(">> "); sc = new Scanner(System.in);
			input = sc.nextLine();
			
			if ( input.equals(CommandConstants.TOP_MENU) ) {
				break;
			} else if ( input.equals(CommandConstants.APP_EXIT) ) {
				BookStoreMainView.appExit();
			} else if( input.equals(CommandConstants.USER_UPDATE) ) {
				UserController userUpdateCtr = ControllerFactory.getInstance().getUserController();
				count = userUpdateCtr.updateUser(userInfo);
				if(count > 0) {
					System.out.println("사용자의 state가 정상적으로  수정되었습니다.");
				} else {
					System.out.println("사용자의 state가 정상적으로 수정되지 않았습니다.\n관리자에게 문의하세요.");
				}
			} else if(input!=null && input.equals(CommandConstants.USER_DELETE)) {
				UserController userDeleteCtr = ControllerFactory.getInstance().getUserController();
				count = userDeleteCtr.deleteUser(userInfo);
				if(count > 0) {
					System.out.println("사용자가  정상적으로  삭제되었습니다.");
					//해당 사용자의 책들도 모두 삭제
					BookController allDeleteBookCtr = ControllerFactory.getInstance().getBookController();
					int result = allDeleteBookCtr.allDeleteUserBook(userInfo.getUid());
					if(result > 0) {
						System.out.println("사용자의 등록된 책들이 모두 정상적으로 삭제 되었습니다.");
					} else {
						System.out.println("사용자의 등록된 책들이 모두 정상적으로 삭제 되지 않았습니다.\n관리자에게 문의하세요.");
					}
				} else {
					System.out.println("사용자가 activate 상태이므로 삭제되지 않았습니다.");
				}
			}
		}
	}
	
	/**
	 * 관리자 -> 사용자 도서관리 화면
	 */
	public void userBookManaging() {
		//현재 등록된 book list 출력
		System.out.println("\n==========사용자 도서관리==========");
		
		BookController controllerBook = ControllerFactory.getInstance().getBookController();
		List<BookInfo> list = controllerBook.getAllBooks();
		printBookList(list);
		
		Scanner sc = null;
		String input = null;
		if(list.isEmpty()) {
			return;
		}
		while(true) {
			bookSearch(); //도서검색
			
			System.out.println("\n====================");
			System.out.println("삭제 할 도서 ID를 입력해주세요 : ");
			System.out.println("00:상위메뉴로 가기");
			System.out.println("99:프로그램 종료");	
			System.out.print(">> "); sc = new Scanner(System.in);
			input = sc.next();
			if ( input.equals(CommandConstants.TOP_MENU) ) {
				break;
			} else if ( input.equals(CommandConstants.APP_EXIT) ) {
				BookStoreMainView.appExit();
			} else if(input!=null && !input.equals("")) {
				int result = controllerBook.deleteBook(input);
				if(result > 0) {
					System.out.println("책이 정상적으로  삭제되었습니다.");
				} else {
					System.out.println("책이 정상적으로 삭제되지 않았습니다.\n관리자에게 문의하세요.");
				}
				break;
			} else {
				System.out.println("삭제 할 도서 ID는 필수 입력입니다.");
				continue;
			}//if
			
		}
	}
	
	/**
	 * 사용자 화면
	 * @param uid
	 */
	public void runUserView(String uid) {
		
		BookController controllerBook = ControllerFactory.getInstance().getBookController();

		Scanner sc = null;
		String input = null;

		while(true) {
			System.out.println("\n==========사용자==========");
			System.out.println("명령어를 입력하세요:");
			System.out.println("1:도서 조회");
			System.out.println("2:도서 등록");
			System.out.println("3:도서 수정");
			System.out.println("4:도서 삭제");
			System.out.println("5:도서 구매");
			System.out.println("33:LogOut");
			System.out.println("99:프로그램 종료");
			System.out.print(">> "); sc = new Scanner(System.in);
			
			input = sc.nextLine();
			if( input.equals(CommandConstants.LOGOUT) ) {
				return;
			} else if( input.equals(CommandConstants.APP_EXIT) ) {
				BookStoreMainView.appExit();
			} else if( input.equals(CommandConstants.BOOK_CREATE) ) {
				bookCreate(uid);
			} else if( input.equals(CommandConstants.BOOK_UPDATE) ) {
				bookUpate(uid);
			} else if(input!=null && input.equals(CommandConstants.BOOK_SEARCH)) {
				bookSearch();
			} else if(input!=null && input.equals(CommandConstants.BOOK_DELETE)) {
				bookDelete(uid);
			} else if(input!=null && input.equals(CommandConstants.BOOK_BUY)) {
				buyEMail(uid);
			} // if 
			
			
		} // while
	}
	
	/**
	 * 프로그램 종료
	 */
	public static void appExit() {
		System.out.println("프로그램이 종료됩니다.");				
		System.exit(0);	
	}
	
	/**
	 * 유저리스트 출력
	 * @param list
	 */
	public void printUserList(List<UserInfo> list) {

		if(list.isEmpty()) {
			return;
		}
        System.out.println("uID\t\tPW\t\tname\tphone\t\temail\t\t\tstate\t\tmanagerYn");
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
       
        for(UserInfo info:list) {
        	System.out.println(info.getPrtInfo());
        }
        
	}
	
	/**
	 * 책 리스트 출력
	 * @param list
	 */
	public void printBookList(List<BookInfo> list) {

		if(list.isEmpty()) {
			return;
		}
        System.out.println("ID\t제목\t\t\t\tISBN\t\t저자\t\t출판사\t\t년도\t판매자ID\t가격\t책 상태");
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
       
        for(BookInfo info:list) {
        	System.out.println(info.getPrtInfo());
        }
        
	}
	
	/**
	 * 도서 조회
	 * @param uid
	 */
	public void bookSearch() {
		
		String inputCondition = null;
		String inputVaue = null;
		
		BookController controllerBook = ControllerFactory.getInstance().getBookController();
		Scanner sc = new Scanner(System.in);
		
		while(true) { //booksearch만 상위메뉴로가기, 프로그램종료 입력값이 특별함
			System.out.println("\n==========도서조회==========");
			System.out.println("검색조건과 조건값을 입력해주세요");
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
			(inputCondition.equals(SearchCondition.BOOKNAME) || inputCondition.equals(SearchCondition.AUTHOR)
			 || inputCondition.equals(SearchCondition.ISBN) || inputCondition.equals(SearchCondition.YEAR)
			 || inputCondition.equals(SearchCondition.PUBLISHER)||inputCondition.equals(SearchCondition.SELLER))) {
				List<BookInfo> bookInfoList = controllerBook.getBookList(inputVaue, inputCondition);
				if(bookInfoList.isEmpty()) {
					System.out.println("일치하는 책이 없습니다. 다시 입력해주세요");
					continue;
				}
				printBookList(bookInfoList);
				break;
			} //if
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
		System.out.println("\n==========도서 등록==========");
		System.out.println("명령어를 입력하세요:");
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
			System.out.print("ISBN (pass는 : p 입력) : "); 
			input = sc.next();
			if(input != null && !input.equals("p")) {
				book.setISBN(input);
			}
			else {
				book.setISBN(" ");
			}
			System.out.print("저자 (pass는 : p 입력) : "); 
			input = sc.next();
			if(input != null && !input.equals("p")) {
				book.setAuthor(input);
			}
			else {
				book.setAuthor("");
			}
			System.out.print("출판사 (pass는 : p 입력) : "); 
			input = sc.next();
			if(input != null && !input.equals("p")) {
				book.setPublisher(input);
			}
			else {
				book.setPublisher("");
			}
			System.out.print("년도 (pass는 : p 입력) : "); 
			input = sc.next();
			if(input != null && !input.equals("p")) {
				book.setYear(input);
			}
			else {
				book.setYear("");
			}
			System.out.print("가격 (pass는 : p 입력) : "); 
			input = sc.next();
			if(input != null && !input.equals("p")) {
				book.setPrice(input);
			}
			else {
				book.setPrice("");
			}
			System.out.print("책 상태 (pass는 : p 입력) : "); 
			input = sc.next();
			if(input != null && !input.equals("p")) {
				book.setState(input);
			}
			else {
				book.setState("");
			}
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
	 * 도서 수정
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
		
		if(list.isEmpty()) {
			System.out.println("등록된 책이 없습니다. 책을 등록해 주세요.");
			return;
		}
		
		while(true) {		
			book.setSellerID(uid);
			System.out.println("\n==========도서 수정==========");
			System.out.println("수정 할 도서 ID를 입력해주세요 : ");
			System.out.println("00:상위메뉴로 가기");
			System.out.println("99:프로그램 종료");	
			System.out.print(">> "); sc = new Scanner(System.in);
			input = sc.next();
			if ( input.equals(CommandConstants.TOP_MENU) ) {
				break;
			} else if ( input.equals(CommandConstants.APP_EXIT) ) {
				BookStoreMainView.appExit();
			} else if(input!=null && !input.equals("")) {
				book.setId(input);		
			} else {
				System.out.println("수정 할 도서 ID는 필수 입력입니다.");
				continue;
			}
			
			System.out.println("\n==========도서수정==========");
			System.out.println("수정할 책 제목을 입력해주세요 : ");
			input = sc.next();
			if(input!=null && !input.equals("")) {
				book.setName(input);		
			} else {
				System.out.println("책 제목은 필수 입력입니다. ");
				continue;
			}
			System.out.print("ISBN (pass는 : p 입력) : "); 
			input = sc.next();
			if(input != null && !input.equals("p")) {
				book.setISBN(input);
			}
			System.out.print("저자 (pass는 : p 입력) :"); 
			input = sc.next();
			if(input != null && !input.equals("p")) {
				book.setAuthor(input);
			}
			System.out.print("출판사 (pass는 : p 입력) :"); 
			input = sc.next();
			if(input != null && !input.equals("p")) {
				book.setPublisher(input);
			}
			System.out.print("년도 (pass는 : p 입력) :");
			input = sc.next();
			if(input != null && !input.equals("p")) {
				book.setYear(input);
			}
			System.out.print("가격 (pass는 : p 입력) :");
			input = sc.next();
			if(input != null && !input.equals("p")) {
				book.setPrice(input);
			}
			System.out.print("책 상태 (pass는 : p 입력) :");
			input = sc.next();
			if(input != null && !input.equals("p")) {
				book.setState(input);
			}
			int result = controllerBook.updateBook(book);
			if(result > 0) {
				System.out.println("책이 정상적으로  수정되었습니다.");
			} else {
				System.out.println("책이 정상적으로 수정되지 않았습니다.\n관리자에게 문의하세요.");
			}
			break;
		}//while
		
	}
	
	/**
	 * 도서 삭제
	 * @param uid
	 */
	public void bookDelete(String uid) {
		Scanner sc = new Scanner(System.in);
		String input = null;
		BookInfo book = new BookInfo();
		System.out.println(uid + "님께서 등록한 도서 목록 입니다.");
		BookController controllerBook = ControllerFactory.getInstance().getBookController();
		List<BookInfo> list = controllerBook.getBookList(uid, SearchCondition.SELLER);
		printBookList(list);
		if(list.isEmpty()) {
			System.out.println("등록 된 도서가 없습니다. 도서를 등록해주세요.");
			return;
		}
		while(true) {
			System.out.println("\n==========도서 삭제==========");
			System.out.println("삭제 할 도서 ID를 입력해주세요.");
			System.out.println("00:상위메뉴로 가기");
			System.out.println("99:프로그램 종료");	
			System.out.print(">> "); sc = new Scanner(System.in);
			input = sc.next();
			if ( input.equals(CommandConstants.TOP_MENU) ) {
				break;
			} else if ( input.equals(CommandConstants.APP_EXIT) ) {
				BookStoreMainView.appExit();
			} else if(input!=null && !input.equals("")) {
				int result = controllerBook.deleteBook(input);
				if(result > 0) {
					System.out.println("책이 정상적으로  삭제되었습니다.");
				} else {
					System.out.println("책이 정상적으로 삭제되지 않았습니다.\n관리자에게 문의하세요.");
				}
				break;
			} else {
				System.out.println("삭제 할 도서 ID는 필수 입력입니다.");
				continue;
			}//if
		}//while
	}
	
	/**
	 * 도서구매 ( 이메일발송기능 )
	 */
	public void buyEMail(String uid) {
		UserController userController = ControllerFactory.getInstance().getUserController();
		EMailController emailController = ControllerFactory.getInstance().getEMailController();
		BookController bookController = ControllerFactory.getInstance().getBookController();
		
		UserInfo sender = userController.getUser(uid);
		UserInfo reciever = null;
		BookInfo bookInfo = null;
		Scanner sc = new Scanner(System.in);
		String input = null;
		// 구매할 책 선택
		bookSearch();
		// 
		while(true) {
			System.out.println("\n==========도서구매==========");
			System.out.println("구매 할 책의 ID를 입력해주세요 : ");
			System.out.println("00:상위메뉴로 가기");
			System.out.println("99:프로그램 종료");	
			System.out.print(">> "); sc = new Scanner(System.in);
			input = sc.next();
			if ( input.equals(CommandConstants.TOP_MENU) ) {
				break;
			} else if ( input.equals(CommandConstants.APP_EXIT) ) {
				BookStoreMainView.appExit();
			} else if(input!=null && !input.equals("")) {
				//구매할 책의 id
				bookInfo = bookController.getBook(input, SearchCondition.BOOKUID);
				reciever = userController.getUser(bookInfo.getSellerID());
				break;
			} else {
				System.out.println("구매 할 도서 ID는 필수 입력입니다.");
				continue;
			}
		}
		
		emailController.sendEMail(sender, reciever, bookInfo);
	}
}

