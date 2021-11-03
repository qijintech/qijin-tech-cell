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
import tech.qijin.cell.feed.db.model.FeedCommentLike;
import tech.qijin.cell.feed.db.model.FeedCommentLikeExample;

public interface FeedCommentLikeMapper {
    @SelectProvider(type=FeedCommentLikeSqlProvider.class, method="countByExample")
    long countByExample(FeedCommentLikeExample example);

    @DeleteProvider(type=FeedCommentLikeSqlProvider.class, method="deleteByExample")
    int deleteByExample(FeedCommentLikeExample example);

    @Delete({
        "delete from feed_comment_like",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into feed_comment_like (channel, comment_id, ",
        "user_id, valid, update_time, ",
        "create_time)",
        "values (#{channel,jdbcType=VARCHAR}, #{commentId,jdbcType=BIGINT}, ",
        "#{userId,jdbcType=BIGINT}, #{valid,jdbcType=TINYINT}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{createTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(FeedCommentLike record);

    @InsertProvider(type=FeedCommentLikeSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(FeedCommentLike record);

    @SelectProvider(type=FeedCommentLikeSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="comment_id", property="commentId", jdbcType=JdbcType.BIGINT),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="valid", property="valid", jdbcType=JdbcType.TINYINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<FeedCommentLike> selectByExample(FeedCommentLikeExample example);

    @Select({
        "select",
        "id, channel, comment_id, user_id, valid, update_time, create_time",
        "from feed_comment_like",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="comment_id", property="commentId", jdbcType=JdbcType.BIGINT),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="valid", property="valid", jdbcType=JdbcType.TINYINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    FeedCommentLike selectByPrimaryKey(Long id);

    @UpdateProvider(type=FeedCommentLikeSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") FeedCommentLike record, @Param("example") FeedCommentLikeExample example);

    @UpdateProvider(type=FeedCommentLikeSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") FeedCommentLike record, @Param("example") FeedCommentLikeExample example);

    @UpdateProvider(type=FeedCommentLikeSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(FeedCommentLike record);

    @Update({
        "update feed_comment_like",
        "set channel = #{channel,jdbcType=VARCHAR},",
          "comment_id = #{commentId,jdbcType=BIGINT},",
          "user_id = #{userId,jdbcType=BIGINT},",
          "valid = #{valid,jdbcType=TINYINT},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(FeedCommentLike record);
}