package tech.qijin.cell.user.db.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
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
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(UserProfile record);

    @InsertProvider(type=UserProfileSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(UserProfile record);

    @SelectProvider(type=UserProfileSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true)
    })
    List<UserProfile> selectByExample(UserProfileExample example);

    @UpdateProvider(type=UserProfileSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") UserProfile record, @Param("example") UserProfileExample example);

    @UpdateProvider(type=UserProfileSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") UserProfile record, @Param("example") UserProfileExample example);
}