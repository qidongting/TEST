package com.igeek.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserAction implements IModel {
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		System.out.println("进入login方法");
		return "pages/board.jsp";
	}
}
