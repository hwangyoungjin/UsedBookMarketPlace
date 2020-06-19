package com.book.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.book.domain.UserInfo;
import com.book.util.JDBCUtils;

/**
 * 사용자 관리 Reposity 구현 클래스 
 * @author USER
 *
 */
public class UserInfoRepositoryImpl implements com.book.repository.UserInfoRepository {

//	public static void main(String args[]) {
//		UserInfoRepositoryImpl impl = new UserInfoRepositoryImpl();
//		impl.getUser("test");
//	}
	
	@Override
	public void createUser(UserInfo userInfo) {
		// TODO Auto-generated method stub

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

            System.out.println("SELECT SQL->"+preparedStatement);
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
            preparedStatement.setString(1, id);
            System.out.println("SELECT SQL->"+preparedStatement);
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteUser(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateUser(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
