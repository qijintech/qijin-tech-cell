package tech.qijin.cell.feed.db.model;

import java.util.Date;
import tech.qijin.util4j.trace.pojo.Channel;

public class FeedImage {
    private Long id;

    private Channel channel;

    private Long feedItemId;

    private String url;

    private Boolean valid;

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

    public Long getFeedItemId() {
        return feedItemId;
    }

    public void setFeedItemId(Long feedItemId) {
        this.feedItemId = feedItemId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
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