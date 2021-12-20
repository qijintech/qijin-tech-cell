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
import tech.qijin.cell.message.db.model.MessageDrops;
import tech.qijin.cell.message.db.model.MessageDropsExample;

public interface MessageDropsMapper {
    @SelectProvider(type=MessageDropsSqlProvider.class, method="countByExample")
    long countByExample(MessageDropsExample example);

    @DeleteProvider(type=MessageDropsSqlProvider.class, method="deleteByExample")
    int deleteByExample(MessageDropsExample example);

    @Delete({
        "delete from message_drops",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into message_drops (channel, message_id, ",
        "drops_id, statement_src, ",
        "data_id, `status`, update_time, ",
        "create_time)",
        "values (#{channel,jdbcType=VARCHAR}, #{messageId,jdbcType=BIGINT}, ",
        "#{dropsId,jdbcType=BIGINT}, #{statementSrc,jdbcType=VARCHAR}, ",
        "#{dataId,jdbcType=BIGINT}, #{status,jdbcType=VARCHAR}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{createTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(MessageDrops record);

    @InsertProvider(type=MessageDropsSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(MessageDrops record);

    @SelectProvider(type=MessageDropsSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="message_id", property="messageId", jdbcType=JdbcType.BIGINT),
        @Result(column="drops_id", property="dropsId", jdbcType=JdbcType.BIGINT),
        @Result(column="statement_src", property="statementSrc", jdbcType=JdbcType.VARCHAR),
        @Result(column="data_id", property="dataId", jdbcType=JdbcType.BIGINT),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<MessageDrops> selectByExample(MessageDropsExample example);

    @Select({
        "select",
        "id, channel, message_id, drops_id, statement_src, data_id, `status`, update_time, ",
        "create_time",
        "from message_drops",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="message_id", property="messageId", jdbcType=JdbcType.BIGINT),
        @Result(column="drops_id", property="dropsId", jdbcType=JdbcType.BIGINT),
        @Result(column="statement_src", property="statementSrc", jdbcType=JdbcType.VARCHAR),
        @Result(column="data_id", property="dataId", jdbcType=JdbcType.BIGINT),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    MessageDrops selectByPrimaryKey(Long id);

    @UpdateProvider(type=MessageDropsSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") MessageDrops record, @Param("example") MessageDropsExample example);

    @UpdateProvider(type=MessageDropsSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") MessageDrops record, @Param("example") MessageDropsExample example);

    @UpdateProvider(type=MessageDropsSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(MessageDrops record);

    @Update({
        "update message_drops",
        "set channel = #{channel,jdbcType=VARCHAR},",
          "message_id = #{messageId,jdbcType=BIGINT},",
          "drops_id = #{dropsId,jdbcType=BIGINT},",
          "statement_src = #{statementSrc,jdbcType=VARCHAR},",
          "data_id = #{dataId,jdbcType=BIGINT},",
          "`status` = #{status,jdbcType=VARCHAR},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(MessageDrops record);
}