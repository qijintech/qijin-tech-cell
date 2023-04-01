package tech.qijin.cell.user.db.model;

import java.util.Date;
import tech.qijin.util4j.trace.pojo.Channel;

public class Item {
    private Long id;

    private Channel channel;

    private String name;

    private String kind;

    private String status;

    private Byte stackable;

    private Byte autoUse;

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

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind == null ? null : kind.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Byte getStackable() {
        return stackable;
    }

    public void setStackable(Byte stackable) {
        this.stackable = stackable;
    }

    public Byte getAutoUse() {
        return autoUse;
    }

    public void setAutoUse(Byte autoUse) {
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