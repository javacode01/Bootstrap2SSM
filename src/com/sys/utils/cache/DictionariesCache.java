package com.sys.utils.cache;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.sys.client.SysDictionariesItemMapper;
import com.sys.model.SysDictionariesItem;
import com.sys.model.SysDictionariesItemExample;
import com.sys.utils.BspUtils;

/**
 * 数据字典项缓存
 * @author Administrator
 *
 */
public class DictionariesCache implements InitializingBean{
	
	private static final Logger logger = Logger.getLogger(DictionariesCache.class);
	
	@Autowired
	private SysDictionariesItemMapper sysDictionariesItemMapper;
	
	//数据字典项
	private List<SysDictionariesItem> dicItemList;

	public List<SysDictionariesItem> getDicItemList() {
		return dicItemList;
	}

	public void setDicItemList(List<SysDictionariesItem> dicItemList1) {
		dicItemList = dicItemList1;
	}
	
	/**
	 * 根据数据字典类型获取数据字典项
	 * @param dicType
	 * @return
	 */
	public List<SysDictionariesItem> getDicItemByDicType(String dicType){
		List<SysDictionariesItem> list = new ArrayList<SysDictionariesItem>();
		for(SysDictionariesItem item : dicItemList) {
			if(dicType.equals(item.getDicType())) {
				list.add(item);
			}
		}
		return list;
	}
	
	/**
	 * 初始化加载数据
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("数据字典加载开始==================================");
		logger.info("数据字典加载开始==================================");
		SysDictionariesItemExample example = new SysDictionariesItemExample();
		example.createCriteria();
		example.setOrderByClause("seq asc");
		setDicItemList(sysDictionariesItemMapper.selectByExample(example));
		logger.info("数据字典加载完成==================================");
		System.out.println("数据字典加载完成==================================");
	}
	
}
