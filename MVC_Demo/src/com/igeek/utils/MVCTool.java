package com.igeek.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.igeek.action.IModel;

public class MVCTool {
	private static final Map<String, IModel> map = new HashMap<String, IModel>();

	public static Map<String, IModel> getMap() {
		return map;
	}
	
	public static  IModel getModel(String modelName)
	{
		return map.get(modelName);
	}
}
