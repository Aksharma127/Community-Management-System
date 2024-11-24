package Community;

import java.util.ArrayList;
import java.util.Scanner;

public class Member extends UserH {
    private ArrayList<Group> joinedGroups = new ArrayList<>();

    public Member(String userId, String username, String password, String email) {
        super(userId, username, password, email);
    }

    @Override
    public void viewProfile() {
        System.out.println("Member Profile:");
        System.out.println("User ID: " + userId);
        System.out.println("Username: " + userName);
        System.out.println("Email: " + email);
    }

    @Override
    public void editProfile() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Editing Member Profile:");
            System.out.println("1: Change Username");
            System.out.println("2: Change Email");
            System.out.println("3: Change Password");
            System.out.println("0: Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter new username: ");
                    String newUsername = sc.nextLine();
                    setUsername(newUsername);
                    System.out.println("Username updated to: " + getUsername());
                    break;
                case 2:
                    System.out.print("Enter new email: ");
                    String newEmail = sc.nextLine();
                    setEmail(newEmail);
                    System.out.println("Email updated to: " + getEmail());
                    break;
                case 3:
                    System.out.print("Enter new password: ");
                    String newPassword = sc.nextLine();
                    setPassword(newPassword);
                    System.out.println("Password updated successfully.");
                    break;
                case 0:
                    System.out.println("Exiting profile editing.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    @Override
    public void joinGroup(Group group) {
        if (joinedGroups.contains(group)) {
            System.out.println("You are already a member of this group: " + group.getGroupName());
            return;
        }
        joinedGroups.add(group);
        group.addMember(this);
        System.out.println("Joined group: " + group.getGroupName());
    }

    @Override
    public void leaveGroup(Group group) {
        if (!joinedGroups.contains(group)) {
            System.out.println("You are not a member of this group: " + group.getGroupName());
            return;
        }
        joinedGroups.remove(group);
        group.removeMember(this);
        System.out.println("Left group: " + group.getGroupName());
    }

    @Override
    public void postMessage(Group group, String content) {
        if (!joinedGroups.contains(group)) {
            System.out.println("You are not a member of this group: " + group.getGroupName());
            return;
        }
        Message message = new Message("MSG-" + System.currentTimeMillis(), this, content);
        group.addMessage(message);
        System.out.println("Message posted successfully in group: " + group.getGroupName());
    }

    @Override
    public void viewJoinedGroups() {
        if (joinedGroups.isEmpty()) {
            System.out.println("You have not joined any groups yet.");
            return;
        }
        System.out.println("Joined Groups:");
        for (Group group : joinedGroups) {
            System.out.println(" - " + group.getGroupName());
        }
    }

    @Override
    public void viewGroupMessage(Group group) {
        if (!joinedGroups.contains(group)) {
            System.out.println("You are not a member of this group: " + group.getGroupName());
            return;
        }
        group.displayMessages();
    }

    @Override
    public void logout() {
        System.out.println("Member logged out.");
    }

    public ArrayList<Group> getJoinedGroups() {
        return joinedGroups;
    }

    @Override
    public void createGroup(String groupName, String description) {
        System.out.println("Members cannot create groups.");
    }

    @Override
    public void deleteGroup(Group group) {
        System.out.println("Members cannot delete groups.");
    }

    @Override
    public void manageUser() {
        System.out.println("Members cannot manage users.");
    }

    @Override
    public String toString() {
        return "Member [userId=" + userId + ", userName=" + userName + ", email=" + email + "]";
    }
}
