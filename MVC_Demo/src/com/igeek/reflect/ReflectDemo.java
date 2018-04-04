package com.igeek.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

import com.igeek.bean.User;

public class ReflectDemo {
	public static void main(String[] args) {
		//通过反射获取类对象
		try {
			Class<User> clazz =(Class<User>)Class.forName("com.igeek.bean.User");
			
			//根据类创建对象
			User user = clazz.newInstance();
			
			
			//获取类的结构信息
			//1.获取类中封装的属性
			Field[] fields = clazz.getDeclaredFields();
			
//			for(Field field : fields)
//			{
//				//System.out.println(field.getName());
//			}
//			
			
			//获取所有声明的方法
			Method[] methods = clazz.getDeclaredMethods();
//			for (Method method : methods) {
//				System.out.println(method.getName());
//			}
			
			//获取具体某一个方法
			Method method = clazz.getDeclaredMethod("setUsername", String.class);
			
			//调用该方法
			method.invoke(user, "li三");
			
			Method method1 = clazz.getDeclaredMethod("getUsername");
			Object result = method1.invoke(user);
			
			System.out.println(result);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
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
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
