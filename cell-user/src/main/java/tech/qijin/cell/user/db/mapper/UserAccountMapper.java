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
import tech.qijin.cell.user.db.model.UserAccount;
import tech.qijin.cell.user.db.model.UserAccountExample;

public interface UserAccountMapper {
    @SelectProvider(type=UserAccountSqlProvider.class, method="countByExample")
    long countByExample(UserAccountExample example);

    @DeleteProvider(type=UserAccountSqlProvider.class, method="deleteByExample")
    int deleteByExample(UserAccountExample example);

    @Delete({
        "delete from user_account",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into user_account (channel, type, ",
        "status, create_time, ",
        "update_time)",
        "values (#{channel,jdbcType=VARCHAR}, #{type,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(UserAccount record);

    @InsertProvider(type=UserAccountSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(UserAccount record);

    @SelectProvider(type=UserAccountSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<UserAccount> selectByExample(UserAccountExample example);

    @Select({
        "select",
        "id, channel, type, status, create_time, update_time",
        "from user_account",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    UserAccount selectByPrimaryKey(Long id);

    @UpdateProvider(type=UserAccountSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") UserAccount record, @Param("example") UserAccountExample example);

    @UpdateProvider(type=UserAccountSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") UserAccount record, @Param("example") UserAccountExample example);

    @UpdateProvider(type=UserAccountSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserAccount record);

    @Update({
        "update user_account",
        "set channel = #{channel,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(UserAccount record);
}