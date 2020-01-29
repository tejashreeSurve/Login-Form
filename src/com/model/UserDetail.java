package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDetail {
	String url = "jdbc:mysql://localhost:3306/LoginUser?useSSL=false";
	String username = "root";
	String password = "root";

	ArrayList<UserInformation> list = new ArrayList<UserInformation>();

	// insert email and password into database 
	public void insertData(String pname, String pemail, String puserPassword)
			throws ClassNotFoundException, SQLException {
		String query = "insert into userDetails values(?,?,?)";
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(url, username, password);
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, pname);
		preparedStatement.setString(2, pemail);
		preparedStatement.setString(3, puserPassword);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		connection.close();
	}

	// fetch records from database 
	public ArrayList<UserInformation> getData() throws ClassNotFoundException, SQLException {
		String query = "select * from userDetails";
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(url, username, password);
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		ResultSet resultset = preparedStatement.executeQuery();
		while (resultset.next()) {
			String name = resultset.getString(1);
			String email = resultset.getString(2);
			String userPassword = resultset.getString(3);
			list.add(new UserInformation(name, email, userPassword));
		}
		preparedStatement.close();
		connection.close();
		return list;
	}
}
