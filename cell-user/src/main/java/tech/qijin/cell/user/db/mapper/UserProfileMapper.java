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
import tech.qijin.cell.user.db.model.UserProfile;
import tech.qijin.cell.user.db.model.UserProfileExample;

public interface UserProfileMapper {
    @SelectProvider(type=UserProfileSqlProvider.class, method="countByExample")
    long countByExample(UserProfileExample example);

    @DeleteProvider(type=UserProfileSqlProvider.class, method="deleteByExample")
    int deleteByExample(UserProfileExample example);

    @Delete({
        "delete from user_profile",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into user_profile (channel, user_id, ",
        "name, avatar, gender, ",
        "mobile, birthday, ",
        "born_city, live_city, ",
        "edu, edu_degree, ",
        "job, height, weight, ",
        "marital_status, create_time, ",
        "update_time)",
        "values (#{channel,jdbcType=VARCHAR}, #{userId,jdbcType=BIGINT}, ",
        "#{name,jdbcType=VARCHAR}, #{avatar,jdbcType=VARCHAR}, #{gender,jdbcType=VARCHAR}, ",
        "#{mobile,jdbcType=CHAR}, #{birthday,jdbcType=TIMESTAMP}, ",
        "#{bornCity,jdbcType=VARCHAR}, #{liveCity,jdbcType=VARCHAR}, ",
        "#{edu,jdbcType=VARCHAR}, #{eduDegree,jdbcType=VARCHAR}, ",
        "#{job,jdbcType=VARCHAR}, #{height,jdbcType=INTEGER}, #{weight,jdbcType=INTEGER}, ",
        "#{maritalStatus,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(UserProfile record);

    @InsertProvider(type=UserProfileSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(UserProfile record);

    @SelectProvider(type=UserProfileSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="avatar", property="avatar", jdbcType=JdbcType.VARCHAR),
        @Result(column="gender", property="gender", jdbcType=JdbcType.VARCHAR),
        @Result(column="mobile", property="mobile", jdbcType=JdbcType.CHAR),
        @Result(column="birthday", property="birthday", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="born_city", property="bornCity", jdbcType=JdbcType.VARCHAR),
        @Result(column="live_city", property="liveCity", jdbcType=JdbcType.VARCHAR),
        @Result(column="edu", property="edu", jdbcType=JdbcType.VARCHAR),
        @Result(column="edu_degree", property="eduDegree", jdbcType=JdbcType.VARCHAR),
        @Result(column="job", property="job", jdbcType=JdbcType.VARCHAR),
        @Result(column="height", property="height", jdbcType=JdbcType.INTEGER),
        @Result(column="weight", property="weight", jdbcType=JdbcType.INTEGER),
        @Result(column="marital_status", property="maritalStatus", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<UserProfile> selectByExample(UserProfileExample example);

    @Select({
        "select",
        "id, channel, user_id, name, avatar, gender, mobile, birthday, born_city, live_city, ",
        "edu, edu_degree, job, height, weight, marital_status, create_time, update_time",
        "from user_profile",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="avatar", property="avatar", jdbcType=JdbcType.VARCHAR),
        @Result(column="gender", property="gender", jdbcType=JdbcType.VARCHAR),
        @Result(column="mobile", property="mobile", jdbcType=JdbcType.CHAR),
        @Result(column="birthday", property="birthday", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="born_city", property="bornCity", jdbcType=JdbcType.VARCHAR),
        @Result(column="live_city", property="liveCity", jdbcType=JdbcType.VARCHAR),
        @Result(column="edu", property="edu", jdbcType=JdbcType.VARCHAR),
        @Result(column="edu_degree", property="eduDegree", jdbcType=JdbcType.VARCHAR),
        @Result(column="job", property="job", jdbcType=JdbcType.VARCHAR),
        @Result(column="height", property="height", jdbcType=JdbcType.INTEGER),
        @Result(column="weight", property="weight", jdbcType=JdbcType.INTEGER),
        @Result(column="marital_status", property="maritalStatus", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    UserProfile selectByPrimaryKey(Long id);

    @UpdateProvider(type=UserProfileSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") UserProfile record, @Param("example") UserProfileExample example);

    @UpdateProvider(type=UserProfileSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") UserProfile record, @Param("example") UserProfileExample example);

    @UpdateProvider(type=UserProfileSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserProfile record);

    @Update({
        "update user_profile",
        "set channel = #{channel,jdbcType=VARCHAR},",
          "user_id = #{userId,jdbcType=BIGINT},",
          "name = #{name,jdbcType=VARCHAR},",
          "avatar = #{avatar,jdbcType=VARCHAR},",
          "gender = #{gender,jdbcType=VARCHAR},",
          "mobile = #{mobile,jdbcType=CHAR},",
          "birthday = #{birthday,jdbcType=TIMESTAMP},",
          "born_city = #{bornCity,jdbcType=VARCHAR},",
          "live_city = #{liveCity,jdbcType=VARCHAR},",
          "edu = #{edu,jdbcType=VARCHAR},",
          "edu_degree = #{eduDegree,jdbcType=VARCHAR},",
          "job = #{job,jdbcType=VARCHAR},",
          "height = #{height,jdbcType=INTEGER},",
          "weight = #{weight,jdbcType=INTEGER},",
          "marital_status = #{maritalStatus,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(UserProfile record);
}