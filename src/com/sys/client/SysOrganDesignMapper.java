package com.sys.client;

import com.sys.model.SysOrganDesign;
import com.sys.model.SysOrganDesignExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysOrganDesignMapper {
    int countByExample(SysOrganDesignExample example);

    int deleteByExample(SysOrganDesignExample example);

    int deleteByPrimaryKey(String recid);

    int insert(SysOrganDesign record);

    int insertSelective(SysOrganDesign record);

    List<SysOrganDesign> selectByExample(SysOrganDesignExample example);

    SysOrganDesign selectByPrimaryKey(String recid);

    int updateByExampleSelective(@Param("record") SysOrganDesign record, @Param("example") SysOrganDesignExample example);

    int updateByExample(@Param("record") SysOrganDesign record, @Param("example") SysOrganDesignExample example);

    int updateByPrimaryKeySelective(SysOrganDesign record);

    int updateByPrimaryKey(SysOrganDesign record);
}