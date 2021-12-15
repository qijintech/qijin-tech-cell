package tech.qijin.cell.counting.db.model;

import java.util.Date;
import tech.qijin.cell.counting.base.CountingMode;
import tech.qijin.cell.counting.base.OnTargetMode;
import tech.qijin.util4j.trace.pojo.Channel;

public class CountingTemplate {
    private Long id;

    private Channel channel;

    private String name;

    private String code;

    private CountingMode mode;

    private String event;

    private Long target;

    private OnTargetMode onTargetMode;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public CountingMode getMode() {
        return mode;
    }

    public void setMode(CountingMode mode) {
        this.mode = mode;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event == null ? null : event.trim();
    }

    public Long getTarget() {
        return target;
    }

    public void setTarget(Long target) {
        this.target = target;
    }

    public OnTargetMode getOnTargetMode() {
        return onTargetMode;
    }

    public void setOnTargetMode(OnTargetMode onTargetMode) {
        this.onTargetMode = onTargetMode;
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