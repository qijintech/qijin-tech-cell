package tech.qijin.cell.account.db.model;

import java.util.Date;
import tech.qijin.cell.account.base.DropsItemKind;
import tech.qijin.util4j.trace.pojo.Channel;

public class DropsItem {
    private Long id;

    private Channel channel;

    private String name;

    private DropsItemKind kind;

    private Long itemId;

    private Long dropsId;

    private Long amount;

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

    public DropsItemKind getKind() {
        return kind;
    }

    public void setKind(DropsItemKind kind) {
        this.kind = kind;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getDropsId() {
        return dropsId;
    }

    public void setDropsId(Long dropsId) {
        this.dropsId = dropsId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
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