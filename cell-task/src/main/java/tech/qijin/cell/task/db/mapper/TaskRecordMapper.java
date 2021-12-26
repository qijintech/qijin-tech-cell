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
import tech.qijin.cell.task.db.model.TaskRecord;
import tech.qijin.cell.task.db.model.TaskRecordExample;

public interface TaskRecordMapper {
    @SelectProvider(type=TaskRecordSqlProvider.class, method="countByExample")
    long countByExample(TaskRecordExample example);

    @DeleteProvider(type=TaskRecordSqlProvider.class, method="deleteByExample")
    int deleteByExample(TaskRecordExample example);

    @Delete({
        "delete from task_record",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into task_record (channel, user_id, ",
        "task_id, counting_code, ",
        "task_format, target, ",
        "start_time, end_time, ",
        "`status`, reward_type, ",
        "reward_id, version, ",
        "update_time, create_time)",
        "values (#{channel,jdbcType=VARCHAR}, #{userId,jdbcType=BIGINT}, ",
        "#{taskId,jdbcType=BIGINT}, #{countingCode,jdbcType=VARCHAR}, ",
        "#{taskFormat,jdbcType=VARCHAR}, #{target,jdbcType=BIGINT}, ",
        "#{startTime,jdbcType=TIMESTAMP}, #{endTime,jdbcType=TIMESTAMP}, ",
        "#{status,jdbcType=VARCHAR}, #{rewardType,jdbcType=VARCHAR}, ",
        "#{rewardId,jdbcType=BIGINT}, #{version,jdbcType=INTEGER}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{createTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(TaskRecord record);

    @InsertProvider(type=TaskRecordSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(TaskRecord record);

    @SelectProvider(type=TaskRecordSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="task_id", property="taskId", jdbcType=JdbcType.BIGINT),
        @Result(column="counting_code", property="countingCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="task_format", property="taskFormat", jdbcType=JdbcType.VARCHAR),
        @Result(column="target", property="target", jdbcType=JdbcType.BIGINT),
        @Result(column="start_time", property="startTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="end_time", property="endTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="reward_type", property="rewardType", jdbcType=JdbcType.VARCHAR),
        @Result(column="reward_id", property="rewardId", jdbcType=JdbcType.BIGINT),
        @Result(column="version", property="version", jdbcType=JdbcType.INTEGER),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<TaskRecord> selectByExample(TaskRecordExample example);

    @Select({
        "select",
        "id, channel, user_id, task_id, counting_code, task_format, target, start_time, ",
        "end_time, `status`, reward_type, reward_id, version, update_time, create_time",
        "from task_record",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="task_id", property="taskId", jdbcType=JdbcType.BIGINT),
        @Result(column="counting_code", property="countingCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="task_format", property="taskFormat", jdbcType=JdbcType.VARCHAR),
        @Result(column="target", property="target", jdbcType=JdbcType.BIGINT),
        @Result(column="start_time", property="startTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="end_time", property="endTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="reward_type", property="rewardType", jdbcType=JdbcType.VARCHAR),
        @Result(column="reward_id", property="rewardId", jdbcType=JdbcType.BIGINT),
        @Result(column="version", property="version", jdbcType=JdbcType.INTEGER),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    TaskRecord selectByPrimaryKey(Long id);

    @UpdateProvider(type=TaskRecordSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") TaskRecord record, @Param("example") TaskRecordExample example);

    @UpdateProvider(type=TaskRecordSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") TaskRecord record, @Param("example") TaskRecordExample example);

    @UpdateProvider(type=TaskRecordSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TaskRecord record);

    @Update({
        "update task_record",
        "set channel = #{channel,jdbcType=VARCHAR},",
          "user_id = #{userId,jdbcType=BIGINT},",
          "task_id = #{taskId,jdbcType=BIGINT},",
          "counting_code = #{countingCode,jdbcType=VARCHAR},",
          "task_format = #{taskFormat,jdbcType=VARCHAR},",
          "target = #{target,jdbcType=BIGINT},",
          "start_time = #{startTime,jdbcType=TIMESTAMP},",
          "end_time = #{endTime,jdbcType=TIMESTAMP},",
          "`status` = #{status,jdbcType=VARCHAR},",
          "reward_type = #{rewardType,jdbcType=VARCHAR},",
          "reward_id = #{rewardId,jdbcType=BIGINT},",
          "version = #{version,jdbcType=INTEGER},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(TaskRecord record);
}