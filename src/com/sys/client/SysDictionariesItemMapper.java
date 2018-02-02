package com.sys.client;

import com.sys.model.SysDictionariesItem;
import com.sys.model.SysDictionariesItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysDictionariesItemMapper {
    int countByExample(SysDictionariesItemExample example);

    int deleteByExample(SysDictionariesItemExample example);

    int deleteByPrimaryKey(String recid);

    int insert(SysDictionariesItem record);

    int insertSelective(SysDictionariesItem record);

    List<SysDictionariesItem> selectByExample(SysDictionariesItemExample example);

    SysDictionariesItem selectByPrimaryKey(String recid);

    int updateByExampleSelective(@Param("record") SysDictionariesItem record, @Param("example") SysDictionariesItemExample example);

    int updateByExample(@Param("record") SysDictionariesItem record, @Param("example") SysDictionariesItemExample example);

    int updateByPrimaryKeySelective(SysDictionariesItem record);

    int updateByPrimaryKey(SysDictionariesItem record);
}