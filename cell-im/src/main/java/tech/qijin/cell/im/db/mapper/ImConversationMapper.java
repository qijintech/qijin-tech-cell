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
import tech.qijin.cell.im.db.model.ImConversation;
import tech.qijin.cell.im.db.model.ImConversationExample;

public interface ImConversationMapper {
    @SelectProvider(type=ImConversationSqlProvider.class, method="countByExample")
    long countByExample(ImConversationExample example);

    @DeleteProvider(type=ImConversationSqlProvider.class, method="deleteByExample")
    int deleteByExample(ImConversationExample example);

    @Delete({
        "delete from im_conversation",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into im_conversation (channel, uid, ",
        "peer_uid, version_id, ",
        "last_msg_id, last_msg, ",
        "last_clear_msg, status, ",
        "update_time, create_time)",
        "values (#{channel,jdbcType=VARCHAR}, #{uid,jdbcType=BIGINT}, ",
        "#{peerUid,jdbcType=BIGINT}, #{versionId,jdbcType=BIGINT}, ",
        "#{lastMsgId,jdbcType=BIGINT}, #{lastMsg,jdbcType=VARCHAR}, ",
        "#{lastClearMsg,jdbcType=BIGINT}, #{status,jdbcType=TINYINT}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{createTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(ImConversation record);

    @InsertProvider(type=ImConversationSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(ImConversation record);

    @SelectProvider(type=ImConversationSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="uid", property="uid", jdbcType=JdbcType.BIGINT),
        @Result(column="peer_uid", property="peerUid", jdbcType=JdbcType.BIGINT),
        @Result(column="version_id", property="versionId", jdbcType=JdbcType.BIGINT),
        @Result(column="last_msg_id", property="lastMsgId", jdbcType=JdbcType.BIGINT),
        @Result(column="last_msg", property="lastMsg", jdbcType=JdbcType.VARCHAR),
        @Result(column="last_clear_msg", property="lastClearMsg", jdbcType=JdbcType.BIGINT),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ImConversation> selectByExample(ImConversationExample example);

    @Select({
        "select",
        "id, channel, uid, peer_uid, version_id, last_msg_id, last_msg, last_clear_msg, ",
        "status, update_time, create_time",
        "from im_conversation",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="uid", property="uid", jdbcType=JdbcType.BIGINT),
        @Result(column="peer_uid", property="peerUid", jdbcType=JdbcType.BIGINT),
        @Result(column="version_id", property="versionId", jdbcType=JdbcType.BIGINT),
        @Result(column="last_msg_id", property="lastMsgId", jdbcType=JdbcType.BIGINT),
        @Result(column="last_msg", property="lastMsg", jdbcType=JdbcType.VARCHAR),
        @Result(column="last_clear_msg", property="lastClearMsg", jdbcType=JdbcType.BIGINT),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    ImConversation selectByPrimaryKey(Long id);

    @UpdateProvider(type=ImConversationSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ImConversation record, @Param("example") ImConversationExample example);

    @UpdateProvider(type=ImConversationSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ImConversation record, @Param("example") ImConversationExample example);

    @UpdateProvider(type=ImConversationSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ImConversation record);

    @Update({
        "update im_conversation",
        "set channel = #{channel,jdbcType=VARCHAR},",
          "uid = #{uid,jdbcType=BIGINT},",
          "peer_uid = #{peerUid,jdbcType=BIGINT},",
          "version_id = #{versionId,jdbcType=BIGINT},",
          "last_msg_id = #{lastMsgId,jdbcType=BIGINT},",
          "last_msg = #{lastMsg,jdbcType=VARCHAR},",
          "last_clear_msg = #{lastClearMsg,jdbcType=BIGINT},",
          "status = #{status,jdbcType=TINYINT},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(ImConversation record);
}