package tech.qijin.cell.feed.db.mapper;

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
import tech.qijin.cell.feed.db.model.FeedInteraction;
import tech.qijin.cell.feed.db.model.FeedInteractionExample;

public interface FeedInteractionMapper {
    @SelectProvider(type=FeedInteractionSqlProvider.class, method="countByExample")
    long countByExample(FeedInteractionExample example);

    @DeleteProvider(type=FeedInteractionSqlProvider.class, method="deleteByExample")
    int deleteByExample(FeedInteractionExample example);

    @Delete({
        "delete from feed_interaction",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into feed_interaction (channel, user_id, ",
        "from_user_id, kind, ",
        "feed_id, comment_id, ",
        "format, valid, update_time, ",
        "create_time)",
        "values (#{channel,jdbcType=VARCHAR}, #{userId,jdbcType=BIGINT}, ",
        "#{fromUserId,jdbcType=BIGINT}, #{kind,jdbcType=VARCHAR}, ",
        "#{feedId,jdbcType=BIGINT}, #{commentId,jdbcType=BIGINT}, ",
        "#{format,jdbcType=VARCHAR}, #{valid,jdbcType=TINYINT}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{createTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(FeedInteraction record);

    @InsertProvider(type=FeedInteractionSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(FeedInteraction record);

    @SelectProvider(type=FeedInteractionSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="from_user_id", property="fromUserId", jdbcType=JdbcType.BIGINT),
        @Result(column="kind", property="kind", jdbcType=JdbcType.VARCHAR),
        @Result(column="feed_id", property="feedId", jdbcType=JdbcType.BIGINT),
        @Result(column="comment_id", property="commentId", jdbcType=JdbcType.BIGINT),
        @Result(column="format", property="format", jdbcType=JdbcType.VARCHAR),
        @Result(column="valid", property="valid", jdbcType=JdbcType.TINYINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<FeedInteraction> selectByExample(FeedInteractionExample example);

    @Select({
        "select",
        "id, channel, user_id, from_user_id, kind, feed_id, comment_id, format, valid, ",
        "update_time, create_time",
        "from feed_interaction",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="from_user_id", property="fromUserId", jdbcType=JdbcType.BIGINT),
        @Result(column="kind", property="kind", jdbcType=JdbcType.VARCHAR),
        @Result(column="feed_id", property="feedId", jdbcType=JdbcType.BIGINT),
        @Result(column="comment_id", property="commentId", jdbcType=JdbcType.BIGINT),
        @Result(column="format", property="format", jdbcType=JdbcType.VARCHAR),
        @Result(column="valid", property="valid", jdbcType=JdbcType.TINYINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    FeedInteraction selectByPrimaryKey(Long id);

    @UpdateProvider(type=FeedInteractionSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") FeedInteraction record, @Param("example") FeedInteractionExample example);

    @UpdateProvider(type=FeedInteractionSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") FeedInteraction record, @Param("example") FeedInteractionExample example);

    @UpdateProvider(type=FeedInteractionSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(FeedInteraction record);

    @Update({
        "update feed_interaction",
        "set channel = #{channel,jdbcType=VARCHAR},",
          "user_id = #{userId,jdbcType=BIGINT},",
          "from_user_id = #{fromUserId,jdbcType=BIGINT},",
          "kind = #{kind,jdbcType=VARCHAR},",
          "feed_id = #{feedId,jdbcType=BIGINT},",
          "comment_id = #{commentId,jdbcType=BIGINT},",
          "format = #{format,jdbcType=VARCHAR},",
          "valid = #{valid,jdbcType=TINYINT},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(FeedInteraction record);
}