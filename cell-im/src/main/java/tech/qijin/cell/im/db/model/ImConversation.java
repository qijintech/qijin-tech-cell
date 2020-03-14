package tech.qijin.cell.im.db.model;

import java.util.Date;

public class ImConversation {
    private Long id;

    private Long ownerId;

    private Long peerId;

    private Long lastMsgid;

    private Long lastDelMsgid;

    private Date createTime;

    private Date updateTime;

    private Long versionId;

    private Byte delStatus;

    private Long sortId;

    private byte[] lastMsg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getPeerId() {
        return peerId;
    }

    public void setPeerId(Long peerId) {
        this.peerId = peerId;
    }

    public Long getLastMsgid() {
        return lastMsgid;
    }

    public void setLastMsgid(Long lastMsgid) {
        this.lastMsgid = lastMsgid;
    }

    public Long getLastDelMsgid() {
        return lastDelMsgid;
    }

    public void setLastDelMsgid(Long lastDelMsgid) {
        this.lastDelMsgid = lastDelMsgid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getVersionId() {
        return versionId;
    }

    public void setVersionId(Long versionId) {
        this.versionId = versionId;
    }

    public Byte getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Byte delStatus) {
        this.delStatus = delStatus;
    }

    public Long getSortId() {
        return sortId;
    }

    public void setSortId(Long sortId) {
        this.sortId = sortId;
    }

    public byte[] getLastMsg() {
        return lastMsg;
    }

    public void setLastMsg(byte[] lastMsg) {
        this.lastMsg = lastMsg;
    }
}