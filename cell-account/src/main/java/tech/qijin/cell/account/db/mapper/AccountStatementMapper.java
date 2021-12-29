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
import tech.qijin.cell.account.db.model.AccountStatement;
import tech.qijin.cell.account.db.model.AccountStatementExample;

public interface AccountStatementMapper {
    @SelectProvider(type=AccountStatementSqlProvider.class, method="countByExample")
    long countByExample(AccountStatementExample example);

    @DeleteProvider(type=AccountStatementSqlProvider.class, method="deleteByExample")
    int deleteByExample(AccountStatementExample example);

    @Delete({
        "delete from account_statement",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into account_statement (channel, user_id, ",
        "kind, format, statement_src, ",
        "data_id, amount, update_time, ",
        "create_time)",
        "values (#{channel,jdbcType=VARCHAR}, #{userId,jdbcType=BIGINT}, ",
        "#{kind,jdbcType=VARCHAR}, #{format,jdbcType=VARCHAR}, #{statementSrc,jdbcType=VARCHAR}, ",
        "#{dataId,jdbcType=BIGINT}, #{amount,jdbcType=BIGINT}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{createTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(AccountStatement record);

    @InsertProvider(type=AccountStatementSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(AccountStatement record);

    @SelectProvider(type=AccountStatementSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="kind", property="kind", jdbcType=JdbcType.VARCHAR),
        @Result(column="format", property="format", jdbcType=JdbcType.VARCHAR),
        @Result(column="statement_src", property="statementSrc", jdbcType=JdbcType.VARCHAR),
        @Result(column="data_id", property="dataId", jdbcType=JdbcType.BIGINT),
        @Result(column="amount", property="amount", jdbcType=JdbcType.BIGINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<AccountStatement> selectByExample(AccountStatementExample example);

    @Select({
        "select",
        "id, channel, user_id, kind, format, statement_src, data_id, amount, update_time, ",
        "create_time",
        "from account_statement",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="kind", property="kind", jdbcType=JdbcType.VARCHAR),
        @Result(column="format", property="format", jdbcType=JdbcType.VARCHAR),
        @Result(column="statement_src", property="statementSrc", jdbcType=JdbcType.VARCHAR),
        @Result(column="data_id", property="dataId", jdbcType=JdbcType.BIGINT),
        @Result(column="amount", property="amount", jdbcType=JdbcType.BIGINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    AccountStatement selectByPrimaryKey(Long id);

    @UpdateProvider(type=AccountStatementSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") AccountStatement record, @Param("example") AccountStatementExample example);

    @UpdateProvider(type=AccountStatementSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") AccountStatement record, @Param("example") AccountStatementExample example);

    @UpdateProvider(type=AccountStatementSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AccountStatement record);

    @Update({
        "update account_statement",
        "set channel = #{channel,jdbcType=VARCHAR},",
          "user_id = #{userId,jdbcType=BIGINT},",
          "kind = #{kind,jdbcType=VARCHAR},",
          "format = #{format,jdbcType=VARCHAR},",
          "statement_src = #{statementSrc,jdbcType=VARCHAR},",
          "data_id = #{dataId,jdbcType=BIGINT},",
          "amount = #{amount,jdbcType=BIGINT},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(AccountStatement record);
}