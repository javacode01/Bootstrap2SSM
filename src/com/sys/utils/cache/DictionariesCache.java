package com.sys.utils.cache;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.sys.client.SysDictionariesItemMapper;
import com.sys.model.SysDictionariesItem;
import com.sys.model.SysDictionariesItemExample;
import com.sys.utils.BspUtils;

/**
 * 数据字典项缓存
 * @author Administrator
 *
 */
public class DictionariesCache {
	
	private static final Logger logger = Logger.getLogger(DictionariesCache.class);
	//数据字典项
	private static List<SysDictionariesItem> dicItemList;
	
	static{
		try{
			System.out.println("数据字典加载开始==================================");
			logger.info("数据字典加载开始==================================");
			SysDictionariesItemMapper dicItemMapper = BspUtils.getBean(SysDictionariesItemMapper.class);
			SysDictionariesItemExample example = new SysDictionariesItemExample();
			example.createCriteria();
			example.setOrderByClause("seq asc");
			setDicItemList(dicItemMapper.selectByExample(example));
			logger.info("数据字典加载完成==================================");
			System.out.println("数据字典加载完成==================================");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static List<SysDictionariesItem> getDicItemList() {
		return dicItemList;
	}

	public static void setDicItemList(List<SysDictionariesItem> dicItemList) {
		DictionariesCache.dicItemList = dicItemList;
	}
	
	/**
	 * 根据数据字典类型获取数据字典项
	 * @param dicType
	 * @return
	 */
	public static List<SysDictionariesItem> getDicItemByDicType(String dicType){
		List<SysDictionariesItem> list = new ArrayList<SysDictionariesItem>();
		for(SysDictionariesItem item : dicItemList) {
			if(dicType.equals(item.getDicType())) {
				list.add(item);
			}
		}
		return list;
	}
	
}
