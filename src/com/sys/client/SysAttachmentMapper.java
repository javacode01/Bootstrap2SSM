package com.sys.client;

import com.sys.model.SysAttachment;
import com.sys.model.SysAttachmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysAttachmentMapper {
    int countByExample(SysAttachmentExample example);

    int deleteByExample(SysAttachmentExample example);

    int deleteByPrimaryKey(String recid);

    int insert(SysAttachment record);

    int insertSelective(SysAttachment record);

    List<SysAttachment> selectByExample(SysAttachmentExample example);

    SysAttachment selectByPrimaryKey(String recid);

    int updateByExampleSelective(@Param("record") SysAttachment record, @Param("example") SysAttachmentExample example);

    int updateByExample(@Param("record") SysAttachment record, @Param("example") SysAttachmentExample example);

    int updateByPrimaryKeySelective(SysAttachment record);

    int updateByPrimaryKey(SysAttachment record);
}