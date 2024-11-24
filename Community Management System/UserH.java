package Community;

public abstract class UserH {
    protected String userId;
    protected String userName;
    protected String password;
    protected String email;

    public UserH(String userId, String userName, String password, String email) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Abstract methods for general functionality
    public abstract void viewProfile();

    public abstract void editProfile();

    public abstract void joinGroup(Group group);

    public abstract void leaveGroup(Group group);

    public abstract void postMessage(Group group, String content);

    public abstract void viewJoinedGroups();

    public abstract void viewGroupMessage(Group group);

    public abstract void logout();

    // Admin-specific methods
    public abstract void createGroup(String groupName, String description);

    public abstract void deleteGroup(Group group);

    public abstract void manageUser();
}
