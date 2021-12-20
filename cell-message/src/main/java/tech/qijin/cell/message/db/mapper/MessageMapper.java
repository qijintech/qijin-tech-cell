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
import tech.qijin.cell.message.db.model.Message;
import tech.qijin.cell.message.db.model.MessageExample;

public interface MessageMapper {
    @SelectProvider(type=MessageSqlProvider.class, method="countByExample")
    long countByExample(MessageExample example);

    @DeleteProvider(type=MessageSqlProvider.class, method="deleteByExample")
    int deleteByExample(MessageExample example);

    @Delete({
        "delete from message",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into message (channel, user_id, ",
        "kind, brief, `status`, ",
        "`read`, has_drops, ",
        "version, update_time, ",
        "create_time)",
        "values (#{channel,jdbcType=VARCHAR}, #{userId,jdbcType=BIGINT}, ",
        "#{kind,jdbcType=VARCHAR}, #{brief,jdbcType=VARCHAR}, #{status,jdbcType=VARCHAR}, ",
        "#{read,jdbcType=TINYINT}, #{hasDrops,jdbcType=TINYINT}, ",
        "#{version,jdbcType=INTEGER}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{createTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(Message record);

    @InsertProvider(type=MessageSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(Message record);

    @SelectProvider(type=MessageSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="kind", property="kind", jdbcType=JdbcType.VARCHAR),
        @Result(column="brief", property="brief", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="read", property="read", jdbcType=JdbcType.TINYINT),
        @Result(column="has_drops", property="hasDrops", jdbcType=JdbcType.TINYINT),
        @Result(column="version", property="version", jdbcType=JdbcType.INTEGER),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Message> selectByExample(MessageExample example);

    @Select({
        "select",
        "id, channel, user_id, kind, brief, `status`, `read`, has_drops, version, update_time, ",
        "create_time",
        "from message",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="kind", property="kind", jdbcType=JdbcType.VARCHAR),
        @Result(column="brief", property="brief", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="read", property="read", jdbcType=JdbcType.TINYINT),
        @Result(column="has_drops", property="hasDrops", jdbcType=JdbcType.TINYINT),
        @Result(column="version", property="version", jdbcType=JdbcType.INTEGER),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    Message selectByPrimaryKey(Long id);

    @UpdateProvider(type=MessageSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Message record, @Param("example") MessageExample example);

    @UpdateProvider(type=MessageSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Message record, @Param("example") MessageExample example);

    @UpdateProvider(type=MessageSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Message record);

    @Update({
        "update message",
        "set channel = #{channel,jdbcType=VARCHAR},",
          "user_id = #{userId,jdbcType=BIGINT},",
          "kind = #{kind,jdbcType=VARCHAR},",
          "brief = #{brief,jdbcType=VARCHAR},",
          "`status` = #{status,jdbcType=VARCHAR},",
          "`read` = #{read,jdbcType=TINYINT},",
          "has_drops = #{hasDrops,jdbcType=TINYINT},",
          "version = #{version,jdbcType=INTEGER},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Message record);
}