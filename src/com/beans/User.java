package com.beans;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.model.UserDetail;
import com.model.UserInformation;

public class User {
	UserDetail user = new UserDetail();
	
	// regex checking for email and password
	public boolean validate(String name, String email, String password) {
		if (name.equals(""))
			return false;
		if (email.equals(""))
			return false;
		if (!email.matches("\\w+\\@\\w+\\.\\w+"))
			return false;
		if (!password.matches("\\w+\\d+"))
			return false;
		return true;
	}

	// check if email or password present in database or not
	public boolean isPresent(String email, String password) throws ClassNotFoundException, SQLException {
		ArrayList<UserInformation> list = new ArrayList<UserInformation>();
		list = user.getData();
		for (UserInformation info : list) {
			if (email.equals(info.getEmail()) && password.equals(info.getPassword()))
				return true;
		}
		return false;
	}
}
