package Community;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    static ArrayList<Group> allGroups = new ArrayList<>(); // Store all groups globally

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<UserH> users = new ArrayList<>();

        // Add default admin
        Admin admin = new Admin("ADM-001", "Admin", "admin@example.com", "adminpass", users);
        users.add(admin);

        while (true) {
            try {
                System.out.println("\nWELCOME TO COMMUNITY MANAGEMENT SYSTEM");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");
                int choice = sc.nextInt();
                sc.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        registerUser(sc, users);
                        break;

                    case 2:
                        loginUser(sc, users);
                        break;

                    case 3:
                        System.out.println("Exiting...");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                sc.nextLine(); // Clear invalid input
            }
        }
    }

    // Register User
    private static void registerUser(Scanner sc, ArrayList<UserH> users) {
        try {
            System.out.print("Enter username: ");
            String regUsername = sc.nextLine();
            if (regUsername.isEmpty()) throw new IllegalArgumentException("Username cannot be empty.");

            System.out.print("Enter password: ");
            String regPassword = sc.nextLine();
            if (regPassword.isEmpty()) throw new IllegalArgumentException("Password cannot be empty.");

            System.out.print("Enter email: ");
            String regEmail = sc.nextLine();
            if (!isValidEmail(regEmail)) throw new IllegalArgumentException("Invalid email format.");

            String userId = "USR-" + System.currentTimeMillis();
            users.add(new Member(userId, regUsername, regPassword, regEmail));
            System.out.println("User registered successfully with ID: " + userId);

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Login User
    private static void loginUser(Scanner sc, ArrayList<UserH> users) {
        try {
            System.out.print("Enter username: ");
            String logUsername = sc.nextLine();

            System.out.print("Enter password: ");
            String logPassword = sc.nextLine();

            if (logUsername.isEmpty() || logPassword.isEmpty()) {
                throw new IllegalArgumentException("Username or Password cannot be empty.");
            }

            UserH loggedInUser = null;
            for (UserH user : users) {
                if (user.getUsername().equals(logUsername) && user.getPassword().equals(logPassword)) {
                    loggedInUser = user;
                    break;
                }
            }

            if (loggedInUser == null) {
                System.out.println("Invalid username or password.");
                return;
            }

            // Ask for role after login
            System.out.println("\nSelect Role:");
            System.out.println("1. Admin");
            System.out.println("2. Member");
            System.out.print("Choose an option: ");
            int roleChoice = sc.nextInt();
            sc.nextLine(); // Consume newline

            if (roleChoice == 1 && loggedInUser instanceof Admin) {
                adminMenu((Admin) loggedInUser, sc);
            } else if (roleChoice == 2 && loggedInUser instanceof Member) {
                memberMenu((Member) loggedInUser, sc);
            } else {
                System.out.println("Invalid role selection. Please try again.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Admin Menu
    private static void adminMenu(Admin admin, Scanner sc) {
        try {
            while (true) {
                System.out.println("\nADMIN MENU");
                System.out.println("1. View Profile");
                System.out.println("2. Edit Profile");
                System.out.println("3. Create Group");
                System.out.println("4. Delete Group");
                System.out.println("5. Manage Users");
                System.out.println("6. Logout");
                System.out.print("Choose an option: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        admin.viewProfile();
                        break;
                    case 2:
                        admin.editProfile();
                        break;
                    case 3:
                        System.out.print("Enter group name: ");
                        String groupName = sc.nextLine();
                        System.out.print("Enter group description: ");
                        String groupDesc = sc.nextLine();
                        Group newGroup = new Group(groupName, groupDesc);
                        admin.createGroup(groupName, groupDesc);
                        allGroups.add(newGroup); // Add to global group list
                        break;
                    case 4:
                        System.out.print("Enter group name to delete: ");
                        String groupToDeleteName = sc.nextLine();
                        Group groupToDelete = findGroupByName(allGroups, groupToDeleteName);
                        if (groupToDelete != null) {
                            admin.deleteGroup(groupToDelete);
                            allGroups.remove(groupToDelete); // Remove from global group list
                        } else {
                            System.out.println("Group not found.");
                        }
                        break;
                    case 5:
                        admin.manageUser();
                        break;
                    case 6:
                        admin.logout();
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            sc.nextLine(); // Clear invalid input
        }
    }

    // Member Menu
    private static void memberMenu(Member member, Scanner sc) {
        try {
            while (true) {
                System.out.println("\nMEMBER MENU");
                System.out.println("1. View Profile");
                System.out.println("2. Edit Profile");
                System.out.println("3. Join Group");
                System.out.println("4. Leave Group");
                System.out.println("5. View Joined Groups");
                System.out.println("6. Post Message");
                System.out.println("7. View Group Messages");
                System.out.println("8. Logout");
                System.out.print("Choose an option: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        member.viewProfile();
                        break;

                    case 2:
                        member.editProfile();
                        break;

                    case 3:
                        System.out.print("Enter group name to join: ");
                        String groupNameToJoin = sc.nextLine();
                        Group groupToJoin = findGroupByName(allGroups, groupNameToJoin);
                        if (groupToJoin != null) {
                            member.joinGroup(groupToJoin);
                        } else {
                            System.out.println("Group not found.");
                        }
                        break;

                    case 4:
                        System.out.print("Enter group name to leave: ");
                        String groupNameToLeave = sc.nextLine();
                        Group groupToLeave = findGroupByName(member.getJoinedGroups(), groupNameToLeave);
                        if (groupToLeave != null) {
                            member.leaveGroup(groupToLeave);
                        } else {
                            System.out.println("You are not a member of this group.");
                        }
                        break;

                    case 5:
                        member.viewJoinedGroups();
                        break;

                    case 6:
                        System.out.print("Enter group name to post message: ");
                        String groupNameToPost = sc.nextLine();
                        Group groupToPost = findGroupByName(member.getJoinedGroups(), groupNameToPost);
                        if (groupToPost != null) {
                            System.out.print("Enter message content: ");
                            String messageContent = sc.nextLine();
                            member.postMessage(groupToPost, messageContent);
                        } else {
                            System.out.println("You are not a member of this group.");
                        }
                        break;

                    case 7:
                        System.out.print("Enter group name to view messages: ");
                        String groupNameToView = sc.nextLine();
                        Group groupToView = findGroupByName(member.getJoinedGroups(), groupNameToView);
                        if (groupToView != null) {
                            groupToView.displayMessages();
                        } else {
                            System.out.println("You are not a member of this group.");
                        }
                        break;

                    case 8:
                        member.logout();
                        return;

                    default:
                        System.out.println("Invalid option.");
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            sc.nextLine(); // Clear invalid input
        }
    }

    // Utility method to find a group by name
    private static Group findGroupByName(ArrayList<Group> groups, String groupName) {
        for (Group group : groups) {
            if (group.getGroupName().equalsIgnoreCase(groupName)) {
                return group;
            }
        }
        return null;
    }

    // Email validation method
    private static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.matches(emailRegex, email);
    }
}
