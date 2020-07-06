package com.book.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.book.controller.SignUpController;
import com.book.domain.UserInfo;
import com.book.factory.ControllerFactory;

class SignUpControllerTest {

	@Test
	void testSignUp() { //가입 잘 된는지 확인
		UserInfo user = new UserInfo();
		user.setName("test1");
		user.setUid("test1");
		user.setPw("test1");
		user.setState("activated");
		user.setEmail("test1@gamil.com");
		SignUpController signupController = ControllerFactory.getInstance().getSignUpController();
		assertEquals(signupController.signUp(user), 1);
	}

}
