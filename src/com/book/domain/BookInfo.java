package com.book.domain;

/**
 * 책 정보 Domain
 * @author USER
 *
 */
public class BookInfo {
	
	private String id; 
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
		if(name!=null && !name.equals("")) this.name = name;
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
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPrtInfo() {
		
//		return ""
//				+ id + "\t"				
//				+ name + "          \t"
//				+ ISBN + "          \t"
//				+ author + "          \t"
//				+ publisher+ "          \t"
//				+ year + "          \t"
//				+ sellerID+ "          \t" 
//				+ price + "          \t"
//				+ state + "          \t";
		
    	return new StringBuffer()
    	 .append(this.getId()).append("\t")
		.append(this.getName()).append("\t\t")
		.append(this.getISBN()).append("\t\t")
		.append(this.getAuthor()).append("\t\t")
		.append(this.getPublisher()).append("\t\t")
		.append(this.getYear()).append("\t")
		.append(this.getSellerID()).append("\t\t")
		.append(this.getPrice()).append("\t")
		.append(this.getState()).toString();
	}
	
	
	@Override
	public String toString() {
		
		return ""
				+ "[ id=" + id + "\n"				
				+ "  name=" + name + "\n"
				+ ", ISBN=" + ISBN + "\n"
				+ ", author=" + author + "\n"
				+ ", publisher=" + publisher+ "\n"
				+ ", year=" + year + "\n"
				+ ", sellerID=" + sellerID+ "\n" 
				+ ", price=" + price + "\n"
				+ ", state=" + state + "]";
	}
	
}
