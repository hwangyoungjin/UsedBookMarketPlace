package com.book.factory;

import com.book.controller.BookController;
import com.book.controller.EMailController;
import com.book.controller.LoginController;
import com.book.controller.SignUpController;
import com.book.controller.UserController;
import com.book.controller.impl.BookControllerImpl;
import com.book.controller.impl.EMailControllerImpl;
import com.book.controller.impl.LoginControllerImpl;
import com.book.controller.impl.SignUpControllerImpl;
import com.book.controller.impl.UserControllerImpl;

public class ControllerFactory {
	
	private static ControllerFactory instance = new ControllerFactory();
	private ControllerFactory() {}
	
	public static ControllerFactory getInstance() {
		return instance;
	}
	
	public UserController getUserController() {
		return new UserControllerImpl();
	}
	
	public BookController getBookController() {
		return new BookControllerImpl();
	}
	
	public LoginController getLoginController() {
		return new LoginControllerImpl();
	}
	
	public SignUpController getSignUpController() {
		return new SignUpControllerImpl();
	}
	public EMailController getEMailController() {
		return new EMailControllerImpl();
	}
	
}
