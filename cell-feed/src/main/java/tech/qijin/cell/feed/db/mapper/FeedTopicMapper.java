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
import tech.qijin.cell.feed.db.model.FeedTopic;
import tech.qijin.cell.feed.db.model.FeedTopicExample;

public interface FeedTopicMapper {
    @SelectProvider(type=FeedTopicSqlProvider.class, method="countByExample")
    long countByExample(FeedTopicExample example);

    @DeleteProvider(type=FeedTopicSqlProvider.class, method="deleteByExample")
    int deleteByExample(FeedTopicExample example);

    @Delete({
        "delete from feed_topic",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into feed_topic (channel, cover, ",
        "text, valid, description, ",
        "update_time, create_time)",
        "values (#{channel,jdbcType=VARCHAR}, #{cover,jdbcType=VARCHAR}, ",
        "#{text,jdbcType=VARCHAR}, #{valid,jdbcType=TINYINT}, #{description,jdbcType=VARCHAR}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{createTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(FeedTopic record);

    @InsertProvider(type=FeedTopicSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(FeedTopic record);

    @SelectProvider(type=FeedTopicSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="cover", property="cover", jdbcType=JdbcType.VARCHAR),
        @Result(column="text", property="text", jdbcType=JdbcType.VARCHAR),
        @Result(column="valid", property="valid", jdbcType=JdbcType.TINYINT),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<FeedTopic> selectByExample(FeedTopicExample example);

    @Select({
        "select",
        "id, channel, cover, text, valid, description, update_time, create_time",
        "from feed_topic",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="cover", property="cover", jdbcType=JdbcType.VARCHAR),
        @Result(column="text", property="text", jdbcType=JdbcType.VARCHAR),
        @Result(column="valid", property="valid", jdbcType=JdbcType.TINYINT),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    FeedTopic selectByPrimaryKey(Integer id);

    @UpdateProvider(type=FeedTopicSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") FeedTopic record, @Param("example") FeedTopicExample example);

    @UpdateProvider(type=FeedTopicSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") FeedTopic record, @Param("example") FeedTopicExample example);

    @UpdateProvider(type=FeedTopicSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(FeedTopic record);

    @Update({
        "update feed_topic",
        "set channel = #{channel,jdbcType=VARCHAR},",
          "cover = #{cover,jdbcType=VARCHAR},",
          "text = #{text,jdbcType=VARCHAR},",
          "valid = #{valid,jdbcType=TINYINT},",
          "description = #{description,jdbcType=VARCHAR},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(FeedTopic record);
}