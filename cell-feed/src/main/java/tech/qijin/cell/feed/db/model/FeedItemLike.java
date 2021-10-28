package tech.qijin.cell.feed.db.model;

import java.util.Date;
import tech.qijin.util4j.trace.pojo.Channel;

public class FeedItemLike {
    private Integer id;

    private Channel channel;

    private Long feedItemId;

    private Long userId;

    private Boolean valid;

    private Date updateTime;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Long getFeedItemId() {
        return feedItemId;
    }

    public void setFeedItemId(Long feedItemId) {
        this.feedItemId = feedItemId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
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