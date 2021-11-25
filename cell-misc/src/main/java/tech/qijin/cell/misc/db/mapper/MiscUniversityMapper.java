package tech.qijin.cell.misc.db.mapper;

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
import tech.qijin.cell.misc.db.model.MiscUniversity;
import tech.qijin.cell.misc.db.model.MiscUniversityExample;

public interface MiscUniversityMapper {
    @SelectProvider(type=MiscUniversitySqlProvider.class, method="countByExample")
    long countByExample(MiscUniversityExample example);

    @DeleteProvider(type=MiscUniversitySqlProvider.class, method="deleteByExample")
    int deleteByExample(MiscUniversityExample example);

    @Delete({
        "delete from misc_university",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into misc_university (name, abbr, ",
        "en, logo, code, ",
        "city, province, ",
        "country)",
        "values (#{name,jdbcType=VARCHAR}, #{abbr,jdbcType=VARCHAR}, ",
        "#{en,jdbcType=VARCHAR}, #{logo,jdbcType=VARCHAR}, #{code,jdbcType=BIGINT}, ",
        "#{city,jdbcType=VARCHAR}, #{province,jdbcType=VARCHAR}, ",
        "#{country,jdbcType=CHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(MiscUniversity record);

    @InsertProvider(type=MiscUniversitySqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(MiscUniversity record);

    @SelectProvider(type=MiscUniversitySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="abbr", property="abbr", jdbcType=JdbcType.VARCHAR),
        @Result(column="en", property="en", jdbcType=JdbcType.VARCHAR),
        @Result(column="logo", property="logo", jdbcType=JdbcType.VARCHAR),
        @Result(column="code", property="code", jdbcType=JdbcType.BIGINT),
        @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
        @Result(column="province", property="province", jdbcType=JdbcType.VARCHAR),
        @Result(column="country", property="country", jdbcType=JdbcType.CHAR)
    })
    List<MiscUniversity> selectByExample(MiscUniversityExample example);

    @Select({
        "select",
        "id, name, abbr, en, logo, code, city, province, country",
        "from misc_university",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="abbr", property="abbr", jdbcType=JdbcType.VARCHAR),
        @Result(column="en", property="en", jdbcType=JdbcType.VARCHAR),
        @Result(column="logo", property="logo", jdbcType=JdbcType.VARCHAR),
        @Result(column="code", property="code", jdbcType=JdbcType.BIGINT),
        @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
        @Result(column="province", property="province", jdbcType=JdbcType.VARCHAR),
        @Result(column="country", property="country", jdbcType=JdbcType.CHAR)
    })
    MiscUniversity selectByPrimaryKey(Integer id);

    @UpdateProvider(type=MiscUniversitySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") MiscUniversity record, @Param("example") MiscUniversityExample example);

    @UpdateProvider(type=MiscUniversitySqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") MiscUniversity record, @Param("example") MiscUniversityExample example);

    @UpdateProvider(type=MiscUniversitySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(MiscUniversity record);

    @Update({
        "update misc_university",
        "set name = #{name,jdbcType=VARCHAR},",
          "abbr = #{abbr,jdbcType=VARCHAR},",
          "en = #{en,jdbcType=VARCHAR},",
          "logo = #{logo,jdbcType=VARCHAR},",
          "code = #{code,jdbcType=BIGINT},",
          "city = #{city,jdbcType=VARCHAR},",
          "province = #{province,jdbcType=VARCHAR},",
          "country = #{country,jdbcType=CHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(MiscUniversity record);
}