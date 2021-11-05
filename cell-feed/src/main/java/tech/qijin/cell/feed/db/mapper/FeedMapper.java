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
import tech.qijin.cell.feed.db.model.Feed;
import tech.qijin.cell.feed.db.model.FeedExample;

public interface FeedMapper {
    @SelectProvider(type=FeedSqlProvider.class, method="countByExample")
    long countByExample(FeedExample example);

    @DeleteProvider(type=FeedSqlProvider.class, method="deleteByExample")
    int deleteByExample(FeedExample example);

    @Delete({
        "delete from feed",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into feed (channel, user_id, ",
        "text, type, topic_id, ",
        "update_time, create_time)",
        "values (#{channel,jdbcType=VARCHAR}, #{userId,jdbcType=BIGINT}, ",
        "#{text,jdbcType=VARCHAR}, #{type,jdbcType=VARCHAR}, #{topicId,jdbcType=INTEGER}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{createTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(Feed record);

    @InsertProvider(type=FeedSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(Feed record);

    @SelectProvider(type=FeedSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="text", property="text", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="topic_id", property="topicId", jdbcType=JdbcType.INTEGER),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Feed> selectByExample(FeedExample example);

    @Select({
        "select",
        "id, channel, user_id, text, type, topic_id, update_time, create_time",
        "from feed",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="text", property="text", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="topic_id", property="topicId", jdbcType=JdbcType.INTEGER),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    Feed selectByPrimaryKey(Long id);

    @UpdateProvider(type=FeedSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Feed record, @Param("example") FeedExample example);

    @UpdateProvider(type=FeedSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Feed record, @Param("example") FeedExample example);

    @UpdateProvider(type=FeedSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Feed record);

    @Update({
        "update feed",
        "set channel = #{channel,jdbcType=VARCHAR},",
          "user_id = #{userId,jdbcType=BIGINT},",
          "text = #{text,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=VARCHAR},",
          "topic_id = #{topicId,jdbcType=INTEGER},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Feed record);
}