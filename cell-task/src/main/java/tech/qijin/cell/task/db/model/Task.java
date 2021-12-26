package tech.qijin.cell.task.db.model;

import java.util.Date;
import tech.qijin.cell.task.base.RewardType;
import tech.qijin.cell.task.base.TaskKind;
import tech.qijin.util4j.trace.pojo.Channel;

public class Task {
    private Long id;

    private Channel channel;

    private String name;

    private TaskKind kind;

    private Long target;

    private String countingCode;

    private RewardType rewardType;

    private Long rewardId;

    private Boolean claimableWhenCreate;

    private Integer order;

    private String forwardText;

    private String forward;

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

    public TaskKind getKind() {
        return kind;
    }

    public void setKind(TaskKind kind) {
        this.kind = kind;
    }

    public Long getTarget() {
        return target;
    }

    public void setTarget(Long target) {
        this.target = target;
    }

    public String getCountingCode() {
        return countingCode;
    }

    public void setCountingCode(String countingCode) {
        this.countingCode = countingCode == null ? null : countingCode.trim();
    }

    public RewardType getRewardType() {
        return rewardType;
    }

    public void setRewardType(RewardType rewardType) {
        this.rewardType = rewardType;
    }

    public Long getRewardId() {
        return rewardId;
    }

    public void setRewardId(Long rewardId) {
        this.rewardId = rewardId;
    }

    public Boolean getClaimableWhenCreate() {
        return claimableWhenCreate;
    }

    public void setClaimableWhenCreate(Boolean claimableWhenCreate) {
        this.claimableWhenCreate = claimableWhenCreate;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getForwardText() {
        return forwardText;
    }

    public void setForwardText(String forwardText) {
        this.forwardText = forwardText == null ? null : forwardText.trim();
    }

    public String getForward() {
        return forward;
    }

    public void setForward(String forward) {
        this.forward = forward == null ? null : forward.trim();
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