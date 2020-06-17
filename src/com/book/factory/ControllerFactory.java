package com.book.factory;

import com.book.controller.UserController;
import com.book.controller.impl.UserControllerImpl;

public class ControllerFactory {
	
	private static ControllerFactory instance = new ControllerFactory();
	private ControllerFactory() {}
	
	public static ControllerFactory getInstance() {
		return instance;
	}
	
	public static UserController getUserController() {
		return new UserControllerImpl();
	}
	
}
