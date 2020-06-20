package com.book.controller.impl;

import com.book.controller.EMailController;
import com.book.domain.BookInfo;
import com.book.domain.UserInfo;

public class EMailControllerImpl implements EMailController {

	@Override
	public void sendEMail(UserInfo sender, UserInfo reciever, BookInfo bookInfo) {
		System.out.println("발신자 ID : "+sender.getUid()+" 님의 이메일 : "+sender.getEmail()+" 으로부터");
		System.out.println("수신자 ID : "+reciever.getUid()+" 님의 이메일 : "+reciever.getEmail()+" 으로");
		System.out.println(bookInfo.getName()+" 책의 구매 요청 메일을 보냈습니다.");
	}

}
