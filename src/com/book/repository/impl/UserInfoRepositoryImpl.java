package com.book.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.book.domain.UserInfo;
import com.book.util.JDBCUtils;

/**
 * 사용자 관리 Reposity 구현 클래스 
 * @author USER
 *
 */
public class UserInfoRepositoryImpl implements com.book.repository.UserInfoRepository {
	
	@Override
	public int createUser(UserInfo userInfo) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int count = 0;

		StringBuffer buf = new StringBuffer()
				.append("INSERT INTO user (uid, pw, name, phone, email, state, managerYn)")
				.append("  VALUES (?, ?, ?, ?, ?, ?, ?) ");
		
		try {
        	
        	connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(buf.toString());
            preparedStatement.setString(1, userInfo.getUid());
            preparedStatement.setString(2, userInfo.getPw());
            preparedStatement.setString(3, userInfo.getName());
            preparedStatement.setString(4, userInfo.getPhone());
            preparedStatement.setString(5, userInfo.getEmail());
            preparedStatement.setString(6, userInfo.getState());
            preparedStatement.setString(7, userInfo.getManagerYn());
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
	public UserInfo getLoginUser(String id, String pw) {
		String query = new StringBuffer()
				.append("SELECT uid, pw, name, phone, email, state, managerYn ")
				.append("  FROM user   ")
				.append(" WHERE uid = ? ")
				.append(" AND pw = ? ")
				.toString();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		UserInfo userInfo = null;
		
        try {
        	
        	connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, pw);

           // System.out.println("SELECT SQL->"+preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	userInfo = new UserInfo();
                userInfo.setUid(rs.getString("uid"));
                userInfo.setPw(rs.getString("pw"));
                userInfo.setName(rs.getString("name"));
                userInfo.setPhone(rs.getString("phone"));
                userInfo.setEmail(rs.getString("email"));
                userInfo.setState(rs.getString("state"));
                userInfo.setManagerYn(rs.getString("managerYn"));
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
		
		return userInfo;
	}


	@Override
	public UserInfo getUser(String id) {

		String query = new StringBuffer()
				.append("SELECT uid, pw, name, phone, email, state, managerYn ")
				.append("  FROM user   ")
				.append(" WHERE uid = ? ")
				.toString();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		UserInfo userInfo = null;
		
        try {
        	
        	connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id); // 위에 있는 첫번째의 '?' 값 지정
          //  System.out.println("SELECT SQL->"+preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	userInfo = new UserInfo();
                userInfo.setUid(rs.getString("uid"));
                userInfo.setPw(rs.getString("pw"));
                userInfo.setName(rs.getString("name"));
                userInfo.setPhone(rs.getString("phone"));
                userInfo.setEmail(rs.getString("email"));
                userInfo.setState(rs.getString("state"));
                userInfo.setManagerYn(rs.getString("managerYn"));
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
		
		return userInfo;
	}

	@Override
	public List<UserInfo> getAllUsers() {
		ArrayList<UserInfo> userInfoList = new ArrayList<>();
		
		StringBuffer buf = new StringBuffer()
				.append("SELECT uid, pw, name, phone, email, state, managerYn ")
				.append("  FROM user   ");
		
		String query = buf.toString();
				
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
        	
        	connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(query);
           // System.out.println("SELECT SQL->"+preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
        		UserInfo userInfo = new UserInfo();
                userInfo.setUid(rs.getString("uid"));
                userInfo.setPw(rs.getString("pw"));
                userInfo.setName(rs.getString("name"));
                userInfo.setPhone(rs.getString("phone"));
                userInfo.setEmail(rs.getString("email"));
                userInfo.setState(rs.getString("state"));
                userInfo.setManagerYn(rs.getString("managerYn"));
            	userInfoList.add(userInfo);
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

		return userInfoList;
	}
	

	@Override
	public int deleteUser(UserInfo userInfo) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int count = 0;

		StringBuffer buf = new StringBuffer();
//				.append("UPDATE user SET uid=?, pw=?, name=?, phone=?, email=?, state=?, managerYn =? ")
				if(userInfo.getState().equals("activate")) {
					return -1;
				}
				else { // 사용자가 deactivate라면
					buf.append("DELETE FROM user WHERE uid = ? ");
				}
				
		try {
        	
        	connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(buf.toString());
            preparedStatement.setString(1, userInfo.getUid());
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
	public int updateUser(UserInfo userInfo) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int count = 0;

		StringBuffer buf = new StringBuffer();
//				.append("UPDATE user SET uid=?, pw=?, name=?, phone=?, email=?, state=?, managerYn =? ")
				if(userInfo.getState().equals("activate")) {
					buf.append("UPDATE user SET state = 'deactivated'");
				}
				else {
					buf.append("UPDATE user SET state = 'activate'");
				}
				buf.append("  WHERE uid = ? ");
		
		try {
        	
        	connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(buf.toString());
            preparedStatement.setString(1, userInfo.getUid());
            //System.out.println("UPDATE SQL->"+preparedStatement);
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
