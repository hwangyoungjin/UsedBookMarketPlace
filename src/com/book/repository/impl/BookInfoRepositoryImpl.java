package com.book.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.book.constants.SearchCondition;
import com.book.domain.BookInfo;
import com.book.repository.BookInfoRepository;
import com.book.util.JDBCUtils;

public class BookInfoRepositoryImpl implements BookInfoRepository {

	@Override
	public int createBook(BookInfo bookInfo) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int count = 0;

		StringBuffer buf = new StringBuffer()
				.append("INSERT INTO book (name, ISBN, author, publisher, year, sellerID, price, state)")
				.append("  VALUES (?, ?, ?, ?, ?, ?, ?, ?) ");
		
		try {
        	
        	connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(buf.toString());
            preparedStatement.setString(1, bookInfo.getName());
            preparedStatement.setString(2, bookInfo.getISBN());
            preparedStatement.setString(3, bookInfo.getAuthor());
            preparedStatement.setString(4, bookInfo.getPublisher());
            preparedStatement.setString(5, bookInfo.getYear());
            preparedStatement.setString(6, bookInfo.getSellerID());
            preparedStatement.setString(7, bookInfo.getPrice());
            preparedStatement.setString(8, bookInfo.getState());
           // System.out.println("INSERT SQL->"+preparedStatement);
            count = preparedStatement.executeUpdate();
         
		} catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        } finally {
        	// 자원 반납
        	try {
        		if(preparedStatement != null) preparedStatement.close();
        		if(connection != null) connection.close();
        	} catch(SQLException se) {
                JDBCUtils.printSQLException(se);
        	}
        } 

		return count;
	}

	@Override
	public BookInfo getBook(String param, String serachCondition) {
						
		StringBuffer buf = new StringBuffer()
				.append("SELECT name, ISBN, author, publisher, year, sellerID, price, state ")
				.append("  FROM book   ");
		
		if(serachCondition.equals(SearchCondition.BOOKNAME)) {
			buf.append(" WHERE name = ? ");
		}else if(serachCondition.equals(SearchCondition.AUTHOR)) {
			buf.append(" WHERE author = ? ");	
		}
		else if(serachCondition.equals(SearchCondition.BOOKUID)) {
			buf.append(" WHERE id = ? ");	
		}
		else if(serachCondition.equals(SearchCondition.SELLER)) {
			buf.append(" WHERE sellerID = ? ");	
		}
		String query = buf.toString();
				
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		BookInfo bookInfo = null;
		
		try {
        	
        	connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, param);
          //  System.out.println("SELECT SQL->"+preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	bookInfo = new BookInfo();
            	bookInfo.setName(rs.getString("name"));
            	bookInfo.setISBN(rs.getString("ISBN"));
            	bookInfo.setAuthor(rs.getString("author"));
            	bookInfo.setPublisher(rs.getString("publisher"));
            	bookInfo.setYear(rs.getString("year"));
            	bookInfo.setSellerID(rs.getString("sellerID"));
            	bookInfo.setPrice(rs.getString("price"));
            	bookInfo.setState(rs.getString("state"));
            }
                        
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        } finally {
        	// 자원 반납
        	try {
        		if(preparedStatement != null) preparedStatement.close();
        		if(connection != null) connection.close();
        	} catch(SQLException se) {
                JDBCUtils.printSQLException(se);
        	}
        } 

		return bookInfo;
	}
	

	@Override
	public List<BookInfo> getBookList(String param, String serachCondition) {
		
		ArrayList<BookInfo> bookInfoList = new ArrayList<>();
		
		StringBuffer buf = new StringBuffer()
				.append("SELECT id, name, ISBN, author, publisher, year, sellerID, price, state ")
				.append("  FROM book   ");
		
		if(serachCondition.equals(SearchCondition.BOOKNAME)) {
			buf.append(" WHERE name LIKE ? ");
		}else if(serachCondition.equals(SearchCondition.ISBN)) {
			buf.append(" WHERE ISBN LIKE ? ");
		}else if(serachCondition.equals(SearchCondition.AUTHOR)) {
			buf.append(" WHERE author LIKE ? ");
		}else if(serachCondition.equals(SearchCondition.PUBLISHER)) {
			buf.append(" WHERE publisher LIKE ? ");
		}else if(serachCondition.equals(SearchCondition.YEAR)) {
			buf.append(" WHERE year LIKE ? ");
		}else if(serachCondition.equals(SearchCondition.SELLER)) {
			buf.append(" WHERE sellerID LIKE ? ");					
		}
		String query = buf.toString();
				
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
        	
        	connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%"+param+"%");
           // System.out.println("SELECT SQL->"+preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
        		BookInfo bookInfo = new BookInfo();
            	bookInfo.setId(rs.getString("id"));
            	bookInfo.setName(rs.getString("name"));
            	bookInfo.setISBN(rs.getString("ISBN"));
            	bookInfo.setAuthor(rs.getString("author"));
            	bookInfo.setPublisher(rs.getString("publisher"));
            	bookInfo.setYear(rs.getString("year"));
            	bookInfo.setSellerID(rs.getString("sellerID"));
            	bookInfo.setPrice(rs.getString("price"));
            	bookInfo.setState(rs.getString("state"));
            	bookInfoList.add(bookInfo);
            }
                        
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        } finally {
        	// 자원 반납
        	try {
        		if(preparedStatement != null) preparedStatement.close();
        		if(connection != null) connection.close();
        	} catch(SQLException se) {
                JDBCUtils.printSQLException(se);
        	}
        } 

		return bookInfoList;
	}

	@Override
	public List<BookInfo> getAllBooks() {
	ArrayList<BookInfo> bookInfoList = new ArrayList<>();
		
		StringBuffer buf = new StringBuffer()
				.append("SELECT id, name, ISBN, author, publisher, year, sellerID, price, state ")
				.append("  FROM book   ");
		
		String query = buf.toString();
				
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
        	
        	connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(query);
            //System.out.println("SELECT SQL->"+preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	BookInfo bookInfo = new BookInfo();
            	bookInfo.setId(rs.getString("id"));
            	bookInfo.setName(rs.getString("name"));
            	bookInfo.setISBN(rs.getString("ISBN"));
            	bookInfo.setAuthor(rs.getString("author"));
            	bookInfo.setPublisher(rs.getString("publisher"));
            	bookInfo.setYear(rs.getString("year"));
            	bookInfo.setSellerID(rs.getString("sellerID"));
            	bookInfo.setPrice(rs.getString("price"));
            	bookInfo.setState(rs.getString("state"));
            	bookInfoList.add(bookInfo);
            }
                        
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        } finally {
        	// 자원 반납
        	try {
        		if(preparedStatement != null) preparedStatement.close();
        		if(connection != null) connection.close();
        	} catch(SQLException se) {
                JDBCUtils.printSQLException(se);
        	}
        } 

		return bookInfoList;
	}

	@Override
	public int deleteBook(String id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int count = 0;

		StringBuffer buf = new StringBuffer();
				buf.append("DELETE FROM book WHERE id = ? ");
				
				
		try {
        	
        	connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(buf.toString());
            preparedStatement.setString(1, id);
           // System.out.println("DELETE SQL->"+preparedStatement);
            count = preparedStatement.executeUpdate();
         
		} catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        } finally {
        	// 자원 반납
        	try {
        		if(preparedStatement != null) preparedStatement.close();
        		if(connection != null) connection.close();
        	} catch(SQLException se) {
                JDBCUtils.printSQLException(se);
        	}
        } 

		return count;
	}

	@Override
	public int updateBook(BookInfo bookInfo) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int count = 0;

		StringBuffer buf = new StringBuffer()
				.append("UPDATE book SET name = ?, ISBN = ?, author = ?, publisher = ?, year = ?, sellerID = ?, price = ?, state = ? ")
				.append("  WHERE id = ? ");
		
		try {
        	
        	connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(buf.toString());
            preparedStatement.setString(1, bookInfo.getName());
            preparedStatement.setString(2, bookInfo.getISBN());
            preparedStatement.setString(3, bookInfo.getAuthor());
            preparedStatement.setString(4, bookInfo.getPublisher());
            preparedStatement.setString(5, bookInfo.getYear());
            preparedStatement.setString(6, bookInfo.getSellerID());
            preparedStatement.setString(7, bookInfo.getPrice());
            preparedStatement.setString(8, bookInfo.getState());
            preparedStatement.setString(9, bookInfo.getId());
          //  System.out.println("UPDATE SQL->"+preparedStatement);
            count = preparedStatement.executeUpdate();
         
		} catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        } finally {
        	// 자원 반납
        	try {
        		if(preparedStatement != null) preparedStatement.close();
        		if(connection != null) connection.close();
        	} catch(SQLException se) {
                JDBCUtils.printSQLException(se);
        	}
        } 

		return count;
	}

	@Override
	public int allDeleteUserBook(String sellerID) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int count = 0;

		StringBuffer buf = new StringBuffer();
			buf.append(" DELETE FROM book WHERE sellerID LIKE ? ");
		try {
        	
        	connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(buf.toString());
            preparedStatement.setString(1, "%"+sellerID+"%");
           // System.out.println("DELETE SQL->"+preparedStatement);
            count = preparedStatement.executeUpdate();
         
		} catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        } finally {
        	// 자원 반납
        	try {
        		if(preparedStatement != null) preparedStatement.close();
        		if(connection != null) connection.close();
        	} catch(SQLException se) {
                JDBCUtils.printSQLException(se);
        	}
        } 

		return count;
	}
}
