package tech.qijin.cell.im.db.model;

import java.util.Date;

public class ImMessageContent {
    private Long id;

    private Long msgId;

    private Byte status;

    private Byte type;

    private Byte withoutSender;

    private Byte withoutRecipient;

    private Date createTime;

    private byte[] content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getWithoutSender() {
        return withoutSender;
    }

    public void setWithoutSender(Byte withoutSender) {
        this.withoutSender = withoutSender;
    }

    public Byte getWithoutRecipient() {
        return withoutRecipient;
    }

    public void setWithoutRecipient(Byte withoutRecipient) {
        this.withoutRecipient = withoutRecipient;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}