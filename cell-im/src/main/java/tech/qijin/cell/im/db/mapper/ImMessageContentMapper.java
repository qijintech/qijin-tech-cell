package tech.qijin.cell.im.db.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import tech.qijin.cell.im.db.model.ImMessageContent;
import tech.qijin.cell.im.db.model.ImMessageContentExample;

public interface ImMessageContentMapper {
    @SelectProvider(type=ImMessageContentSqlProvider.class, method="countByExample")
    long countByExample(ImMessageContentExample example);

    @DeleteProvider(type=ImMessageContentSqlProvider.class, method="deleteByExample")
    int deleteByExample(ImMessageContentExample example);

    @Delete({
        "delete from im_message_content",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into im_message_content (msg_id, status, ",
        "type, without_sender, ",
        "without_recipient, create_time, ",
        "content)",
        "values (#{msgId,jdbcType=BIGINT}, #{status,jdbcType=TINYINT}, ",
        "#{type,jdbcType=TINYINT}, #{withoutSender,jdbcType=TINYINT}, ",
        "#{withoutRecipient,jdbcType=TINYINT}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{content,jdbcType=VARBINARY})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(ImMessageContent record);

    @InsertProvider(type=ImMessageContentSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(ImMessageContent record);

    @SelectProvider(type=ImMessageContentSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="msg_id", property="msgId", jdbcType=JdbcType.BIGINT),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
        @Result(column="type", property="type", jdbcType=JdbcType.TINYINT),
        @Result(column="without_sender", property="withoutSender", jdbcType=JdbcType.TINYINT),
        @Result(column="without_recipient", property="withoutRecipient", jdbcType=JdbcType.TINYINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="content", property="content", jdbcType=JdbcType.VARBINARY)
    })
    List<ImMessageContent> selectByExampleWithBLOBs(ImMessageContentExample example);

    @SelectProvider(type=ImMessageContentSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="msg_id", property="msgId", jdbcType=JdbcType.BIGINT),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
        @Result(column="type", property="type", jdbcType=JdbcType.TINYINT),
        @Result(column="without_sender", property="withoutSender", jdbcType=JdbcType.TINYINT),
        @Result(column="without_recipient", property="withoutRecipient", jdbcType=JdbcType.TINYINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ImMessageContent> selectByExample(ImMessageContentExample example);

    @Select({
        "select",
        "id, msg_id, status, type, without_sender, without_recipient, create_time, content",
        "from im_message_content",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="msg_id", property="msgId", jdbcType=JdbcType.BIGINT),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
        @Result(column="type", property="type", jdbcType=JdbcType.TINYINT),
        @Result(column="without_sender", property="withoutSender", jdbcType=JdbcType.TINYINT),
        @Result(column="without_recipient", property="withoutRecipient", jdbcType=JdbcType.TINYINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="content", property="content", jdbcType=JdbcType.VARBINARY)
    })
    ImMessageContent selectByPrimaryKey(Long id);

    @UpdateProvider(type=ImMessageContentSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ImMessageContent record, @Param("example") ImMessageContentExample example);

    @UpdateProvider(type=ImMessageContentSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") ImMessageContent record, @Param("example") ImMessageContentExample example);

    @UpdateProvider(type=ImMessageContentSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ImMessageContent record, @Param("example") ImMessageContentExample example);

    @UpdateProvider(type=ImMessageContentSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ImMessageContent record);

    @Update({
        "update im_message_content",
        "set msg_id = #{msgId,jdbcType=BIGINT},",
          "status = #{status,jdbcType=TINYINT},",
          "type = #{type,jdbcType=TINYINT},",
          "without_sender = #{withoutSender,jdbcType=TINYINT},",
          "without_recipient = #{withoutRecipient,jdbcType=TINYINT},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "content = #{content,jdbcType=VARBINARY}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKeyWithBLOBs(ImMessageContent record);

    @Update({
        "update im_message_content",
        "set msg_id = #{msgId,jdbcType=BIGINT},",
          "status = #{status,jdbcType=TINYINT},",
          "type = #{type,jdbcType=TINYINT},",
          "without_sender = #{withoutSender,jdbcType=TINYINT},",
          "without_recipient = #{withoutRecipient,jdbcType=TINYINT},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(ImMessageContent record);
}