package tech.qijin.cell.account.db.model;

import java.util.Date;
import tech.qijin.cell.account.base.AccountKind;
import tech.qijin.util4j.trace.pojo.Channel;

public class Account {
    private Long id;

    private Channel channel;

    private Long userId;

    private AccountKind kind;

    private String name;

    private Boolean isItem;

    private Long balance;

    private Integer version;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Boolean getIsItem() {
        return isItem;
    }

    public void setIsItem(Boolean isItem) {
        this.isItem = isItem;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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