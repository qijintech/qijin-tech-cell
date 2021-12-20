package tech.qijin.cell.message.db.model;

import java.util.Date;
import tech.qijin.cell.message.base.MessageDropsStatus;
import tech.qijin.util4j.trace.pojo.Channel;

public class MessageDrops {
    private Long id;

    private Channel channel;

    private Long messageId;

    private Long dropsId;

    private String statementSrc;

    private Long dataId;

    private MessageDropsStatus status;

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

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getDropsId() {
        return dropsId;
    }

    public void setDropsId(Long dropsId) {
        this.dropsId = dropsId;
    }

    public String getStatementSrc() {
        return statementSrc;
    }

    public void setStatementSrc(String statementSrc) {
        this.statementSrc = statementSrc == null ? null : statementSrc.trim();
    }

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public MessageDropsStatus getStatus() {
        return status;
    }

    public void setStatus(MessageDropsStatus status) {
        this.status = status;
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