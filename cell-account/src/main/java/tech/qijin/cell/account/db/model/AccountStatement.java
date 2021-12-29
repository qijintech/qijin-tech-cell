package tech.qijin.cell.account.db.model;

import java.util.Date;
import tech.qijin.cell.account.base.AccountKind;
import tech.qijin.cell.account.base.StatementSrc;
import tech.qijin.util4j.trace.pojo.Channel;

public class AccountStatement {
    private Long id;

    private Channel channel;

    private Long userId;

    private AccountKind kind;

    private String format;

    private StatementSrc statementSrc;

    private Long dataId;

    private Long amount;

    private Date updateTime;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public AccountKind getKind() {
        return kind;
    }

    public void setKind(AccountKind kind) {
        this.kind = kind;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format == null ? null : format.trim();
    }

    public StatementSrc getStatementSrc() {
        return statementSrc;
    }

    public void setStatementSrc(StatementSrc statementSrc) {
        this.statementSrc = statementSrc;
    }

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}