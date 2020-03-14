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
import tech.qijin.cell.im.db.model.ImMessageInfo;
import tech.qijin.cell.im.db.model.ImMessageInfoExample;

public interface ImMessageInfoMapper {
    @SelectProvider(type=ImMessageInfoSqlProvider.class, method="countByExample")
    long countByExample(ImMessageInfoExample example);

    @DeleteProvider(type=ImMessageInfoSqlProvider.class, method="deleteByExample")
    int deleteByExample(ImMessageInfoExample example);

    @Delete({
        "delete from im_message_info",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into im_message_info (send_id, msg_id, ",
        "status, seq_id, create_time, ",
        "update_time, version_id, ",
        "union_id)",
        "values (#{sendId,jdbcType=BIGINT}, #{msgId,jdbcType=BIGINT}, ",
        "#{status,jdbcType=TINYINT}, #{seqId,jdbcType=BIGINT}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{versionId,jdbcType=BIGINT}, ",
        "#{unionId,jdbcType=VARBINARY})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(ImMessageInfo record);

    @InsertProvider(type=ImMessageInfoSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(ImMessageInfo record);

    @SelectProvider(type=ImMessageInfoSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="send_id", property="sendId", jdbcType=JdbcType.BIGINT),
        @Result(column="msg_id", property="msgId", jdbcType=JdbcType.BIGINT),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
        @Result(column="seq_id", property="seqId", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="version_id", property="versionId", jdbcType=JdbcType.BIGINT),
        @Result(column="union_id", property="unionId", jdbcType=JdbcType.VARBINARY)
    })
    List<ImMessageInfo> selectByExampleWithBLOBs(ImMessageInfoExample example);

    @SelectProvider(type=ImMessageInfoSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="send_id", property="sendId", jdbcType=JdbcType.BIGINT),
        @Result(column="msg_id", property="msgId", jdbcType=JdbcType.BIGINT),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
        @Result(column="seq_id", property="seqId", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="version_id", property="versionId", jdbcType=JdbcType.BIGINT)
    })
    List<ImMessageInfo> selectByExample(ImMessageInfoExample example);

    @Select({
        "select",
        "id, send_id, msg_id, status, seq_id, create_time, update_time, version_id, union_id",
        "from im_message_info",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="send_id", property="sendId", jdbcType=JdbcType.BIGINT),
        @Result(column="msg_id", property="msgId", jdbcType=JdbcType.BIGINT),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
        @Result(column="seq_id", property="seqId", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="version_id", property="versionId", jdbcType=JdbcType.BIGINT),
        @Result(column="union_id", property="unionId", jdbcType=JdbcType.VARBINARY)
    })
    ImMessageInfo selectByPrimaryKey(Long id);

    @UpdateProvider(type=ImMessageInfoSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ImMessageInfo record, @Param("example") ImMessageInfoExample example);

    @UpdateProvider(type=ImMessageInfoSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") ImMessageInfo record, @Param("example") ImMessageInfoExample example);

    @UpdateProvider(type=ImMessageInfoSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ImMessageInfo record, @Param("example") ImMessageInfoExample example);

    @UpdateProvider(type=ImMessageInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ImMessageInfo record);

    @Update({
        "update im_message_info",
        "set send_id = #{sendId,jdbcType=BIGINT},",
          "msg_id = #{msgId,jdbcType=BIGINT},",
          "status = #{status,jdbcType=TINYINT},",
          "seq_id = #{seqId,jdbcType=BIGINT},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "version_id = #{versionId,jdbcType=BIGINT},",
          "union_id = #{unionId,jdbcType=VARBINARY}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKeyWithBLOBs(ImMessageInfo record);

    @Update({
        "update im_message_info",
        "set send_id = #{sendId,jdbcType=BIGINT},",
          "msg_id = #{msgId,jdbcType=BIGINT},",
          "status = #{status,jdbcType=TINYINT},",
          "seq_id = #{seqId,jdbcType=BIGINT},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "version_id = #{versionId,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(ImMessageInfo record);
}