package com.book.domain;

/**
 * 책 정보 Domain
 * 
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
		if (name != null && !name.equals(""))
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPrtInfo() {

		StringBuffer buf = new StringBuffer();
		buf.append(this.getId()).append("\t");

		if (this.getName().length() > "\t\t\t\t\t\t\t\t\t\t".length()) { // 이름의 길이가 11보다 크다면
			buf.append(this.getName()).append("\t");
		} else if (this.getName().length() > "\t\t\t\t\t\t\t\t".length()) { // 이름의 길이가 8보다 크다면
			buf.append(this.getName()).append("\t\t");
		}
		else if(this.getName().length() > "\t\t\t\t".length()) { // 이름의 길이가 4보다크다면 
			buf.append(this.getName()).append("\t\t\t"); 
		}
		else {
			buf.append(this.getName()).append("\t\t\t\t");
		}

		if (this.getISBN() == null) {
			buf.append("\t\t\t");
		} else if (this.getISBN().length() > "\t\t\t\t\t\t\t".length()) { // 이름의 길이가 7보다 크다면
			buf.append(this.getISBN()).append("\t");
		} else {
			buf.append(this.getISBN()).append("\t\t");
		}

		if (this.getAuthor() == null) {
			buf.append("\t\t\t");
		} else if (this.getAuthor().length() > "\t\t\t\t\t".length()) { // 이름의 길이가 7보다 크다면
			buf.append(this.getAuthor()).append("\t");
		} else
			buf.append(this.getAuthor()).append("\t\t");

		if (this.getPublisher() == null) {
			buf.append("\t\t");
		} else
			buf.append(this.getPublisher()).append("\t\t");

		if (this.getYear() == null) {
			buf.append("\t");
		} else
			buf.append(this.getYear()).append("\t");

		if (this.getSellerID().length() > "\t\t\t\t\t\t\t".length()) { // 이름의 길이가 7보다 크다면
			buf.append(this.getSellerID()).append("\t");
		} else if (this.getSellerID().length() < "\t\t\t".length()) { // 이름의 길이가 3보다 작다면
			buf.append(this.getSellerID()).append("\t\t\t");
		} else {
			buf.append(this.getSellerID()).append("\t\t");
		}

		if (this.getPrice() == null) {
			buf.append("\t");
		} else
			buf.append(this.getPrice()).append("\t");

		if (this.getState() == null) {
			buf.append("");
		} else
			buf.append(this.getState());

		return buf.toString();
	}

	@Override
	public String toString() {

		return "" + "[ id=" + id + "\n" + "  name=" + name + "\n" + ", ISBN=" + ISBN + "\n" + ", author=" + author
				+ "\n" + ", publisher=" + publisher + "\n" + ", year=" + year + "\n" + ", sellerID=" + sellerID + "\n"
				+ ", price=" + price + "\n" + ", state=" + state + "]";
	}

}
