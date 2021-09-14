package tech.qijin.cell.iam.db.mapper;

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
import tech.qijin.cell.iam.db.model.RoleAuth;
import tech.qijin.cell.iam.db.model.RoleAuthExample;

public interface RoleAuthMapper {
    @SelectProvider(type=RoleAuthSqlProvider.class, method="countByExample")
    long countByExample(RoleAuthExample example);

    @DeleteProvider(type=RoleAuthSqlProvider.class, method="deleteByExample")
    int deleteByExample(RoleAuthExample example);

    @Delete({
        "delete from role_auth",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into role_auth (channel, role, ",
        "auth, valid, create_time, ",
        "update_time)",
        "values (#{channel,jdbcType=VARCHAR}, #{role,jdbcType=VARCHAR}, ",
        "#{auth,jdbcType=VARCHAR}, #{valid,jdbcType=TINYINT}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(RoleAuth record);

    @InsertProvider(type=RoleAuthSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(RoleAuth record);

    @SelectProvider(type=RoleAuthSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="role", property="role", jdbcType=JdbcType.VARCHAR),
        @Result(column="auth", property="auth", jdbcType=JdbcType.VARCHAR),
        @Result(column="valid", property="valid", jdbcType=JdbcType.TINYINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<RoleAuth> selectByExample(RoleAuthExample example);

    @Select({
        "select",
        "id, channel, role, auth, valid, create_time, update_time",
        "from role_auth",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="role", property="role", jdbcType=JdbcType.VARCHAR),
        @Result(column="auth", property="auth", jdbcType=JdbcType.VARCHAR),
        @Result(column="valid", property="valid", jdbcType=JdbcType.TINYINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    RoleAuth selectByPrimaryKey(Long id);

    @UpdateProvider(type=RoleAuthSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") RoleAuth record, @Param("example") RoleAuthExample example);

    @UpdateProvider(type=RoleAuthSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") RoleAuth record, @Param("example") RoleAuthExample example);

    @UpdateProvider(type=RoleAuthSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(RoleAuth record);

    @Update({
        "update role_auth",
        "set channel = #{channel,jdbcType=VARCHAR},",
          "role = #{role,jdbcType=VARCHAR},",
          "auth = #{auth,jdbcType=VARCHAR},",
          "valid = #{valid,jdbcType=TINYINT},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(RoleAuth record);
}