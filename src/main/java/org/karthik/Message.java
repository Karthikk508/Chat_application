package org.karthik;

import java.time.LocalDateTime;

public class Message {
    private int messageId;
    private String content;
    private User sender;
    private User receiver;
    private LocalDateTime timeStamp;
    private boolean isDeleted;

    public Message(int messageId, String content, User sender, User receiver, LocalDateTime timeStamp, boolean isDeleted) {
        this.messageId = messageId;
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
        this.timeStamp = timeStamp;
        this.isDeleted = isDeleted;
    }

    public int getMessageId() {
        return this.messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getSender() {
        return this.sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return this.receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public LocalDateTime getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public boolean isDeleted() {
        return this.isDeleted;
    }

    public void setDeleted(boolean deleted) {
        this.isDeleted = deleted;
    }

    public String toString() {
        int var10000 = this.messageId;
        return "Message{messageId=" + var10000 + ", content='" + this.content + "', sender=" + String.valueOf(this.sender) + ", receiver=" + String.valueOf(this.receiver) + ", timeStamp=" + String.valueOf(this.timeStamp) + ", isDeleted=" + this.isDeleted + "}";
    }
}