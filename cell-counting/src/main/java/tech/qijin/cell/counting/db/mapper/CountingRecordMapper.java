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
import tech.qijin.cell.counting.db.model.CountingRecord;
import tech.qijin.cell.counting.db.model.CountingRecordExample;

public interface CountingRecordMapper {
    @SelectProvider(type=CountingRecordSqlProvider.class, method="countByExample")
    long countByExample(CountingRecordExample example);

    @DeleteProvider(type=CountingRecordSqlProvider.class, method="deleteByExample")
    int deleteByExample(CountingRecordExample example);

    @Delete({
        "delete from counting_record",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into counting_record (channel, user_id, ",
        "counting_code, counting_format, ",
        "curr, target, version, ",
        "update_time, create_time)",
        "values (#{channel,jdbcType=VARCHAR}, #{userId,jdbcType=BIGINT}, ",
        "#{countingCode,jdbcType=CHAR}, #{countingFormat,jdbcType=VARCHAR}, ",
        "#{curr,jdbcType=BIGINT}, #{target,jdbcType=BIGINT}, #{version,jdbcType=INTEGER}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{createTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(CountingRecord record);

    @InsertProvider(type=CountingRecordSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(CountingRecord record);

    @SelectProvider(type=CountingRecordSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="counting_code", property="countingCode", jdbcType=JdbcType.CHAR),
        @Result(column="counting_format", property="countingFormat", jdbcType=JdbcType.VARCHAR),
        @Result(column="curr", property="curr", jdbcType=JdbcType.BIGINT),
        @Result(column="target", property="target", jdbcType=JdbcType.BIGINT),
        @Result(column="version", property="version", jdbcType=JdbcType.INTEGER),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<CountingRecord> selectByExample(CountingRecordExample example);

    @Select({
        "select",
        "id, channel, user_id, counting_code, counting_format, curr, target, version, ",
        "update_time, create_time",
        "from counting_record",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="counting_code", property="countingCode", jdbcType=JdbcType.CHAR),
        @Result(column="counting_format", property="countingFormat", jdbcType=JdbcType.VARCHAR),
        @Result(column="curr", property="curr", jdbcType=JdbcType.BIGINT),
        @Result(column="target", property="target", jdbcType=JdbcType.BIGINT),
        @Result(column="version", property="version", jdbcType=JdbcType.INTEGER),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    CountingRecord selectByPrimaryKey(Long id);

    @UpdateProvider(type=CountingRecordSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") CountingRecord record, @Param("example") CountingRecordExample example);

    @UpdateProvider(type=CountingRecordSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") CountingRecord record, @Param("example") CountingRecordExample example);

    @UpdateProvider(type=CountingRecordSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(CountingRecord record);

    @Update({
        "update counting_record",
        "set channel = #{channel,jdbcType=VARCHAR},",
          "user_id = #{userId,jdbcType=BIGINT},",
          "counting_code = #{countingCode,jdbcType=CHAR},",
          "counting_format = #{countingFormat,jdbcType=VARCHAR},",
          "curr = #{curr,jdbcType=BIGINT},",
          "target = #{target,jdbcType=BIGINT},",
          "version = #{version,jdbcType=INTEGER},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(CountingRecord record);
}