package com.binarybricks.pragya.youplus.datamodel;

/**
 * this is a data model class representing the conversation data.
 * Created by PRAGYA on 8/2/2016.
 */
public class ConversationData {

    private Long friendId;
    private String friendName;
    private String lastMessage;
    private String lastMessageReceivedTime;
    private String avatarImageURL;
    private Integer unreadCount;
    private boolean isLoading;

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getLastMessageReceivedTime() {
        return lastMessageReceivedTime;
    }

    public void setLastMessageReceivedTime(String lastMessageReceivedTime) {
        this.lastMessageReceivedTime = lastMessageReceivedTime;
    }

    public String getAvatarImageURL() {
        return avatarImageURL;
    }

    public void setAvatarImageURL(String avatarImageURL) {
        this.avatarImageURL = avatarImageURL;
    }

    public Integer getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(Integer unreadCount) {
        this.unreadCount = unreadCount;
    }
}
