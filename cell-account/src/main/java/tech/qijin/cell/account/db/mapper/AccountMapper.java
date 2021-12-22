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
import tech.qijin.cell.account.db.model.Account;
import tech.qijin.cell.account.db.model.AccountExample;

public interface AccountMapper {
    @SelectProvider(type=AccountSqlProvider.class, method="countByExample")
    long countByExample(AccountExample example);

    @DeleteProvider(type=AccountSqlProvider.class, method="deleteByExample")
    int deleteByExample(AccountExample example);

    @Delete({
        "delete from account",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into account (channel, user_id, ",
        "kind, `name`, is_item, ",
        "balance, version, ",
        "update_time, create_time)",
        "values (#{channel,jdbcType=VARCHAR}, #{userId,jdbcType=BIGINT}, ",
        "#{kind,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, #{isItem,jdbcType=TINYINT}, ",
        "#{balance,jdbcType=BIGINT}, #{version,jdbcType=INTEGER}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{createTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(Account record);

    @InsertProvider(type=AccountSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(Account record);

    @SelectProvider(type=AccountSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="kind", property="kind", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_item", property="isItem", jdbcType=JdbcType.TINYINT),
        @Result(column="balance", property="balance", jdbcType=JdbcType.BIGINT),
        @Result(column="version", property="version", jdbcType=JdbcType.INTEGER),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Account> selectByExample(AccountExample example);

    @Select({
        "select",
        "id, channel, user_id, kind, `name`, is_item, balance, version, update_time, ",
        "create_time",
        "from account",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="kind", property="kind", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_item", property="isItem", jdbcType=JdbcType.TINYINT),
        @Result(column="balance", property="balance", jdbcType=JdbcType.BIGINT),
        @Result(column="version", property="version", jdbcType=JdbcType.INTEGER),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    Account selectByPrimaryKey(Long id);

    @UpdateProvider(type=AccountSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Account record, @Param("example") AccountExample example);

    @UpdateProvider(type=AccountSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Account record, @Param("example") AccountExample example);

    @UpdateProvider(type=AccountSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Account record);

    @Update({
        "update account",
        "set channel = #{channel,jdbcType=VARCHAR},",
          "user_id = #{userId,jdbcType=BIGINT},",
          "kind = #{kind,jdbcType=VARCHAR},",
          "`name` = #{name,jdbcType=VARCHAR},",
          "is_item = #{isItem,jdbcType=TINYINT},",
          "balance = #{balance,jdbcType=BIGINT},",
          "version = #{version,jdbcType=INTEGER},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Account record);
}