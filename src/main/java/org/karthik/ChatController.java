package org.karthik;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ChatController {
    private Map<Integer, User> usersById = new HashMap();
    private Map<String, User> usersByUserName = new HashMap();
    private Map<Integer, Group> groups = new HashMap();
    private User currentUser;
    private int nextMessageId = 0;
    private int nextGroupId = 1;

    public User getCurrentUser() {
        return this.currentUser;
    }

    public void registerUsers(int userId, String userName, String password) {
        User user = new User(userId, userName, password);
        this.usersById.put(userId, user);
        this.usersByUserName.put(userName, user);
    }

    public boolean validateLogin(String userName, String password) {
        if (this.usersByUserName.containsKey(userName)) {
            this.currentUser = (User)this.usersByUserName.get(userName);
            if (this.currentUser.getPassword().equals(password)) {
                return true;
            }

            System.out.println("Incorrect credentials !!!!!!!!!!!!");
        }

        System.out.println("User not found !!!!!!!!!!!!");
        return false;
    }

    public void sendMessage(int id, String message) {
        User receiver = (User)this.usersById.get(id);
        Message message1 = new Message(++this.nextMessageId, message, this.currentUser, receiver, LocalDateTime.now(), false);
        this.currentUser.setSentMessages(message1);
        receiver.setReceivedMessages(message1);
    }

    public User getUserByUserName(String userName) {
        return (User)this.usersByUserName.get(userName);
    }

    public User getUserByUserId(int id) {
        return (User)this.usersById.get(id);
    }

    public void viewChatHistory(int id) {
        User user = this.getUserByUserId(id);
        System.out.println(user.getUserName());
        List<Message> list = new ArrayList(this.currentUser.getReceivedMessages().stream().filter((m1) -> {
            return m1.getSender().equals(user);
        }).toList());
        list.addAll(this.currentUser.getSentMessages().stream().filter((mx) -> {
            return mx.getReceiver().equals(user);
        }).toList());
        list.sort((m1, m2) -> {
            return m1.getTimeStamp().compareTo(m2.getTimeStamp());
        });
        Iterator var4 = list.iterator();

        while(var4.hasNext()) {
            Message m = (Message)var4.next();
            PrintStream var10000 = System.out;
            String var10001 = String.valueOf(m.getTimeStamp());
            var10000.println(var10001 + "        " + m.getContent());
        }

    }

    public void createGroup(String groupName, List<User> listOfUsers) {
        Group group = new Group(++this.nextGroupId, groupName, listOfUsers);
        this.groups.put(this.nextGroupId, group);
        System.out.println("Group created sucessfully !!!! ");
    }

    public void sendGroupMessage(int groupId, String message) {
        Group group = (Group)this.groups.get(groupId);
        group.setMessages(new Message(++this.nextMessageId, message, this.currentUser, (User)null, LocalDateTime.now(), false));
    }
}