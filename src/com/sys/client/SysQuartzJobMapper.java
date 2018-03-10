package com.sys.client;

import com.sys.model.SysQuartzJob;
import com.sys.model.SysQuartzJobExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysQuartzJobMapper {
    int countByExample(SysQuartzJobExample example);

    int deleteByExample(SysQuartzJobExample example);

    int deleteByPrimaryKey(String recid);

    int insert(SysQuartzJob record);

    int insertSelective(SysQuartzJob record);

    List<SysQuartzJob> selectByExample(SysQuartzJobExample example);

    SysQuartzJob selectByPrimaryKey(String recid);

    int updateByExampleSelective(@Param("record") SysQuartzJob record, @Param("example") SysQuartzJobExample example);

    int updateByExample(@Param("record") SysQuartzJob record, @Param("example") SysQuartzJobExample example);

    int updateByPrimaryKeySelective(SysQuartzJob record);

    int updateByPrimaryKey(SysQuartzJob record);
}