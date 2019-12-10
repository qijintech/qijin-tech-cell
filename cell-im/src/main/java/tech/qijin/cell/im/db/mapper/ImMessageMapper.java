package tech.qijin.cell.im.db.mapper;

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
import tech.qijin.cell.im.db.model.ImMessage;
import tech.qijin.cell.im.db.model.ImMessageExample;

public interface ImMessageMapper {
    @SelectProvider(type=ImMessageSqlProvider.class, method="countByExample")
    long countByExample(ImMessageExample example);

    @DeleteProvider(type=ImMessageSqlProvider.class, method="deleteByExample")
    int deleteByExample(ImMessageExample example);

    @Delete({
        "delete from im_message",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into im_message (message_id)",
        "values (#{messageId,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(ImMessage record);

    @InsertProvider(type=ImMessageSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(ImMessage record);

    @SelectProvider(type=ImMessageSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="message_id", property="messageId", jdbcType=JdbcType.INTEGER)
    })
    List<ImMessage> selectByExample(ImMessageExample example);

    @Select({
        "select",
        "id, message_id",
        "from im_message",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="message_id", property="messageId", jdbcType=JdbcType.INTEGER)
    })
    ImMessage selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ImMessageSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ImMessage record, @Param("example") ImMessageExample example);

    @UpdateProvider(type=ImMessageSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ImMessage record, @Param("example") ImMessageExample example);

    @UpdateProvider(type=ImMessageSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ImMessage record);

    @Update({
        "update im_message",
        "set message_id = #{messageId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ImMessage record);
}