package tech.qijin.cell.gim.db.mapper;

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
import tech.qijin.cell.gim.db.model.GimMessage;
import tech.qijin.cell.gim.db.model.GimMessageExample;

public interface GimMessageMapper {
    @SelectProvider(type=GimMessageSqlProvider.class, method="countByExample")
    long countByExample(GimMessageExample example);

    @DeleteProvider(type=GimMessageSqlProvider.class, method="deleteByExample")
    int deleteByExample(GimMessageExample example);

    @Delete({
        "delete from gim_message",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into gim_message (message_id)",
        "values (#{messageId,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(GimMessage record);

    @InsertProvider(type=GimMessageSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(GimMessage record);

    @SelectProvider(type=GimMessageSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="message_id", property="messageId", jdbcType=JdbcType.INTEGER)
    })
    List<GimMessage> selectByExample(GimMessageExample example);

    @Select({
        "select",
        "id, message_id",
        "from gim_message",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="message_id", property="messageId", jdbcType=JdbcType.INTEGER)
    })
    GimMessage selectByPrimaryKey(Integer id);

    @UpdateProvider(type=GimMessageSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") GimMessage record, @Param("example") GimMessageExample example);

    @UpdateProvider(type=GimMessageSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") GimMessage record, @Param("example") GimMessageExample example);

    @UpdateProvider(type=GimMessageSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(GimMessage record);

    @Update({
        "update gim_message",
        "set message_id = #{messageId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(GimMessage record);
}