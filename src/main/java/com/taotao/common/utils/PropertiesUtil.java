package com.taotao.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * 配置文件工具
 * @author hys
 *
 */
public class PropertiesUtil {

	/**
	 * 获取配置文件中的配置信息
	 * @param filePath 配置文件在工程中的路径
	 * @return 返回一个Map数据
	 * @throws IOException
	 */
	public static Map<String, String> getPropertiesFromFile(String filePath) throws IOException{
		Map<String, String> propMap = new HashMap<String, String>();
		Properties pro = new Properties();
		InputStream  input = Object.class.getResourceAsStream(filePath);
		if(input != null && !("".equals(input)))
		{
			pro.load(input);// 加载属性列表
			Iterator<String> it = pro.stringPropertyNames().iterator();
			while(it.hasNext()){
				String key=it.next();
				propMap.put(key, pro.getProperty(key));
			}
			input.close();
			return propMap;
		}
//		input.close();
		return null;
		
	}
}
