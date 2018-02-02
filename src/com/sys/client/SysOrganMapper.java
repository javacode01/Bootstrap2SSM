package com.sys.client;

import com.sys.model.SysOrgan;
import com.sys.model.SysOrganExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysOrganMapper {
    int countByExample(SysOrganExample example);

    int deleteByExample(SysOrganExample example);

    int deleteByPrimaryKey(String organId);

    int insert(SysOrgan record);

    int insertSelective(SysOrgan record);

    List<SysOrgan> selectByExample(SysOrganExample example);

    SysOrgan selectByPrimaryKey(String organId);

    int updateByExampleSelective(@Param("record") SysOrgan record, @Param("example") SysOrganExample example);

    int updateByExample(@Param("record") SysOrgan record, @Param("example") SysOrganExample example);

    int updateByPrimaryKeySelective(SysOrgan record);

    int updateByPrimaryKey(SysOrgan record);
}