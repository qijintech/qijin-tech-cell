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
import tech.qijin.cell.feed.db.model.FeedImage;
import tech.qijin.cell.feed.db.model.FeedImageExample;

public interface FeedImageMapper {
    @SelectProvider(type=FeedImageSqlProvider.class, method="countByExample")
    long countByExample(FeedImageExample example);

    @DeleteProvider(type=FeedImageSqlProvider.class, method="deleteByExample")
    int deleteByExample(FeedImageExample example);

    @Delete({
        "delete from feed_image",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into feed_image (channel, feed_item_id, ",
        "url, valid, update_time, ",
        "create_time)",
        "values (#{channel,jdbcType=VARCHAR}, #{feedItemId,jdbcType=BIGINT}, ",
        "#{url,jdbcType=VARCHAR}, #{valid,jdbcType=TINYINT}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{createTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(FeedImage record);

    @InsertProvider(type=FeedImageSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(FeedImage record);

    @SelectProvider(type=FeedImageSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="feed_item_id", property="feedItemId", jdbcType=JdbcType.BIGINT),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="valid", property="valid", jdbcType=JdbcType.TINYINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<FeedImage> selectByExample(FeedImageExample example);

    @Select({
        "select",
        "id, channel, feed_item_id, url, valid, update_time, create_time",
        "from feed_image",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="feed_item_id", property="feedItemId", jdbcType=JdbcType.BIGINT),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="valid", property="valid", jdbcType=JdbcType.TINYINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    FeedImage selectByPrimaryKey(Long id);

    @UpdateProvider(type=FeedImageSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") FeedImage record, @Param("example") FeedImageExample example);

    @UpdateProvider(type=FeedImageSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") FeedImage record, @Param("example") FeedImageExample example);

    @UpdateProvider(type=FeedImageSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(FeedImage record);

    @Update({
        "update feed_image",
        "set channel = #{channel,jdbcType=VARCHAR},",
          "feed_item_id = #{feedItemId,jdbcType=BIGINT},",
          "url = #{url,jdbcType=VARCHAR},",
          "valid = #{valid,jdbcType=TINYINT},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(FeedImage record);
}