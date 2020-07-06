package com.book.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.book.controller.UserController;
import com.book.domain.UserInfo;
import com.book.factory.ControllerFactory;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerTest {

	UserController userController = ControllerFactory.getInstance().getUserController();
	
	@Test
	@Order(1)
	void testGetUser() { //유저가 잘 불러와지는지 확인
		UserInfo user =  userController.getUser("test1");
		assertNotEquals(null, user);
	}

	@Test
	@Order(2)
	void testGetAllUsers() { // 모든 유저가 잘 불러와지는지 확인
		List<UserInfo> users = userController.getAllUsers();
		assertTrue(!users.isEmpty());
	}

	@Test
	@Order(3)
	void testUpdateUser() { //상태가 activated인 유저 상태변경이 잘 되는지 확인
		UserInfo user = userController.getUser("test1");
		assertEquals(userController.updateUser(user), 1);
	}
	
	@Test 
	@Order(4)
	void testDeleteUser() { //uid가 test1이고 , 상태가 deactivated인 유저 잘 삭제 되는지
		UserInfo user = userController.getUser("test1");
		assertEquals(userController.deleteUser(user), 1);
	}
}
