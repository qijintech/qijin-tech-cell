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
        "insert into im_conversation (owner_id, peer_id, ",
        "last_msgid, last_del_msgid, ",
        "create_time, update_time, ",
        "version_id, del_status, ",
        "sort_id, last_msg)",
        "values (#{ownerId,jdbcType=BIGINT}, #{peerId,jdbcType=BIGINT}, ",
        "#{lastMsgid,jdbcType=BIGINT}, #{lastDelMsgid,jdbcType=BIGINT}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{versionId,jdbcType=BIGINT}, #{delStatus,jdbcType=TINYINT}, ",
        "#{sortId,jdbcType=BIGINT}, #{lastMsg,jdbcType=VARBINARY})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(ImConversation record);

    @InsertProvider(type=ImConversationSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(ImConversation record);

    @SelectProvider(type=ImConversationSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="owner_id", property="ownerId", jdbcType=JdbcType.BIGINT),
        @Result(column="peer_id", property="peerId", jdbcType=JdbcType.BIGINT),
        @Result(column="last_msgid", property="lastMsgid", jdbcType=JdbcType.BIGINT),
        @Result(column="last_del_msgid", property="lastDelMsgid", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="version_id", property="versionId", jdbcType=JdbcType.BIGINT),
        @Result(column="del_status", property="delStatus", jdbcType=JdbcType.TINYINT),
        @Result(column="sort_id", property="sortId", jdbcType=JdbcType.BIGINT),
        @Result(column="last_msg", property="lastMsg", jdbcType=JdbcType.VARBINARY)
    })
    List<ImConversation> selectByExampleWithBLOBs(ImConversationExample example);

    @SelectProvider(type=ImConversationSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="owner_id", property="ownerId", jdbcType=JdbcType.BIGINT),
        @Result(column="peer_id", property="peerId", jdbcType=JdbcType.BIGINT),
        @Result(column="last_msgid", property="lastMsgid", jdbcType=JdbcType.BIGINT),
        @Result(column="last_del_msgid", property="lastDelMsgid", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="version_id", property="versionId", jdbcType=JdbcType.BIGINT),
        @Result(column="del_status", property="delStatus", jdbcType=JdbcType.TINYINT),
        @Result(column="sort_id", property="sortId", jdbcType=JdbcType.BIGINT)
    })
    List<ImConversation> selectByExample(ImConversationExample example);

    @Select({
        "select",
        "id, owner_id, peer_id, last_msgid, last_del_msgid, create_time, update_time, ",
        "version_id, del_status, sort_id, last_msg",
        "from im_conversation",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="owner_id", property="ownerId", jdbcType=JdbcType.BIGINT),
        @Result(column="peer_id", property="peerId", jdbcType=JdbcType.BIGINT),
        @Result(column="last_msgid", property="lastMsgid", jdbcType=JdbcType.BIGINT),
        @Result(column="last_del_msgid", property="lastDelMsgid", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="version_id", property="versionId", jdbcType=JdbcType.BIGINT),
        @Result(column="del_status", property="delStatus", jdbcType=JdbcType.TINYINT),
        @Result(column="sort_id", property="sortId", jdbcType=JdbcType.BIGINT),
        @Result(column="last_msg", property="lastMsg", jdbcType=JdbcType.VARBINARY)
    })
    ImConversation selectByPrimaryKey(Long id);

    @UpdateProvider(type=ImConversationSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ImConversation record, @Param("example") ImConversationExample example);

    @UpdateProvider(type=ImConversationSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") ImConversation record, @Param("example") ImConversationExample example);

    @UpdateProvider(type=ImConversationSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ImConversation record, @Param("example") ImConversationExample example);

    @UpdateProvider(type=ImConversationSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ImConversation record);

    @Update({
        "update im_conversation",
        "set owner_id = #{ownerId,jdbcType=BIGINT},",
          "peer_id = #{peerId,jdbcType=BIGINT},",
          "last_msgid = #{lastMsgid,jdbcType=BIGINT},",
          "last_del_msgid = #{lastDelMsgid,jdbcType=BIGINT},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "version_id = #{versionId,jdbcType=BIGINT},",
          "del_status = #{delStatus,jdbcType=TINYINT},",
          "sort_id = #{sortId,jdbcType=BIGINT},",
          "last_msg = #{lastMsg,jdbcType=VARBINARY}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKeyWithBLOBs(ImConversation record);

    @Update({
        "update im_conversation",
        "set owner_id = #{ownerId,jdbcType=BIGINT},",
          "peer_id = #{peerId,jdbcType=BIGINT},",
          "last_msgid = #{lastMsgid,jdbcType=BIGINT},",
          "last_del_msgid = #{lastDelMsgid,jdbcType=BIGINT},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "version_id = #{versionId,jdbcType=BIGINT},",
          "del_status = #{delStatus,jdbcType=TINYINT},",
          "sort_id = #{sortId,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(ImConversation record);
}