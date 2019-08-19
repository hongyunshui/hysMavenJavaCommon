package com.taotao.common.pojo;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 自定义响应结构
 * @author hys
 *
 */
public class RespResult {
	
	// 定义jackson对象
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	// 响应业务状态
	private Integer status;
	
	// 响应消息
	private String msg;
	
	// 响应中的数据
	private Object data;
	
	public RespResult(){
		
	}
	
	public RespResult(Object data) {
		this.data = data;
		this.msg = "OK";
		this.status = 200;
	}

	public RespResult(Integer status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public static RespResult build(Integer status, String msg, Object data){
		return new RespResult(status, msg, data);
	}
	
	public static RespResult ok(Object data){
		return new RespResult(data);
	}
	
	public static RespResult ok(){
		return new RespResult(null);
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	/**
	 * 将json结果集转化为RespResult对象
	 * @param jsonData
	 * @param clazz
	 * @return
	 */
	public static RespResult formatToPojo(String jsonData, Class<?> clazz){
		try{
			if(clazz == null){
				return MAPPER.readValue(jsonData, RespResult.class);
			}
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			JsonNode data = jsonNode.get("data");
			Object obj = null;
			if(clazz != null){
				if(data.isObject()){
					obj = MAPPER.readValue(data.traverse(), clazz);
				} else if(data.isTextual()){
					obj = MAPPER.readValue(data.asText(), clazz);
				}
			}
			return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
		} catch(Exception e){
			return null;
		}
	}
	
	/**
	 * 没有Object对象的转化
	 * @param json
	 * @return
	 */
	public static RespResult format(String json){
		try {
			return MAPPER.readValue(json, RespResult.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Object 是集合转化
	 * @param jsonData
	 * @param clazz
	 * @return
	 */
	public static RespResult formatToList(String jsonData, Class<?> clazz){
		
		try {
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			JsonNode data = jsonNode.get("data");
			Object obj = null;
			if(data.isArray() && data.size() > 0){
				obj = MAPPER.readValue(data.traverse(), MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
			}
			return build(jsonNode.get("status").intValue(),jsonNode.get("msg").asText(), obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
