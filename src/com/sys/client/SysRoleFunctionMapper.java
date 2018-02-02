package com.sys.client;

import com.sys.model.SysRoleFunction;
import com.sys.model.SysRoleFunctionExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SysRoleFunctionMapper {
    int countByExample(SysRoleFunctionExample example);

    int deleteByExample(SysRoleFunctionExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysRoleFunction record);

    int insertSelective(SysRoleFunction record);

    List<SysRoleFunction> selectByExample(SysRoleFunctionExample example);

    SysRoleFunction selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysRoleFunction record, @Param("example") SysRoleFunctionExample example);

    int updateByExample(@Param("record") SysRoleFunction record, @Param("example") SysRoleFunctionExample example);

    int updateByPrimaryKeySelective(SysRoleFunction record);

    int updateByPrimaryKey(SysRoleFunction record);

	List<Map<String, String>> selectAllResource();
}