package tech.qijin.cell.message.db.model;

import java.util.Date;
import tech.qijin.cell.message.base.MessageKind;
import tech.qijin.cell.message.base.MessageStatus;
import tech.qijin.util4j.trace.pojo.Channel;

public class Message {
    private Long id;

    private Channel channel;

    private Long userId;

    private MessageKind kind;

    private String brief;

    private MessageStatus status;

    private Boolean read;

    private Boolean hasDrops;

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

    public MessageKind getKind() {
        return kind;
    }

    public void setKind(MessageKind kind) {
        this.kind = kind;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief == null ? null : brief.trim();
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public Boolean getHasDrops() {
        return hasDrops;
    }

    public void setHasDrops(Boolean hasDrops) {
        this.hasDrops = hasDrops;
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