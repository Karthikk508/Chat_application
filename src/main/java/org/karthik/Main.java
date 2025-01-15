package org.karthik;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<User> list = new ArrayList();
    private static ChatController controller = new ChatController();
    private static Scanner scanner;

    public static void main(String[] args) {
        registerUsers();
        boolean flag = true;

        while(flag) {
            System.out.println("1. Login\n2. Exit\n");
            int choice = scanner.nextInt();
            switch(choice) {
                case 1:
                    if (login()) {
                        System.out.println("Logged in successfully.");
                        mainMenu();
                    } else {
                        System.out.println("Login error !!!!!!!!!!!");
                    }
                    break;
                case 2:
                    flag = false;
                    break;
                default:
                    System.out.println("Invalid option ");
            }
        }

    }

    public static void registerUsers() {
        controller.registerUsers(10, "karthik", "1234");
        controller.registerUsers(15, "mohan", "6789");
        controller.registerUsers(11, "ajay", "5678");
        controller.registerUsers(12, "anitha", "2345");
        controller.registerUsers(13, "sneha", "3456");
        controller.registerUsers(14, "arjun", "4567");
        controller.registerUsers(16, "divya", "7890");
        controller.registerUsers(17, "vivek", "8901");
    }

    public static boolean login() {
        scanner.nextLine();
        System.out.print("Enter your userName : ");
        String userName = scanner.nextLine();
        System.out.print("Enter your password : ");
        String password = scanner.nextLine();
        return controller.validateLogin(userName, password);
    }

    public static void mainMenu() {
        boolean flag = true;

        while(flag) {
            System.out.println("Home :\n1. send message\n2. view chat history\n3. createGroup\n4. send group message\n9. logout");
            int choice = scanner.nextInt();
            switch(choice) {
                case 1:
                    sendMessage();
                    break;
                case 2:
                    viewChatHistory();
                    break;
                case 3:
                    createGroup();
                    break;
                case 4:
                    sendGroupMessage();
                    break;
                case 5:
                case 6:
                case 7:
                case 8:
                default:
                    System.out.println("Invalid option");
                    break;
                case 9:
                    flag = false;
            }
        }

    }

    private static void sendGroupMessage() {
        System.out.println("Enter the group id : ");
        int groupId = scanner.nextInt();
        String message = scanner.nextLine();
        controller.sendGroupMessage(groupId, message);
    }

    private static void sendMessage() {
        System.out.print("Enter the person id to whom you want to send message : ");
        int id = scanner.nextInt();
        System.out.print("Enter the message : ");
        scanner.nextLine();
        String message = scanner.nextLine();
        controller.sendMessage(id, message);
    }

    private static void viewChatHistory() {
        System.out.println("Enter the userId : ");
        int id = scanner.nextInt();
        controller.viewChatHistory(id);
    }

    private static void createGroup() {
        List<User> listOfUsers = new ArrayList();
        scanner.nextLine();
        System.out.print("Enter the group name : ");
        String groupName = scanner.nextLine();
        System.out.println();
        System.out.println("Enter the user id of the user to create group :");
        int id = scanner.nextInt();
        if (id >= 10 && id <= 17) {
            if (id != controller.getCurrentUser().getUserId()) {
                listOfUsers.add(controller.getUserByUserId(id));
            } else {
                System.out.println("Enter a valid user !!!!!");
            }
        } else {
            System.out.println("User not found !!!!!!!!!!!");
        }

        boolean flag = true;

        while(flag) {
            System.out.println("Add members :\n1 . Add more users to group\n2 . create");
            int choice = scanner.nextInt();
            switch(choice) {
                case 1:
                    addUsers(listOfUsers);
                    break;
                case 2:
                    flag = false;
                    break;
                default:
                    System.out.println("Invalid option ");
            }
        }

        controller.createGroup(groupName, listOfUsers);
    }

    private static void addUsers(List<User> listOfUsers) {
        System.out.println("Enter the user id of the user to create group :");
        int id = scanner.nextInt();
        if (id >= 10 && id <= 17) {
            if (!listOfUsers.contains(controller.getUserByUserId(id))) {
                listOfUsers.add(controller.getUserByUserId(id));
            } else {
                System.out.println("User already added ::: ");
            }
        } else {
            System.out.println("User not found !!!!!!!!!!!");
        }

    }

    static {
        scanner = new Scanner(System.in);
    }
}

