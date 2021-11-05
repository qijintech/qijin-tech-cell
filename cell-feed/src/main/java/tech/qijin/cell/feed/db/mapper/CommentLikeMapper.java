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
import tech.qijin.cell.feed.db.model.CommentLike;
import tech.qijin.cell.feed.db.model.CommentLikeExample;

public interface CommentLikeMapper {
    @SelectProvider(type=CommentLikeSqlProvider.class, method="countByExample")
    long countByExample(CommentLikeExample example);

    @DeleteProvider(type=CommentLikeSqlProvider.class, method="deleteByExample")
    int deleteByExample(CommentLikeExample example);

    @Delete({
        "delete from comment_like",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into comment_like (channel, comment_id, ",
        "user_id, valid, update_time, ",
        "create_time)",
        "values (#{channel,jdbcType=VARCHAR}, #{commentId,jdbcType=BIGINT}, ",
        "#{userId,jdbcType=BIGINT}, #{valid,jdbcType=TINYINT}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{createTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(CommentLike record);

    @InsertProvider(type=CommentLikeSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(CommentLike record);

    @SelectProvider(type=CommentLikeSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="comment_id", property="commentId", jdbcType=JdbcType.BIGINT),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="valid", property="valid", jdbcType=JdbcType.TINYINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<CommentLike> selectByExample(CommentLikeExample example);

    @Select({
        "select",
        "id, channel, comment_id, user_id, valid, update_time, create_time",
        "from comment_like",
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
    CommentLike selectByPrimaryKey(Long id);

    @UpdateProvider(type=CommentLikeSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") CommentLike record, @Param("example") CommentLikeExample example);

    @UpdateProvider(type=CommentLikeSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") CommentLike record, @Param("example") CommentLikeExample example);

    @UpdateProvider(type=CommentLikeSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(CommentLike record);

    @Update({
        "update comment_like",
        "set channel = #{channel,jdbcType=VARCHAR},",
          "comment_id = #{commentId,jdbcType=BIGINT},",
          "user_id = #{userId,jdbcType=BIGINT},",
          "valid = #{valid,jdbcType=TINYINT},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(CommentLike record);
}