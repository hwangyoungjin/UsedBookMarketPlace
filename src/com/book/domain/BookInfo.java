package com.book.domain;

/**
 * 책 정보 Domain
 * @author USER
 *
 */
public class BookInfo {
	
	private String name; 
	private String ISBN; 
	private String author; 
	private String publisher; 
	private String year;
	private String sellerID;
	private String price;
	private String state;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getSellerID() {
		return sellerID;
	}
	public void setSellerID(String sellerID) {
		this.sellerID = sellerID;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		
		return ""
				+ "[name=" + name 
				+ ", ISBN=" + ISBN 
				+ ", author=" + author 
				+ ", publisher=" + publisher
				+ ", year=" + year 
				+ ", sellerID=" + sellerID 
				+ ", price=" + price 
				+ ", state=" + state + "]";
	}


	
}
