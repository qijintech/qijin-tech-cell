package tech.qijin.cell.account.db.mapper;

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
import tech.qijin.cell.account.db.model.DropsItem;
import tech.qijin.cell.account.db.model.DropsItemExample;

public interface DropsItemMapper {
    @SelectProvider(type=DropsItemSqlProvider.class, method="countByExample")
    long countByExample(DropsItemExample example);

    @DeleteProvider(type=DropsItemSqlProvider.class, method="deleteByExample")
    int deleteByExample(DropsItemExample example);

    @Delete({
        "delete from drops_item",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into drops_item (channel, `name`, ",
        "kind, item_id, drops_id, ",
        "amount, update_time, ",
        "create_time)",
        "values (#{channel,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
        "#{kind,jdbcType=VARCHAR}, #{itemId,jdbcType=BIGINT}, #{dropsId,jdbcType=BIGINT}, ",
        "#{amount,jdbcType=BIGINT}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{createTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(DropsItem record);

    @InsertProvider(type=DropsItemSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(DropsItem record);

    @SelectProvider(type=DropsItemSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="kind", property="kind", jdbcType=JdbcType.VARCHAR),
        @Result(column="item_id", property="itemId", jdbcType=JdbcType.BIGINT),
        @Result(column="drops_id", property="dropsId", jdbcType=JdbcType.BIGINT),
        @Result(column="amount", property="amount", jdbcType=JdbcType.BIGINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<DropsItem> selectByExample(DropsItemExample example);

    @Select({
        "select",
        "id, channel, `name`, kind, item_id, drops_id, amount, update_time, create_time",
        "from drops_item",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="kind", property="kind", jdbcType=JdbcType.VARCHAR),
        @Result(column="item_id", property="itemId", jdbcType=JdbcType.BIGINT),
        @Result(column="drops_id", property="dropsId", jdbcType=JdbcType.BIGINT),
        @Result(column="amount", property="amount", jdbcType=JdbcType.BIGINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    DropsItem selectByPrimaryKey(Long id);

    @UpdateProvider(type=DropsItemSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") DropsItem record, @Param("example") DropsItemExample example);

    @UpdateProvider(type=DropsItemSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") DropsItem record, @Param("example") DropsItemExample example);

    @UpdateProvider(type=DropsItemSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(DropsItem record);

    @Update({
        "update drops_item",
        "set channel = #{channel,jdbcType=VARCHAR},",
          "`name` = #{name,jdbcType=VARCHAR},",
          "kind = #{kind,jdbcType=VARCHAR},",
          "item_id = #{itemId,jdbcType=BIGINT},",
          "drops_id = #{dropsId,jdbcType=BIGINT},",
          "amount = #{amount,jdbcType=BIGINT},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(DropsItem record);
}