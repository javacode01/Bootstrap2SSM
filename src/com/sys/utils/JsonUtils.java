package com.sys.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtils {

	private final static MyCustomMapper objectMapper = new MyCustomMapper();
	
	/**
	 * javaBean,list,array convert to json string
	 */
	public static String obj2json(Object obj)  {
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * json string convert to javaBean
	 */
	public static <T> T json2pojo(String jsonStr, Class<T> clazz){
		try {
			return objectMapper.readValue(jsonStr, clazz);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * json string convert to map
	 */
	public static <T> Map<String, Object> json2map(String jsonStr){
		try {
			return objectMapper.readValue(jsonStr, Map.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * json string convert to map with javaBean
	 */
	public static <T> Map<String, T> json2map(String jsonStr, Class<T> clazz){
		Map<String, Map<String, Object>> map;
		try {
			map = objectMapper.readValue(jsonStr,
					new TypeReference<Map<String, T>>() {
					});
			Map<String, T> result = new HashMap<String, T>();
			for (Entry<String, Map<String, Object>> entry : map.entrySet()) {
				result.put(entry.getKey(), map2pojo(entry.getValue(), clazz));
			}
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	/**
	 * json array string convert to list with javaBean
	 */
	public static <T> List<T> json2list(String jsonArrayStr, Class<T> clazz)
			{
		List<Map<String, Object>> list;
		try {
			list = objectMapper.readValue(jsonArrayStr,
					new TypeReference<List<T>>() {
					});
			List<T> result = new ArrayList<T>();
			for (Map<String, Object> map : list) {
				result.add(map2pojo(map, clazz));
			}
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	/**
	 * map convert to javaBean
	 */
	public static <T> T map2pojo(Map map, Class<T> clazz) {
		return objectMapper.convertValue(map, clazz);
	}
	public static void main(String args[]){
		System.out.println(JsonUtils.obj2json(new java.sql.Timestamp((new Date()).getTime()).getTime()));
	}
	public static Map pojo2map(Object o){
		return json2map(obj2json(o));
	}
}
class MyCustomMapper extends ObjectMapper{ 
    public MyCustomMapper() {
        this.setSerializationInclusion(Include.NON_NULL);
        // 设置 SerializationFeature.FAIL_ON_EMPTY_BEANS 为 false
        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //设置日期转换yyyy-MM-dd HH:mm:ss  
        setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")); 
        TimeZone tz = TimeZone.getTimeZone("GMT+8");
        this.setTimeZone(tz); 
    }
}