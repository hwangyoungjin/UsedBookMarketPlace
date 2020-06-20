package com.book.controller;

import com.book.domain.BookInfo;
import com.book.domain.UserInfo;

public interface EMailController {
	public void sendEMail(UserInfo sender,UserInfo reciever, BookInfo bookInfo);
}
