package tech.qijin.cell.counting.db.mapper;

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
import tech.qijin.cell.counting.db.model.CountingTemplate;
import tech.qijin.cell.counting.db.model.CountingTemplateExample;

public interface CountingTemplateMapper {
    @SelectProvider(type=CountingTemplateSqlProvider.class, method="countByExample")
    long countByExample(CountingTemplateExample example);

    @DeleteProvider(type=CountingTemplateSqlProvider.class, method="deleteByExample")
    int deleteByExample(CountingTemplateExample example);

    @Delete({
        "delete from counting_template",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into counting_template (channel, name, ",
        "code, mode, event, ",
        "target, on_target_mode, ",
        "update_time, create_time)",
        "values (#{channel,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
        "#{code,jdbcType=CHAR}, #{mode,jdbcType=VARCHAR}, #{event,jdbcType=VARCHAR}, ",
        "#{target,jdbcType=BIGINT}, #{onTargetMode,jdbcType=VARCHAR}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{createTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(CountingTemplate record);

    @InsertProvider(type=CountingTemplateSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(CountingTemplate record);

    @SelectProvider(type=CountingTemplateSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="code", property="code", jdbcType=JdbcType.CHAR),
        @Result(column="mode", property="mode", jdbcType=JdbcType.VARCHAR),
        @Result(column="event", property="event", jdbcType=JdbcType.VARCHAR),
        @Result(column="target", property="target", jdbcType=JdbcType.BIGINT),
        @Result(column="on_target_mode", property="onTargetMode", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<CountingTemplate> selectByExample(CountingTemplateExample example);

    @Select({
        "select",
        "id, channel, name, code, mode, event, target, on_target_mode, update_time, create_time",
        "from counting_template",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="code", property="code", jdbcType=JdbcType.CHAR),
        @Result(column="mode", property="mode", jdbcType=JdbcType.VARCHAR),
        @Result(column="event", property="event", jdbcType=JdbcType.VARCHAR),
        @Result(column="target", property="target", jdbcType=JdbcType.BIGINT),
        @Result(column="on_target_mode", property="onTargetMode", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    CountingTemplate selectByPrimaryKey(Long id);

    @UpdateProvider(type=CountingTemplateSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") CountingTemplate record, @Param("example") CountingTemplateExample example);

    @UpdateProvider(type=CountingTemplateSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") CountingTemplate record, @Param("example") CountingTemplateExample example);

    @UpdateProvider(type=CountingTemplateSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(CountingTemplate record);

    @Update({
        "update counting_template",
        "set channel = #{channel,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "code = #{code,jdbcType=CHAR},",
          "mode = #{mode,jdbcType=VARCHAR},",
          "event = #{event,jdbcType=VARCHAR},",
          "target = #{target,jdbcType=BIGINT},",
          "on_target_mode = #{onTargetMode,jdbcType=VARCHAR},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(CountingTemplate record);
}