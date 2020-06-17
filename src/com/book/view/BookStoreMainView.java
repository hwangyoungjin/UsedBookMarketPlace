package com.book.view;

import com.book.controller.UserController;
import com.book.domain.UserInfo;
import com.book.factory.ControllerFactory;

public class BookStoreMainView {

	public static void main(String[] args) {

		UserController controller = ControllerFactory.getInstance().getUserController();
		UserInfo info = controller.getUser("test");
		System.out.println(info);
	
		
	}

}
