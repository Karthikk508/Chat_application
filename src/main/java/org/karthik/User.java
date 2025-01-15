package org.karthik;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int userId;
    private String userName;
    private String password;
    private List<Message> sentMessages;
    private List<Message> receivedMessages;
    private List<Group> groups;

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Message> getSentMessages() {
        return this.sentMessages;
    }

    public void setSentMessages(Message sentMessages) {
        this.sentMessages.add(sentMessages);
    }

    public List<Message> getReceivedMessages() {
        return this.receivedMessages;
    }

    public void setReceivedMessages(Message receivedMessage) {
        this.receivedMessages.add(receivedMessage);
    }

    public List<Group> getGroups() {
        return this.groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public User(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.sentMessages = new ArrayList();
        this.receivedMessages = new ArrayList();
        this.groups = new ArrayList();
    }
}