package tech.qijin.cell.relation.db.model;

import java.util.Date;
import tech.qijin.cell.relation.base.RelationKind;
import tech.qijin.cell.relation.base.RelationStatus;
import tech.qijin.util4j.trace.pojo.Channel;

public class Relation {
    private Long id;

    private Channel channel;

    private Long userId;

    private Long peerUserId;

    private RelationKind kind;

    private String format;

    private RelationStatus status;

    private Integer version;

    private Date updateTime;

    private Date createTime;

    private Date relationTime;

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

    public Long getPeerUserId() {
        return peerUserId;
    }

    public void setPeerUserId(Long peerUserId) {
        this.peerUserId = peerUserId;
    }

    public RelationKind getKind() {
        return kind;
    }

    public void setKind(RelationKind kind) {
        this.kind = kind;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format == null ? null : format.trim();
    }

    public RelationStatus getStatus() {
        return status;
    }

    public void setStatus(RelationStatus status) {
        this.status = status;
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

    public Date getRelationTime() {
        return relationTime;
    }

    public void setRelationTime(Date relationTime) {
        this.relationTime = relationTime;
    }
}