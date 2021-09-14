package tech.qijin.cell.iam.db.model;

import java.util.Date;
import tech.qijin.cell.iam.base.IamAuth;
import tech.qijin.cell.iam.base.IamRole;
import tech.qijin.util4j.trace.pojo.Channel;

public class RoleAuth {
    private Long id;

    private Channel channel;

    private IamRole role;

    private IamAuth auth;

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

    public IamRole getRole() {
        return role;
    }

    public void setRole(IamRole role) {
        this.role = role;
    }

    public IamAuth getAuth() {
        return auth;
    }

    public void setAuth(IamAuth auth) {
        this.auth = auth;
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