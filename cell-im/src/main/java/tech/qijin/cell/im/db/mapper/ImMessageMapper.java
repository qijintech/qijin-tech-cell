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
import tech.qijin.cell.im.db.model.ImMessage;
import tech.qijin.cell.im.db.model.ImMessageExample;

public interface ImMessageMapper {
    @SelectProvider(type=ImMessageSqlProvider.class, method="countByExample")
    long countByExample(ImMessageExample example);

    @DeleteProvider(type=ImMessageSqlProvider.class, method="deleteByExample")
    int deleteByExample(ImMessageExample example);

    @Delete({
        "delete from im_message",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into im_message (channel, msg_id, ",
        "msg_type, from_uid, ",
        "union_id, content, ",
        "extra, status, read_status, ",
        "update_time, create_time)",
        "values (#{channel,jdbcType=VARCHAR}, #{msgId,jdbcType=BIGINT}, ",
        "#{msgType,jdbcType=VARCHAR}, #{fromUid,jdbcType=BIGINT}, ",
        "#{unionId,jdbcType=VARCHAR}, #{content,jdbcType=VARCHAR}, ",
        "#{extra,jdbcType=VARCHAR}, #{status,jdbcType=INTEGER}, #{readStatus,jdbcType=TINYINT}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{createTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(ImMessage record);

    @InsertProvider(type=ImMessageSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(ImMessage record);

    @SelectProvider(type=ImMessageSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="msg_id", property="msgId", jdbcType=JdbcType.BIGINT),
        @Result(column="msg_type", property="msgType", jdbcType=JdbcType.VARCHAR),
        @Result(column="from_uid", property="fromUid", jdbcType=JdbcType.BIGINT),
        @Result(column="union_id", property="unionId", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="extra", property="extra", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="read_status", property="readStatus", jdbcType=JdbcType.TINYINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ImMessage> selectByExample(ImMessageExample example);

    @Select({
        "select",
        "id, channel, msg_id, msg_type, from_uid, union_id, content, extra, status, read_status, ",
        "update_time, create_time",
        "from im_message",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="msg_id", property="msgId", jdbcType=JdbcType.BIGINT),
        @Result(column="msg_type", property="msgType", jdbcType=JdbcType.VARCHAR),
        @Result(column="from_uid", property="fromUid", jdbcType=JdbcType.BIGINT),
        @Result(column="union_id", property="unionId", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="extra", property="extra", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="read_status", property="readStatus", jdbcType=JdbcType.TINYINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    ImMessage selectByPrimaryKey(Long id);

    @UpdateProvider(type=ImMessageSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ImMessage record, @Param("example") ImMessageExample example);

    @UpdateProvider(type=ImMessageSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ImMessage record, @Param("example") ImMessageExample example);

    @UpdateProvider(type=ImMessageSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ImMessage record);

    @Update({
        "update im_message",
        "set channel = #{channel,jdbcType=VARCHAR},",
          "msg_id = #{msgId,jdbcType=BIGINT},",
          "msg_type = #{msgType,jdbcType=VARCHAR},",
          "from_uid = #{fromUid,jdbcType=BIGINT},",
          "union_id = #{unionId,jdbcType=VARCHAR},",
          "content = #{content,jdbcType=VARCHAR},",
          "extra = #{extra,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=INTEGER},",
          "read_status = #{readStatus,jdbcType=TINYINT},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(ImMessage record);
}