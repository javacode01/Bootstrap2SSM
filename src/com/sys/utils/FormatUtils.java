package com.sys.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;

/**
 * @ClassName: FormatUtils 
 * @Description: 系统格式转换类
 * @author AK
 * @date 2017-11-15 下午10:41:39 
 *
 */
public class FormatUtils {
	
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

}
