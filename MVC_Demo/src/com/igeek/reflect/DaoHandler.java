package com.igeek.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DaoHandler {
	public static<T> List<T> executeQuery(String sql,Class<T> clazz,Object...params)
	{
		Connection con = DaoUtils.getConnection();
		PreparedStatement ps = null;
		List<T> list = new ArrayList<T>();
		if(con!=null)
		{
			try {
				ps = con.prepareStatement(sql);
				
				for(int i=0;i<params.length;i++)
				{
					ps.setObject(i+1, params[i]);
				}
				ResultSet rs = ps.executeQuery();
				ResultSetMetaData meta = rs.getMetaData();
				//获取查询所有字段名称
				String[] names = getColumnNames(meta);
				Method[] methods = clazz.getDeclaredMethods();
				while(rs.next())
				{
					//创建一个对象
					T t = clazz.newInstance();
					//遍历其中每一行
					for(int i=0;i<names.length;i++)
					{
						//根据该列名称找到该方法
						for(Method method : methods)
						{
							//方法匹配上
							if(method.getName().equalsIgnoreCase("set" + names[i]))
							{
								Object result = null;
								//判断该方法参数数据类型
								Class type = method.getParameterTypes()[0];
								if(type==int.class)
								{
									result = rs.getInt(i+1);
								}else if(type==double.class)
								{
									result = rs.getDouble(i+1);
								}else if(type==Date.class)
								{
									result = rs.getDate(i+1);
								}else if(type==String.class)
								{
									result = rs.getString(i+1);
								}else{
									result = rs.getObject(i+1);
								}
								
								
								//调用该方法,赋值
								method.invoke(t, result);
							}
						}
					}
					
					list.add(t);
					
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}

	private static String[] getColumnNames(ResultSetMetaData meta) {
		String[] names = null;
		try {
			int length = meta.getColumnCount();
			names = new String[length];
			
			for(int i=0;i<length;i++)
			{
				names[i] = meta.getColumnName(i+1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return names;
	}
}
