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
import tech.qijin.cell.user.db.model.UserAccountMini;
import tech.qijin.cell.user.db.model.UserAccountMiniExample;

public interface UserAccountMiniMapper {
    @SelectProvider(type=UserAccountMiniSqlProvider.class, method="countByExample")
    long countByExample(UserAccountMiniExample example);

    @DeleteProvider(type=UserAccountMiniSqlProvider.class, method="deleteByExample")
    int deleteByExample(UserAccountMiniExample example);

    @Delete({
        "delete from user_account_mini",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into user_account_mini (user_id, channel, ",
        "openid, unionid, ",
        "session_key, mobile, ",
        "status, create_time, ",
        "update_time)",
        "values (#{userId,jdbcType=BIGINT}, #{channel,jdbcType=VARCHAR}, ",
        "#{openid,jdbcType=VARCHAR}, #{unionid,jdbcType=VARCHAR}, ",
        "#{sessionKey,jdbcType=VARCHAR}, #{mobile,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(UserAccountMini record);

    @InsertProvider(type=UserAccountMiniSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(UserAccountMini record);

    @SelectProvider(type=UserAccountMiniSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="openid", property="openid", jdbcType=JdbcType.VARCHAR),
        @Result(column="unionid", property="unionid", jdbcType=JdbcType.VARCHAR),
        @Result(column="session_key", property="sessionKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="mobile", property="mobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<UserAccountMini> selectByExample(UserAccountMiniExample example);

    @Select({
        "select",
        "id, user_id, channel, openid, unionid, session_key, mobile, status, create_time, ",
        "update_time",
        "from user_account_mini",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="openid", property="openid", jdbcType=JdbcType.VARCHAR),
        @Result(column="unionid", property="unionid", jdbcType=JdbcType.VARCHAR),
        @Result(column="session_key", property="sessionKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="mobile", property="mobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    UserAccountMini selectByPrimaryKey(Long id);

    @UpdateProvider(type=UserAccountMiniSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") UserAccountMini record, @Param("example") UserAccountMiniExample example);

    @UpdateProvider(type=UserAccountMiniSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") UserAccountMini record, @Param("example") UserAccountMiniExample example);

    @UpdateProvider(type=UserAccountMiniSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserAccountMini record);

    @Update({
        "update user_account_mini",
        "set user_id = #{userId,jdbcType=BIGINT},",
          "channel = #{channel,jdbcType=VARCHAR},",
          "openid = #{openid,jdbcType=VARCHAR},",
          "unionid = #{unionid,jdbcType=VARCHAR},",
          "session_key = #{sessionKey,jdbcType=VARCHAR},",
          "mobile = #{mobile,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(UserAccountMini record);
}