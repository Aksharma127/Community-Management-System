package Community;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class UserService {
    private HashMap<String, User> userDatabase;

    public UserService() {
        userDatabase = new HashMap<>();
    }

    public HashMap<String, User> getUsers() {
        return userDatabase;
    }

    public boolean registerUser(String username, String password, String email) {
        if (userDatabase.containsKey(username)) {
            System.out.println("Username already exists.");
            return false;
        }

        String hashedPassword = hashPassword(password);
        User user = new User(username, hashedPassword, email);
        userDatabase.put(username, user);
        System.out.println("User registered successfully.");
        return true;
    }

    public boolean loginUser(String username, String password) {
        if (!userDatabase.containsKey(username)) {
            System.out.println("User not found.");
            return false;
        }

        User user = userDatabase.get(username);
        String hashedPassword = hashPassword(password);

        if (user.getPassword().equals(hashedPassword)) {
            System.out.println("Login successful.");
            return true;
        } else {
            System.out.println("Incorrect password.");
            return false;
        }
    }

    private String hashPassword(String password) {
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
            System.err.println("Error hashing password: " + e.getMessage());
            return password; // Fallback to plain password (not recommended in real applications)
        }
    }
}
