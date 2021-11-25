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
import tech.qijin.cell.misc.db.model.MiscCity;
import tech.qijin.cell.misc.db.model.MiscCityExample;

public interface MiscCityMapper {
    @SelectProvider(type=MiscCitySqlProvider.class, method="countByExample")
    long countByExample(MiscCityExample example);

    @DeleteProvider(type=MiscCitySqlProvider.class, method="deleteByExample")
    int deleteByExample(MiscCityExample example);

    @Delete({
        "delete from misc_city",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into misc_city (city, province, ",
        "code, spell)",
        "values (#{city,jdbcType=VARCHAR}, #{province,jdbcType=VARCHAR}, ",
        "#{code,jdbcType=VARCHAR}, #{spell,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(MiscCity record);

    @InsertProvider(type=MiscCitySqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(MiscCity record);

    @SelectProvider(type=MiscCitySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
        @Result(column="province", property="province", jdbcType=JdbcType.VARCHAR),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
        @Result(column="spell", property="spell", jdbcType=JdbcType.VARCHAR)
    })
    List<MiscCity> selectByExample(MiscCityExample example);

    @Select({
        "select",
        "id, city, province, code, spell",
        "from misc_city",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
        @Result(column="province", property="province", jdbcType=JdbcType.VARCHAR),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
        @Result(column="spell", property="spell", jdbcType=JdbcType.VARCHAR)
    })
    MiscCity selectByPrimaryKey(Integer id);

    @UpdateProvider(type=MiscCitySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") MiscCity record, @Param("example") MiscCityExample example);

    @UpdateProvider(type=MiscCitySqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") MiscCity record, @Param("example") MiscCityExample example);

    @UpdateProvider(type=MiscCitySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(MiscCity record);

    @Update({
        "update misc_city",
        "set city = #{city,jdbcType=VARCHAR},",
          "province = #{province,jdbcType=VARCHAR},",
          "code = #{code,jdbcType=VARCHAR},",
          "spell = #{spell,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(MiscCity record);
}