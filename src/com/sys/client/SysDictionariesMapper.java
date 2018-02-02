package com.sys.client;

import com.sys.model.SysDictionaries;
import com.sys.model.SysDictionariesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysDictionariesMapper {
    int countByExample(SysDictionariesExample example);

    int deleteByExample(SysDictionariesExample example);

    int deleteByPrimaryKey(String recid);

    int insert(SysDictionaries record);

    int insertSelective(SysDictionaries record);

    List<SysDictionaries> selectByExample(SysDictionariesExample example);

    SysDictionaries selectByPrimaryKey(String recid);

    int updateByExampleSelective(@Param("record") SysDictionaries record, @Param("example") SysDictionariesExample example);

    int updateByExample(@Param("record") SysDictionaries record, @Param("example") SysDictionariesExample example);

    int updateByPrimaryKeySelective(SysDictionaries record);

    int updateByPrimaryKey(SysDictionaries record);
}