package tech.qijin.cell.user.db.mapper;

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
import tech.qijin.cell.user.db.model.Drops;
import tech.qijin.cell.user.db.model.DropsExample;

public interface DropsMapper {
    @SelectProvider(type=DropsSqlProvider.class, method="countByExample")
    long countByExample(DropsExample example);

    @DeleteProvider(type=DropsSqlProvider.class, method="deleteByExample")
    int deleteByExample(DropsExample example);

    @Delete({
        "delete from drops",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into drops (channel, name, ",
        "kind, remark, update_time, ",
        "create_time)",
        "values (#{channel,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
        "#{kind,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{createTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(Drops record);

    @InsertProvider(type=DropsSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(Drops record);

    @SelectProvider(type=DropsSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="kind", property="kind", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Drops> selectByExample(DropsExample example);

    @Select({
        "select",
        "id, channel, name, kind, remark, update_time, create_time",
        "from drops",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="kind", property="kind", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    Drops selectByPrimaryKey(Long id);

    @UpdateProvider(type=DropsSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Drops record, @Param("example") DropsExample example);

    @UpdateProvider(type=DropsSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Drops record, @Param("example") DropsExample example);

    @UpdateProvider(type=DropsSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Drops record);

    @Update({
        "update drops",
        "set channel = #{channel,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "kind = #{kind,jdbcType=VARCHAR},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Drops record);
}