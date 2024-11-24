package Community;

import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends UserH {
    private ArrayList<Group> createdGroups = new ArrayList<>();
    private ArrayList<UserH> users; // List of users to manage
    private Scanner sc = new Scanner(System.in);

    public Admin(String userId, String userName, String email, String password, ArrayList<UserH> users) {
        super(userId, userName, password, email);
        this.users = users; // Initialize the list of users
    }

    public void createGroup(String groupName, String description) {
        Group group = new Group(groupName, description);
        createdGroups.add(group);
        System.out.println("Group \"" + groupName + "\" created by Admin.");
    }

    public ArrayList<Group> getCreatedGroups() {
        return createdGroups;
    }

    public Group findGroupByName(String groupName) {
        for (Group group : createdGroups) {
            if (group.getGroupName().equalsIgnoreCase(groupName)) {
                return group;
            }
        }
        return null;
    }

    public void deleteGroup(Group group) {
        if (createdGroups.contains(group)) {
            createdGroups.remove(group);
            System.out.println("Group \"" + group.getGroupName() + "\" deleted by Admin.");
        } else {
            System.out.println("Group not found.");
        }
    }

    @Override
    public void viewProfile() {
        System.out.println("Admin Profile:");
        System.out.println("User ID: " + userId);
        System.out.println("Username: " + userName);
        System.out.println("Email: " + email);
    }

    @Override
    public void editProfile() {
        System.out.println("Editing Admin Profile...");
        System.out.print("Enter new username: ");
        this.userName = sc.nextLine();
        System.out.print("Enter new email: ");
        this.email = sc.nextLine();
        System.out.println("Profile updated.");
    }

    @Override
    public void joinGroup(Group group) {
        System.out.println("Admins cannot join groups.");
    }

    @Override
    public void leaveGroup(Group group) {
        System.out.println("Admins cannot leave groups.");
    }

    @Override
    public void postMessage(Group group, String content) {
        System.out.println("Admins cannot post messages in groups.");
    }

    @Override
    public void viewJoinedGroups() {
        System.out.println("Admins do not have joined groups.");
    }

    @Override
    public void viewGroupMessage(Group group) {
        group.displayMessages();
    }

    @Override
    public void manageUser() {
        System.out.println("Manage Users:");
        System.out.println("1: Show all users");
        System.out.println("2: Deactivate User");
        System.out.println("3: Reactivate User");
        System.out.println("4: Reset User Password");
        System.out.println("0: Exit");

        while (true) {
            System.out.print("Choose your option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    showUsers();
                    break;
                case 2:
                    System.out.print("Enter username to deactivate: ");
                    deactivateUser(sc.nextLine());
                    break;
                case 3:
                    System.out.print("Enter username to reactivate: ");
                    reactivateUser(sc.nextLine());
                    break;
                case 4:
                    System.out.print("Enter username: ");
                    String username = sc.nextLine();
                    System.out.print("Enter new password: ");
                    resetPassword(username, sc.nextLine());
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void showUsers() {
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            System.out.println("List of users:");
            for (UserH user : users) {
                System.out.println("Username: " + user.getUsername() + ", Email: " + user.getEmail());
            }
        }
    }

    private void deactivateUser(String username) {
        for (UserH user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                System.out.println("User \"" + username + "\" has been deactivated.");
                return;
            }
        }
        System.out.println("User not found.");
    }

    private void reactivateUser(String username) {
        for (UserH user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                System.out.println("User \"" + username + "\" has been reactivated.");
                return;
            }
        }
        System.out.println("User not found.");
    }

    private void resetPassword(String username, String newPassword) {
        for (UserH user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                user.setPassword(newPassword);
                System.out.println("Password for user \"" + username + "\" has been reset.");
                return;
            }
        }
        System.out.println("User not found.");
    }

    @Override
    public void logout() {
        System.out.println("Admin logged out.");
    }
}
