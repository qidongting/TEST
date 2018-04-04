package com.igeek.test;

import java.util.List;

import com.igeek.bean.User;
import com.igeek.dao.impl.UserDaoImpl;

public class TestDao {
	public static void main(String[] args) {
		UserDaoImpl udao = new UserDaoImpl();
		List<User> list = udao.findAll();
		System.out.println(list.size());
		for(User user : list)
		{
			System.out.println(user.getUsername() + "---" + user.getUserpwd() + " ---" + user.getRegtime());
		}
	}
}
