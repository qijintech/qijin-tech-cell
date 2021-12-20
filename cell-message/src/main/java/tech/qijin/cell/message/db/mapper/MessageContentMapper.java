package tech.qijin.cell.message.db.mapper;

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
import tech.qijin.cell.message.db.model.MessageContent;
import tech.qijin.cell.message.db.model.MessageContentExample;

public interface MessageContentMapper {
    @SelectProvider(type=MessageContentSqlProvider.class, method="countByExample")
    long countByExample(MessageContentExample example);

    @DeleteProvider(type=MessageContentSqlProvider.class, method="deleteByExample")
    int deleteByExample(MessageContentExample example);

    @Delete({
        "delete from message_content",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into message_content (channel, message_id, ",
        "update_time, create_time, ",
        "content)",
        "values (#{channel,jdbcType=VARCHAR}, #{messageId,jdbcType=BIGINT}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{content,jdbcType=LONGVARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(MessageContent record);

    @InsertProvider(type=MessageContentSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(MessageContent record);

    @SelectProvider(type=MessageContentSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="message_id", property="messageId", jdbcType=JdbcType.BIGINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<MessageContent> selectByExampleWithBLOBs(MessageContentExample example);

    @SelectProvider(type=MessageContentSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="message_id", property="messageId", jdbcType=JdbcType.BIGINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<MessageContent> selectByExample(MessageContentExample example);

    @Select({
        "select",
        "id, channel, message_id, update_time, create_time, content",
        "from message_content",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="message_id", property="messageId", jdbcType=JdbcType.BIGINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    MessageContent selectByPrimaryKey(Long id);

    @UpdateProvider(type=MessageContentSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") MessageContent record, @Param("example") MessageContentExample example);

    @UpdateProvider(type=MessageContentSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") MessageContent record, @Param("example") MessageContentExample example);

    @UpdateProvider(type=MessageContentSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") MessageContent record, @Param("example") MessageContentExample example);

    @UpdateProvider(type=MessageContentSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(MessageContent record);

    @Update({
        "update message_content",
        "set channel = #{channel,jdbcType=VARCHAR},",
          "message_id = #{messageId,jdbcType=BIGINT},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "content = #{content,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKeyWithBLOBs(MessageContent record);

    @Update({
        "update message_content",
        "set channel = #{channel,jdbcType=VARCHAR},",
          "message_id = #{messageId,jdbcType=BIGINT},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(MessageContent record);
}