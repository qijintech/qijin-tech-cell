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
import tech.qijin.cell.feed.db.model.FeedItemLike;
import tech.qijin.cell.feed.db.model.FeedItemLikeExample;

public interface FeedItemLikeMapper {
    @SelectProvider(type=FeedItemLikeSqlProvider.class, method="countByExample")
    long countByExample(FeedItemLikeExample example);

    @DeleteProvider(type=FeedItemLikeSqlProvider.class, method="deleteByExample")
    int deleteByExample(FeedItemLikeExample example);

    @Delete({
        "delete from feed_item_like",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into feed_item_like (channel, feed_item_id, ",
        "user_id, valid, update_time, ",
        "create_time)",
        "values (#{channel,jdbcType=VARCHAR}, #{feedItemId,jdbcType=BIGINT}, ",
        "#{userId,jdbcType=BIGINT}, #{valid,jdbcType=TINYINT}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{createTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(FeedItemLike record);

    @InsertProvider(type=FeedItemLikeSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(FeedItemLike record);

    @SelectProvider(type=FeedItemLikeSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="feed_item_id", property="feedItemId", jdbcType=JdbcType.BIGINT),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="valid", property="valid", jdbcType=JdbcType.TINYINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<FeedItemLike> selectByExample(FeedItemLikeExample example);

    @Select({
        "select",
        "id, channel, feed_item_id, user_id, valid, update_time, create_time",
        "from feed_item_like",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="feed_item_id", property="feedItemId", jdbcType=JdbcType.BIGINT),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="valid", property="valid", jdbcType=JdbcType.TINYINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    FeedItemLike selectByPrimaryKey(Integer id);

    @UpdateProvider(type=FeedItemLikeSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") FeedItemLike record, @Param("example") FeedItemLikeExample example);

    @UpdateProvider(type=FeedItemLikeSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") FeedItemLike record, @Param("example") FeedItemLikeExample example);

    @UpdateProvider(type=FeedItemLikeSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(FeedItemLike record);

    @Update({
        "update feed_item_like",
        "set channel = #{channel,jdbcType=VARCHAR},",
          "feed_item_id = #{feedItemId,jdbcType=BIGINT},",
          "user_id = #{userId,jdbcType=BIGINT},",
          "valid = #{valid,jdbcType=TINYINT},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(FeedItemLike record);
}