package com.igeek.dao.impl;

import java.util.List;

import com.igeek.bean.User;
import com.igeek.reflect.DaoHandler;

public class UserDaoImpl {
	public List<User> findAll()
	{
		String sql = "select * from users";
		return DaoHandler.executeQuery(sql, User.class);
	}
}
