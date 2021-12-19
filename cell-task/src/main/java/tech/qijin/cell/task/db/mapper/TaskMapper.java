package tech.qijin.cell.task.db.mapper;

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
import tech.qijin.cell.task.db.model.Task;
import tech.qijin.cell.task.db.model.TaskExample;

public interface TaskMapper {
    @SelectProvider(type=TaskSqlProvider.class, method="countByExample")
    long countByExample(TaskExample example);

    @DeleteProvider(type=TaskSqlProvider.class, method="deleteByExample")
    int deleteByExample(TaskExample example);

    @Delete({
        "delete from task",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into task (channel, `name`, ",
        "kind, target, counting_code, ",
        "reward_type, reward_id, ",
        "`order`, update_time, ",
        "create_time)",
        "values (#{channel,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
        "#{kind,jdbcType=VARCHAR}, #{target,jdbcType=BIGINT}, #{countingCode,jdbcType=CHAR}, ",
        "#{rewardType,jdbcType=VARCHAR}, #{rewardId,jdbcType=BIGINT}, ",
        "#{order,jdbcType=INTEGER}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{createTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(Task record);

    @InsertProvider(type=TaskSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(Task record);

    @SelectProvider(type=TaskSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="kind", property="kind", jdbcType=JdbcType.VARCHAR),
        @Result(column="target", property="target", jdbcType=JdbcType.BIGINT),
        @Result(column="counting_code", property="countingCode", jdbcType=JdbcType.CHAR),
        @Result(column="reward_type", property="rewardType", jdbcType=JdbcType.VARCHAR),
        @Result(column="reward_id", property="rewardId", jdbcType=JdbcType.BIGINT),
        @Result(column="order", property="order", jdbcType=JdbcType.INTEGER),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Task> selectByExample(TaskExample example);

    @Select({
        "select",
        "id, channel, `name`, kind, target, counting_code, reward_type, reward_id, `order`, ",
        "update_time, create_time",
        "from task",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="kind", property="kind", jdbcType=JdbcType.VARCHAR),
        @Result(column="target", property="target", jdbcType=JdbcType.BIGINT),
        @Result(column="counting_code", property="countingCode", jdbcType=JdbcType.CHAR),
        @Result(column="reward_type", property="rewardType", jdbcType=JdbcType.VARCHAR),
        @Result(column="reward_id", property="rewardId", jdbcType=JdbcType.BIGINT),
        @Result(column="order", property="order", jdbcType=JdbcType.INTEGER),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    Task selectByPrimaryKey(Long id);

    @UpdateProvider(type=TaskSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Task record, @Param("example") TaskExample example);

    @UpdateProvider(type=TaskSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Task record, @Param("example") TaskExample example);

    @UpdateProvider(type=TaskSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Task record);

    @Update({
        "update task",
        "set channel = #{channel,jdbcType=VARCHAR},",
          "`name` = #{name,jdbcType=VARCHAR},",
          "kind = #{kind,jdbcType=VARCHAR},",
          "target = #{target,jdbcType=BIGINT},",
          "counting_code = #{countingCode,jdbcType=CHAR},",
          "reward_type = #{rewardType,jdbcType=VARCHAR},",
          "reward_id = #{rewardId,jdbcType=BIGINT},",
          "`order` = #{order,jdbcType=INTEGER},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Task record);
}