package com.igeek.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.igeek.action.IModel;
import com.igeek.utils.MVCTool;

public class CoreController extends HttpServlet {

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String[] splits = uri.split("/");
		String method = splits[splits.length-1].substring(0,splits[splits.length-1].indexOf("."));
		String modelName = splits[splits.length-2];
		
		IModel model = MVCTool.getModel(modelName);
		String url="error.jsp";
		//可能找不到
		if(model!=null){
			//如果模型存在,调用模型中相应的方法
			if(method!=null)
			{
				//调用该方法
				try {
					Method methodObj = model.getClass().getDeclaredMethod(method, HttpServletRequest.class,HttpServletResponse.class);
					
					Object result = methodObj.invoke(model, request,response);
					if(result!=null)
					{
						url = result.toString();
					}
					
					//跳转
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
				}
			}else{
				request.setAttribute("error", "请求方法不存在");
			}
			
			
		}else{
			request.setAttribute("error", "请求模型不存在");
		}
		
		request.getRequestDispatcher("/"+url).forward(request, response);
		
	}

	
	public void init() throws ServletException {
		// Put your code here
		//解析XML文件
		parseConfig();
	}


	private void parseConfig() {
		// TODO Auto-generated method stub
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(this.getClass().getResource("/model.xml").getPath());
			
			Element mvc = document.getRootElement();
			List<Element> models = mvc.elements("model");
			for(Element model : models){
				String modelName = model.elementText("model-name");
				String modelClass = model.elementText("model-class");
				Class<IModel> clazz =(Class<IModel>) Class.forName(modelClass);
				//根据modelClass字符串创建类对象
				IModel modelObject = clazz.newInstance();
				
				//存入map中
				MVCTool.getMap().put(modelName, modelObject);
			}
			
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
