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
import tech.qijin.cell.user.db.model.UserAccountUsername;
import tech.qijin.cell.user.db.model.UserAccountUsernameExample;

public interface UserAccountUsernameMapper {
    @SelectProvider(type=UserAccountUsernameSqlProvider.class, method="countByExample")
    long countByExample(UserAccountUsernameExample example);

    @DeleteProvider(type=UserAccountUsernameSqlProvider.class, method="deleteByExample")
    int deleteByExample(UserAccountUsernameExample example);

    @Delete({
        "delete from user_account_username",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into user_account_username (channel, username, ",
        "password, status, ",
        "create_time, update_time)",
        "values (#{channel,jdbcType=VARCHAR}, #{username,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{status,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(UserAccountUsername record);

    @InsertProvider(type=UserAccountUsernameSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(UserAccountUsername record);

    @SelectProvider(type=UserAccountUsernameSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<UserAccountUsername> selectByExample(UserAccountUsernameExample example);

    @Select({
        "select",
        "id, channel, username, password, status, create_time, update_time",
        "from user_account_username",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    UserAccountUsername selectByPrimaryKey(Long id);

    @UpdateProvider(type=UserAccountUsernameSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") UserAccountUsername record, @Param("example") UserAccountUsernameExample example);

    @UpdateProvider(type=UserAccountUsernameSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") UserAccountUsername record, @Param("example") UserAccountUsernameExample example);

    @UpdateProvider(type=UserAccountUsernameSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserAccountUsername record);

    @Update({
        "update user_account_username",
        "set channel = #{channel,jdbcType=VARCHAR},",
          "username = #{username,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(UserAccountUsername record);
}