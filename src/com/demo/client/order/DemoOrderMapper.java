package com.demo.client.order;

import com.demo.model.order.DemoOrder;
import com.demo.model.order.DemoOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DemoOrderMapper {
    int countByExample(DemoOrderExample example);

    int deleteByExample(DemoOrderExample example);

    int deleteByPrimaryKey(String recid);

    int insert(DemoOrder record);

    int insertSelective(DemoOrder record);

    List<DemoOrder> selectByExample(DemoOrderExample example);

    DemoOrder selectByPrimaryKey(String recid);

    int updateByExampleSelective(@Param("record") DemoOrder record, @Param("example") DemoOrderExample example);

    int updateByExample(@Param("record") DemoOrder record, @Param("example") DemoOrderExample example);

    int updateByPrimaryKeySelective(DemoOrder record);

    int updateByPrimaryKey(DemoOrder record);
}