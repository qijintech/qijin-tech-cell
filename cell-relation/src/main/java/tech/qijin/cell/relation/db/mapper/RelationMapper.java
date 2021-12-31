package tech.qijin.cell.relation.db.mapper;

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
import tech.qijin.cell.relation.db.model.Relation;
import tech.qijin.cell.relation.db.model.RelationExample;

public interface RelationMapper {
    @SelectProvider(type=RelationSqlProvider.class, method="countByExample")
    long countByExample(RelationExample example);

    @DeleteProvider(type=RelationSqlProvider.class, method="deleteByExample")
    int deleteByExample(RelationExample example);

    @Delete({
        "delete from relation",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into relation (channel, user_id, ",
        "peer_user_id, kind, ",
        "format, `status`, ",
        "version, update_time, ",
        "create_time, relation_time)",
        "values (#{channel,jdbcType=VARCHAR}, #{userId,jdbcType=BIGINT}, ",
        "#{peerUserId,jdbcType=BIGINT}, #{kind,jdbcType=VARCHAR}, ",
        "#{format,jdbcType=VARCHAR}, #{status,jdbcType=VARCHAR}, ",
        "#{version,jdbcType=INTEGER}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{relationTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(Relation record);

    @InsertProvider(type=RelationSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(Relation record);

    @SelectProvider(type=RelationSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="peer_user_id", property="peerUserId", jdbcType=JdbcType.BIGINT),
        @Result(column="kind", property="kind", jdbcType=JdbcType.VARCHAR),
        @Result(column="format", property="format", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="version", property="version", jdbcType=JdbcType.INTEGER),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="relation_time", property="relationTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Relation> selectByExample(RelationExample example);

    @Select({
        "select",
        "id, channel, user_id, peer_user_id, kind, format, `status`, version, update_time, ",
        "create_time, relation_time",
        "from relation",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="peer_user_id", property="peerUserId", jdbcType=JdbcType.BIGINT),
        @Result(column="kind", property="kind", jdbcType=JdbcType.VARCHAR),
        @Result(column="format", property="format", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="version", property="version", jdbcType=JdbcType.INTEGER),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="relation_time", property="relationTime", jdbcType=JdbcType.TIMESTAMP)
    })
    Relation selectByPrimaryKey(Long id);

    @UpdateProvider(type=RelationSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Relation record, @Param("example") RelationExample example);

    @UpdateProvider(type=RelationSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Relation record, @Param("example") RelationExample example);

    @UpdateProvider(type=RelationSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Relation record);

    @Update({
        "update relation",
        "set channel = #{channel,jdbcType=VARCHAR},",
          "user_id = #{userId,jdbcType=BIGINT},",
          "peer_user_id = #{peerUserId,jdbcType=BIGINT},",
          "kind = #{kind,jdbcType=VARCHAR},",
          "format = #{format,jdbcType=VARCHAR},",
          "`status` = #{status,jdbcType=VARCHAR},",
          "version = #{version,jdbcType=INTEGER},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "relation_time = #{relationTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Relation record);
}