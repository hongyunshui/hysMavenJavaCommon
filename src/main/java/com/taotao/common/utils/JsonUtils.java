package com.taotao.common.utils;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	
	// 定义json对象
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	/**
	 * java对象数据格式转换为json字符串
	 * @param data
	 * @return
	 */
	public static String objectToJson(Object data){
		String string;
		try {
			string = MAPPER.writeValueAsString(data);
			return string;
		} catch (JsonProcessingException e) {
			System.out.println("java对象数据格式转换为json字符串异常");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * json转换为pojo 
	 * @param jsonData
	 * @param beanType
	 * @return
	 */
	public static<T> T jsonToPojo(String jsonData, Class<T> beanType){
		
		T t;
		try {
			t = MAPPER.readValue(jsonData, beanType);
			return t;
		} catch (JsonParseException e) {
			System.out.println("json转换为pojo JsonPare异常");
			e.printStackTrace();
		} catch (JsonMappingException e) {
			System.out.println("json转换为 pojo JsonMapping异常");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("json转换为 pojo IOException异常");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * json数据格式转换为list数据格式
	 * @param jsonData
	 * @param beanType
	 * @return
	 */
	public static <T>List<T> jsonToList(String jsonData, Class<T> beanType){
		JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
		try {
			List<T> list = MAPPER.readValue(jsonData, javaType);
			return list;
		} catch (JsonParseException e) {
			System.out.println("json数据格式转换为list数据格式JsonParseException异常");
			e.printStackTrace();
		} catch (JsonMappingException e) {
			System.out.println("json数据格式转换为list数据格式JsonMappingException异常");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("json数据格式转换为list数据格式IOException异常");
			e.printStackTrace();
		}
		return null;
	}

}
