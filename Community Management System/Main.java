package Community;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    // List of all registered users and groups
    static ArrayList<UserH> users = new ArrayList<>();
    static ArrayList<Group> allGroups = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Default admin to get started
        Admin defaultAdmin = new Admin("ADM-001", "Admin", hashPassword("adminpass"), "admin@example.com", users);
        users.add(defaultAdmin);

        // Main menu for the system
        while (true) {
            System.out.println("\nWELCOME TO COMMUNITY MANAGEMENT SYSTEM");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Read input

            if (choice == 1) {
                registerUser(sc);
            } else if (choice == 2) {
                loginUser(sc);
            } else if (choice == 3) {
                System.out.println("Goodbye!");
                sc.close();
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void registerUser(Scanner sc) {
        try {
            // Collect registration details
            System.out.print("Enter username: ");
            String username = sc.nextLine();
            if (username.isEmpty()) throw new IllegalArgumentException("Username cannot be empty.");

            System.out.print("Enter password: ");
            String password = sc.nextLine();
            if (password.isEmpty()) throw new IllegalArgumentException("Password cannot be empty.");

            System.out.print("Enter email: ");
            String email = sc.nextLine();
            if (!isValidEmail(email)) throw new IllegalArgumentException("Invalid email format.");

            // Ask the user to select a role
            System.out.println("Select Role:");
            System.out.println("1. Admin");
            System.out.println("2. Member");
            System.out.print("Choose an option: ");
            int roleChoice = sc.nextInt();
            sc.nextLine(); // Read input

            String userId = "USR-" + System.currentTimeMillis();
            String hashedPassword = hashPassword(password);
            UserH newUser;

            // Register as Admin or Member based on the choice
            if (roleChoice == 1) {
                newUser = new Admin(userId, username, hashedPassword, email, users);
                System.out.println("Admin registered successfully with ID: " + userId);
                adminMenu((Admin) newUser, sc); // Direct to Admin menu
            } else if (roleChoice == 2) {
                newUser = new Member(userId, username, hashedPassword, email);
                System.out.println("Member registered successfully with ID: " + userId);
                memberMenu((Member) newUser, sc); // Direct to Member menu
            } else {
                System.out.println("Invalid role. Registration aborted.");
                return;
            }

            users.add(newUser); // Add the new user to the list
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void loginUser(Scanner sc) {
        try {
            // Ask for login credentials
            System.out.print("Enter username: ");
            String username = sc.nextLine();

            System.out.print("Enter password: ");
            String password = sc.nextLine();

            if (username.isEmpty() || password.isEmpty()) {
                throw new IllegalArgumentException("Username or password cannot be empty.");
            }

            String hashedPassword = hashPassword(password);
            UserH loggedInUser = null;

            // Check the credentials in the user list
            for (UserH user : users) {
                if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(hashedPassword)) {
                    loggedInUser = user;
                    break;
                }
            }

            if (loggedInUser == null) {
                System.out.println("Invalid username or password.");
                return;
            }

            System.out.println("Login successful!");

            // Redirect to the correct menu based on role
            if (loggedInUser instanceof Admin) {
                adminMenu((Admin) loggedInUser, sc);
            } else if (loggedInUser instanceof Member) {
                memberMenu((Member) loggedInUser, sc);
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void adminMenu(Admin admin, Scanner sc) {
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

            if (choice == 1) {
                admin.viewProfile();
            } else if (choice == 2) {
                admin.editProfile();
            } else if (choice == 3) {
                System.out.print("Enter group name: ");
                String groupName = sc.nextLine();
                System.out.print("Enter group description: ");
                String description = sc.nextLine();
                admin.createGroup(groupName, description);
                allGroups.add(new Group(groupName, description));
                System.out.println("Group created successfully.");
            } else if (choice == 4) {
                System.out.print("Enter group name to delete: ");
                String groupName = sc.nextLine();
                admin.deleteGroup(findGroupByName(groupName));
            } else if (choice == 5) {
                admin.manageUser();
            } else if (choice == 6) {
                System.out.println("Logged out.");
                break;
            } else {
                System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void memberMenu(Member member, Scanner sc) {
        while (true) {
            System.out.println("\nMEMBER MENU");
            System.out.println("1. View Profile");
            System.out.println("2. Edit Profile");
            System.out.println("3. Join Group");
            System.out.println("4. Leave Group");
            System.out.println("5. View Joined Groups");
            System.out.println("6. Logout");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                member.viewProfile();
            } else if (choice == 2) {
                member.editProfile();
            } else if (choice == 3) {
                System.out.print("Enter group name to join: ");
                String groupName = sc.nextLine();
                member.joinGroup(findGroupByName(groupName));
            } else if (choice == 4) {
                System.out.print("Enter group name to leave: ");
                String groupName = sc.nextLine();
                member.leaveGroup(findGroupByName(groupName));
            } else if (choice == 5) {
                member.viewJoinedGroups();
            } else if (choice == 6) {
                System.out.println("Logged out.");
                break;
            } else {
                System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static Group findGroupByName(String groupName) {
        for (Group group : allGroups) {
            if (group.getGroupName().equalsIgnoreCase(groupName)) {
                return group;
            }
        }
        System.out.println("Group not found.");
        return null;
    }

    private static boolean isValidEmail(String email) {
        return Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", email);
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            return password;
        }
    }
}
