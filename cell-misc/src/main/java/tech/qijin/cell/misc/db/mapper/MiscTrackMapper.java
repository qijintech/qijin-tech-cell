package tech.qijin.cell.misc.db.mapper;

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
import tech.qijin.cell.misc.db.model.MiscTrack;
import tech.qijin.cell.misc.db.model.MiscTrackExample;

public interface MiscTrackMapper {
    @SelectProvider(type=MiscTrackSqlProvider.class, method="countByExample")
    long countByExample(MiscTrackExample example);

    @DeleteProvider(type=MiscTrackSqlProvider.class, method="deleteByExample")
    int deleteByExample(MiscTrackExample example);

    @Delete({
        "delete from misc_track",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into misc_track (channel, event, ",
        "operator, data, create_time, ",
        "update_time)",
        "values (#{channel,jdbcType=VARCHAR}, #{event,jdbcType=VARCHAR}, ",
        "#{operator,jdbcType=BIGINT}, #{data,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(MiscTrack record);

    @InsertProvider(type=MiscTrackSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(MiscTrack record);

    @SelectProvider(type=MiscTrackSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="event", property="event", jdbcType=JdbcType.VARCHAR),
        @Result(column="operator", property="operator", jdbcType=JdbcType.BIGINT),
        @Result(column="data", property="data", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<MiscTrack> selectByExample(MiscTrackExample example);

    @Select({
        "select",
        "id, channel, event, operator, data, create_time, update_time",
        "from misc_track",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="event", property="event", jdbcType=JdbcType.VARCHAR),
        @Result(column="operator", property="operator", jdbcType=JdbcType.BIGINT),
        @Result(column="data", property="data", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    MiscTrack selectByPrimaryKey(Integer id);

    @UpdateProvider(type=MiscTrackSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") MiscTrack record, @Param("example") MiscTrackExample example);

    @UpdateProvider(type=MiscTrackSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") MiscTrack record, @Param("example") MiscTrackExample example);

    @UpdateProvider(type=MiscTrackSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(MiscTrack record);

    @Update({
        "update misc_track",
        "set channel = #{channel,jdbcType=VARCHAR},",
          "event = #{event,jdbcType=VARCHAR},",
          "operator = #{operator,jdbcType=BIGINT},",
          "data = #{data,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(MiscTrack record);
}