package com.igeek.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

import com.igeek.bean.User;

public class ReflectDemo {
	public static void main(String[] args) {
		//ͨ�������ȡ�����
		try {
			Class<User> clazz =(Class<User>)Class.forName("com.igeek.bean.User");
			
			//�����ഴ������
			User user = clazz.newInstance();
			
			
			//��ȡ��Ľṹ��Ϣ
			//1.��ȡ���з�װ������
			Field[] fields = clazz.getDeclaredFields();
			
//			for(Field field : fields)
//			{
//				//System.out.println(field.getName());
//			}
//			
			
			//��ȡ���������ķ���
			Method[] methods = clazz.getDeclaredMethods();
//			for (Method method : methods) {
//				System.out.println(method.getName());
//			}
			
			//��ȡ����ĳһ������
			Method method = clazz.getDeclaredMethod("setUsername", String.class);
			
			//���ø÷���
			method.invoke(user, "li��");
			
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
