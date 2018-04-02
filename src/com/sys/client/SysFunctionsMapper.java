package com.sys.client;

import com.sys.model.SysFunctions;
import com.sys.model.SysFunctionsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysFunctionsMapper {
    int countByExample(SysFunctionsExample example);

    int deleteByExample(SysFunctionsExample example);

    int deleteByPrimaryKey(String functionId);

    int insert(SysFunctions record);

    int insertSelective(SysFunctions record);

    List<SysFunctions> selectByExample(SysFunctionsExample example);

    SysFunctions selectByPrimaryKey(String functionId);

    int updateByExampleSelective(@Param("record") SysFunctions record, @Param("example") SysFunctionsExample example);

    int updateByExample(@Param("record") SysFunctions record, @Param("example") SysFunctionsExample example);

    int updateByPrimaryKeySelective(SysFunctions record);

    int updateByPrimaryKey(SysFunctions record);
    
    /**
     * 根据用户编号获取用户分配的模块、功能和操作
     * @param userCode
     * @return
     */
	List<SysFunctions> getFunctionsByUserCode(String userCode);
}