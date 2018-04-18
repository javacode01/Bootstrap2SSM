package ${basePackage}.service${baseModule};

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ${basePackage}.client${baseModule}.${className}Mapper;
import ${basePackage}.model${baseModule}.${className};
import ${basePackage}.model${baseModule}.${className}Example;
import com.sys.utils.PageListData;
import com.sys.utils.SysConstant;

@Service
public class ${className}Service {
	
	@Autowired
	private ${className}Mapper ${className?lower_case}Mapper;
	
	/**
	 * 分页查询
	 * @param pote
	 * @return
	 */
	public PageListData list${className}ByPage(${className}Example example) {
		int totalCount = ${className?lower_case}Mapper.countByExample(example);
		List<${className}> list = ${className?lower_case}Mapper.selectByExample(example);			
		PageListData pld = new PageListData();
		pld.setTotal(totalCount);
		pld.setRows(list);
		return pld;
	}
	
	/**
	 * 根据主键获取对象
	 * @param recid
	 * @return
	 */
	public ${className} get${className}ById(String id) {
		return ${className?lower_case}Mapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 新增对象
	 * @param edit
	 */
	public void add${className}(${className} add) {
		int count = ${className?lower_case}Mapper.insertSelective(add);
		if(1!=count) {
			throw new RuntimeException(SysConstant.SYS_ERROR_ADD);
		}
	}
	
	/**
	 * 更新对象信息
	 * @param edit
	 */
	@Transactional
	public void update${className}(${className} edit) {
		int count = ${className?lower_case}Mapper.updateByPrimaryKeySelective(edit);
		if(1!=count) {
			throw new RuntimeException(SysConstant.SYS_ERROR_UPDATE);
		}
	}
	
	/**
	 * 批量删除对象
	 * @param orderids
	 */
	@Transactional
	public void delete${className}(String recids) {
		String[] array = recids.split(",");
		for(String recid:array) {
			${className?lower_case}Mapper.deleteByPrimaryKey(recid);
		}
		
	}

}
