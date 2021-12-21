package tech.qijin.cell.account.db.model;

import java.util.Date;
import tech.qijin.cell.account.base.ItemKind;
import tech.qijin.cell.account.base.ItemStatus;
import tech.qijin.util4j.trace.pojo.Channel;

public class Item {
    private Long id;

    private Channel channel;

    private String name;

    private ItemKind kind;

    private ItemStatus status;

    private Boolean stackable;

    private Boolean autoUse;

    private Date expiredTime;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public ItemKind getKind() {
        return kind;
    }

    public void setKind(ItemKind kind) {
        this.kind = kind;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }

    public Boolean getStackable() {
        return stackable;
    }

    public void setStackable(Boolean stackable) {
        this.stackable = stackable;
    }

    public Boolean getAutoUse() {
        return autoUse;
    }

    public void setAutoUse(Boolean autoUse) {
        this.autoUse = autoUse;
    }

    public Date getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
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