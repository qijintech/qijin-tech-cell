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
import tech.qijin.cell.feed.db.model.FeedComment;
import tech.qijin.cell.feed.db.model.FeedCommentExample;

public interface FeedCommentMapper {
    @SelectProvider(type=FeedCommentSqlProvider.class, method="countByExample")
    long countByExample(FeedCommentExample example);

    @DeleteProvider(type=FeedCommentSqlProvider.class, method="deleteByExample")
    int deleteByExample(FeedCommentExample example);

    @Delete({
        "delete from feed_comment",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into feed_comment (channel, feed_item_Id, ",
        "user_id, to_comment_id, ",
        "content_text, content_image, ",
        "valid, update_time, ",
        "create_time)",
        "values (#{channel,jdbcType=VARCHAR}, #{feedItemId,jdbcType=BIGINT}, ",
        "#{userId,jdbcType=BIGINT}, #{toCommentId,jdbcType=BIGINT}, ",
        "#{contentText,jdbcType=VARCHAR}, #{contentImage,jdbcType=VARCHAR}, ",
        "#{valid,jdbcType=TINYINT}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{createTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(FeedComment record);

    @InsertProvider(type=FeedCommentSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(FeedComment record);

    @SelectProvider(type=FeedCommentSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="feed_item_Id", property="feedItemId", jdbcType=JdbcType.BIGINT),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="to_comment_id", property="toCommentId", jdbcType=JdbcType.BIGINT),
        @Result(column="content_text", property="contentText", jdbcType=JdbcType.VARCHAR),
        @Result(column="content_image", property="contentImage", jdbcType=JdbcType.VARCHAR),
        @Result(column="valid", property="valid", jdbcType=JdbcType.TINYINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<FeedComment> selectByExample(FeedCommentExample example);

    @Select({
        "select",
        "id, channel, feed_item_Id, user_id, to_comment_id, content_text, content_image, ",
        "valid, update_time, create_time",
        "from feed_comment",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="feed_item_Id", property="feedItemId", jdbcType=JdbcType.BIGINT),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="to_comment_id", property="toCommentId", jdbcType=JdbcType.BIGINT),
        @Result(column="content_text", property="contentText", jdbcType=JdbcType.VARCHAR),
        @Result(column="content_image", property="contentImage", jdbcType=JdbcType.VARCHAR),
        @Result(column="valid", property="valid", jdbcType=JdbcType.TINYINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    FeedComment selectByPrimaryKey(Long id);

    @UpdateProvider(type=FeedCommentSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") FeedComment record, @Param("example") FeedCommentExample example);

    @UpdateProvider(type=FeedCommentSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") FeedComment record, @Param("example") FeedCommentExample example);

    @UpdateProvider(type=FeedCommentSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(FeedComment record);

    @Update({
        "update feed_comment",
        "set channel = #{channel,jdbcType=VARCHAR},",
          "feed_item_Id = #{feedItemId,jdbcType=BIGINT},",
          "user_id = #{userId,jdbcType=BIGINT},",
          "to_comment_id = #{toCommentId,jdbcType=BIGINT},",
          "content_text = #{contentText,jdbcType=VARCHAR},",
          "content_image = #{contentImage,jdbcType=VARCHAR},",
          "valid = #{valid,jdbcType=TINYINT},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(FeedComment record);
}