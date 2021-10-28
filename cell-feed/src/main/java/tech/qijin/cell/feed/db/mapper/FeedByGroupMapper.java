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
import tech.qijin.cell.feed.db.model.FeedByGroup;
import tech.qijin.cell.feed.db.model.FeedByGroupExample;

public interface FeedByGroupMapper {
    @SelectProvider(type=FeedByGroupSqlProvider.class, method="countByExample")
    long countByExample(FeedByGroupExample example);

    @DeleteProvider(type=FeedByGroupSqlProvider.class, method="deleteByExample")
    int deleteByExample(FeedByGroupExample example);

    @Delete({
        "delete from feed_by_group",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into feed_by_group (channel, group_id, ",
        "feed_item_id, valid, ",
        "update_time, create_time)",
        "values (#{channel,jdbcType=VARCHAR}, #{groupId,jdbcType=BIGINT}, ",
        "#{feedItemId,jdbcType=BIGINT}, #{valid,jdbcType=TINYINT}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{createTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(FeedByGroup record);

    @InsertProvider(type=FeedByGroupSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(FeedByGroup record);

    @SelectProvider(type=FeedByGroupSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="group_id", property="groupId", jdbcType=JdbcType.BIGINT),
        @Result(column="feed_item_id", property="feedItemId", jdbcType=JdbcType.BIGINT),
        @Result(column="valid", property="valid", jdbcType=JdbcType.TINYINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<FeedByGroup> selectByExample(FeedByGroupExample example);

    @Select({
        "select",
        "id, channel, group_id, feed_item_id, valid, update_time, create_time",
        "from feed_by_group",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="group_id", property="groupId", jdbcType=JdbcType.BIGINT),
        @Result(column="feed_item_id", property="feedItemId", jdbcType=JdbcType.BIGINT),
        @Result(column="valid", property="valid", jdbcType=JdbcType.TINYINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    FeedByGroup selectByPrimaryKey(Integer id);

    @UpdateProvider(type=FeedByGroupSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") FeedByGroup record, @Param("example") FeedByGroupExample example);

    @UpdateProvider(type=FeedByGroupSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") FeedByGroup record, @Param("example") FeedByGroupExample example);

    @UpdateProvider(type=FeedByGroupSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(FeedByGroup record);

    @Update({
        "update feed_by_group",
        "set channel = #{channel,jdbcType=VARCHAR},",
          "group_id = #{groupId,jdbcType=BIGINT},",
          "feed_item_id = #{feedItemId,jdbcType=BIGINT},",
          "valid = #{valid,jdbcType=TINYINT},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(FeedByGroup record);
}