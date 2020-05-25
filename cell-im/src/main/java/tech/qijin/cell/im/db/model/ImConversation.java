package tech.qijin.cell.im.db.model;

import java.util.Date;

public class ImConversation {
    private Long id;

    private Long uid;

    private Long peerUid;

    private Long versionId;

    private Integer status;

    private String lastMsg;

    private Long lastClearMsg;

    private Date updateTime;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getPeerUid() {
        return peerUid;
    }

    public void setPeerUid(Long peerUid) {
        this.peerUid = peerUid;
    }

    public Long getVersionId() {
        return versionId;
    }

    public void setVersionId(Long versionId) {
        this.versionId = versionId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLastMsg() {
        return lastMsg;
    }

    public void setLastMsg(String lastMsg) {
        this.lastMsg = lastMsg == null ? null : lastMsg.trim();
    }

    public Long getLastClearMsg() {
        return lastClearMsg;
    }

    public void setLastClearMsg(Long lastClearMsg) {
        this.lastClearMsg = lastClearMsg;
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