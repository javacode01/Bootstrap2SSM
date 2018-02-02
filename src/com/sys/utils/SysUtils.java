package com.sys.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.security.MessageDigest;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统工具类
 * @author Administrator
 *
 */
public class SysUtils {
	
	/**
	 * 校验字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str) {
		if (null == str || "".equals(str)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 获取随机字符串
	 * @return
	 */
	public static String getUUID() {
		String s = UUID.randomUUID().toString().replace("-", "");
		return s;
	}
	
	/**
	 * 获取指定长度随机字符串
	 * @param length
	 * @return
	 */
	public static String getUUID(int length) {
		String uuid="";
		for(int i=0;i<length;i++){
			uuid=uuid+UUID.randomUUID().toString().substring(0,1);
		}
		return uuid;
	}
	
	public final static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public final static String MD5LOWER(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String clob2String(Clob clob) {
		String resString = "";
		if (clob == null) {
			return resString;
		}
		Reader is = null;
		try {
			is = clob.getCharacterStream();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(is);
		String s = null;
		try {
			s = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		StringBuffer sb = new StringBuffer();
		while (s != null) {
			sb.append(s);
			try {
				s = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		resString = sb.toString();
		return resString;
	}
    
    /**
	 * 
	* @Title: isAjaxRequest 
	* @Description: TODO(判断是否是ajax) 
	* @param @param request
	* @param @param response
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {
		String requestType = request.getHeader("X-Requested-With");
		if(SysUtils.isNull(requestType)){
			return false;
		}else{
			return true;
		}
	}
	
}
