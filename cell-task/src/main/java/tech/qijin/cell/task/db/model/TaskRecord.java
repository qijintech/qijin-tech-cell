package tech.qijin.cell.task.db.model;

import java.util.Date;
import tech.qijin.cell.task.base.TaskRecordStatus;
import tech.qijin.util4j.trace.pojo.Channel;

public class TaskRecord {
    private Long id;

    private Channel channel;

    private Long userId;

    private Long taskId;

    private String countingCode;

    private String taskFormat;

    private Long target;

    private Date startTime;

    private Date endTime;

    private TaskRecordStatus status;

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

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getCountingCode() {
        return countingCode;
    }

    public void setCountingCode(String countingCode) {
        this.countingCode = countingCode == null ? null : countingCode.trim();
    }

    public String getTaskFormat() {
        return taskFormat;
    }

    public void setTaskFormat(String taskFormat) {
        this.taskFormat = taskFormat == null ? null : taskFormat.trim();
    }

    public Long getTarget() {
        return target;
    }

    public void setTarget(Long target) {
        this.target = target;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public TaskRecordStatus getStatus() {
        return status;
    }

    public void setStatus(TaskRecordStatus status) {
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
}