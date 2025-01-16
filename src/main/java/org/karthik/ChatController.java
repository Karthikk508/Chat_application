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
    private int nextGroupId = 0;

    public User getCurrentUser() {
        return this.currentUser;
    }

    public User getUserByUserName(String userName) {
        return usersByUserName.get(userName);
    }

    public User getUserByUserId(int id) {
        return usersById.get(id);
    }

    public boolean validateGroup(int groupId) {
        Group group = groups.get(groupId);
        return group != null;
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
        Group group = new Group(++nextGroupId, groupName, listOfUsers);
        groups.put(nextGroupId, group);
        currentUser.setGroups(group);

        for(User user : listOfUsers){
            user.setGroups(group);
        }
        System.out.println("Group created sucessfully !!!! ");
    }

    public void sendGroupMessage(int groupId,String message) {

        Group group = groups.get(groupId);
        if(group == null) {
            System.out.println("Group doesn't exist : !!!!!!");
            return;
        }
        group.setMessages(new Message(++nextMessageId, message,currentUser, (User)null, LocalDateTime.now(), false));
    }

    public void viewGroupChats(int groupId){

        Group group1 = currentUser.getGroups().stream().filter((group -> group.getGroupId() == groupId)).toList().getFirst();

        if(group1 == null){
            System.out.println("Group id doesn't exist");
        }
        else{

            List<Message> list =  group1.getMessages();
            list.sort((Message m1,Message m2)-> m1.getTimeStamp().compareTo(m2.getTimeStamp()));

            System.out.println("Group name : " + group1.getGroupName());

            for(Message m : group1.getMessages()){

                if(m.getSender().equals(currentUser)){
                    System.out.println("Time : " + m.getTimeStamp() + "       You : " + m.getContent());
                }
                else {
                    System.out.println("Time : " + m.getTimeStamp() + "       " +m.getSender().getUserName() + " : " + m.getContent());
                }
            }
        }


    }
}