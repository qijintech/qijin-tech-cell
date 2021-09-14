package tech.qijin.cell.iam.db.model;

import java.util.Date;
import tech.qijin.cell.iam.base.IamRole;
import tech.qijin.util4j.trace.pojo.Channel;

public class UserRole {
    private Long id;

    private Channel channel;

    private Long userId;

    private IamRole role;

    private Boolean valid;

    private Date createTime;

    private Date updateTime;

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

    public IamRole getRole() {
        return role;
    }

    public void setRole(IamRole role) {
        this.role = role;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
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
}