package com.book.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.book.controller.LoginController;
import com.book.domain.UserInfo;
import com.book.factory.ControllerFactory;

class LoginControllerTest {

	@Test
	void testDoLogin() { //Login이 잘 되는지 확인 - admin의 이름이 관리자인지
		LoginController logInController = ControllerFactory.getInstance().getLoginController();
		UserInfo info = logInController.doLogin("admin", "nayana");
		assertEquals(info.getName(), "관리자");
	}

}
