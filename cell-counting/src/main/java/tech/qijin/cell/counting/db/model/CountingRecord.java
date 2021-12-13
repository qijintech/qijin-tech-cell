package tech.qijin.cell.counting.db.model;

import java.util.Date;
import tech.qijin.util4j.trace.pojo.Channel;

public class CountingRecord {
    private Long id;

    private Channel channel;

    private Long userId;

    private String countingCode;

    private String countingFormat;

    private Long curr;

    private Long target;

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

    public String getCountingCode() {
        return countingCode;
    }

    public void setCountingCode(String countingCode) {
        this.countingCode = countingCode == null ? null : countingCode.trim();
    }

    public String getCountingFormat() {
        return countingFormat;
    }

    public void setCountingFormat(String countingFormat) {
        this.countingFormat = countingFormat == null ? null : countingFormat.trim();
    }

    public Long getCurr() {
        return curr;
    }

    public void setCurr(Long curr) {
        this.curr = curr;
    }

    public Long getTarget() {
        return target;
    }

    public void setTarget(Long target) {
        this.target = target;
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