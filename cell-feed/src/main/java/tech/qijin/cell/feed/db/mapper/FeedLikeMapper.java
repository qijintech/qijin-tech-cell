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
import tech.qijin.cell.feed.db.model.FeedLike;
import tech.qijin.cell.feed.db.model.FeedLikeExample;

public interface FeedLikeMapper {
    @SelectProvider(type=FeedLikeSqlProvider.class, method="countByExample")
    long countByExample(FeedLikeExample example);

    @DeleteProvider(type=FeedLikeSqlProvider.class, method="deleteByExample")
    int deleteByExample(FeedLikeExample example);

    @Delete({
        "delete from feed_like",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into feed_like (channel, feed_id, ",
        "user_id, valid, update_time, ",
        "create_time)",
        "values (#{channel,jdbcType=VARCHAR}, #{feedId,jdbcType=BIGINT}, ",
        "#{userId,jdbcType=BIGINT}, #{valid,jdbcType=TINYINT}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{createTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(FeedLike record);

    @InsertProvider(type=FeedLikeSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(FeedLike record);

    @SelectProvider(type=FeedLikeSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="feed_id", property="feedId", jdbcType=JdbcType.BIGINT),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="valid", property="valid", jdbcType=JdbcType.TINYINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<FeedLike> selectByExample(FeedLikeExample example);

    @Select({
        "select",
        "id, channel, feed_id, user_id, valid, update_time, create_time",
        "from feed_like",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="feed_id", property="feedId", jdbcType=JdbcType.BIGINT),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="valid", property="valid", jdbcType=JdbcType.TINYINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    FeedLike selectByPrimaryKey(Long id);

    @UpdateProvider(type=FeedLikeSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") FeedLike record, @Param("example") FeedLikeExample example);

    @UpdateProvider(type=FeedLikeSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") FeedLike record, @Param("example") FeedLikeExample example);

    @UpdateProvider(type=FeedLikeSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(FeedLike record);

    @Update({
        "update feed_like",
        "set channel = #{channel,jdbcType=VARCHAR},",
          "feed_id = #{feedId,jdbcType=BIGINT},",
          "user_id = #{userId,jdbcType=BIGINT},",
          "valid = #{valid,jdbcType=TINYINT},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(FeedLike record);
}