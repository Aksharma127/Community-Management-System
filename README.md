# Community Management System

### **Project Overview**
This project implements a **Community Management System**, allowing users to register, log in, and manage groups. The system supports two types of users: **Admin** and **Member**. Each type has different functionalities, with the **Admin** having more control over group creation, deletion, and user management, while the **Member** can join groups, post messages, and view messages from the groups they belong to.

The system features a secure authentication mechanism with **password masking**, **role-based menus**, and **user input validation**. It is built using **Java** and follows object-oriented principles.

---

### **Project Features**

1. **User Authentication**:
   - Secure login for **Admin** and **Member** users.
   - Passwords are masked during input.
   - Role-based access control (Admin vs Member).
   
2. **Group Management**:
   - **Admins** can create and delete groups.
   - **Members** can join and leave groups, as well as post and view messages within groups.

3. **Error Handling**:
   - The system features exception handling to handle invalid inputs, empty fields, and incorrect login credentials.
   - Email validation during registration ensures proper format.

4. **Menus and Navigation**:
   - Dynamic menus based on user role (Admin or Member).
   - Clear prompts and messages guide the user through each option.
   
---

### **Project Structure**

- **Admin Class**:
  - Admin users can manage groups and users.
  - Can create and delete groups.
  
- **Member Class**:
  - Member users can join and leave groups, as well as post messages.
  
- **Group Class**:
  - Defines the group, including group name, description, members, and messages.
  
- **Message Class**:
  - Each message is associated with a specific group and member.

- **UserH Class**:
  - This is the base class for both Admin and Member, defining shared methods like viewing and editing profiles.

- **Main Class**:
  - Manages user input, authentication, role selection, and navigation to the correct menu (Admin or Member).
  
---

### **Technologies Used**

- **Java**: The project is implemented entirely in Java, using standard libraries like `java.util.ArrayList`, `java.util.Scanner`, and `java.util.regex.Pattern`.
- **Password Masking**: The console input uses `Console.readPassword()` to mask passwords during input.
  
---

### **Contributors**

This project was developed by a team of 6 members, each contributing to different parts of the system. Here's a breakdown of our individual contributions:

1. **Hitesh**: 
   - Worked on creating the **User** and **Admin** classes.
   - Helped define the structure for user and Admin role management.

2. **Abhay**:
   - Contributed to the **Admin** and **Member** class designs.

3. **Komal**:
   - Developed the **Message** and **Group** classes.
   - Implemented the functionality to allow members to post messages in groups.

4. **Arun**:
   - Worked on the **Message** and **Group** classes.
   - Developed the group management features (adding/removing members, displaying messages).

5. **Udit**:
   - Focused on connecting all the classes and ensuring smooth interaction between components.
   - Worked on the **Main** class, authentication, handling user input, routing, and error handling.
   - Assisted with user authentication and ensured proper login flow.

6. **Akshit**:
   - Implemented the **Main** class and authentication system.
   - Worked on exception handling, error detection, and validating user inputs (e.g., checking empty fields, and invalid emails).
   - Unit testing

---

### **Results and Output**

After successfully running the application, the user is greeted with the following options:

1. **Registration**:
   - Users can register by providing a username, password, and email. Upon successful registration, the system generates a unique user ID.

2. **Login**:
   - Users are prompted to enter their username and password.
   - After successful authentication, they are asked to select their role (Admin or Member).

3. **Admin Menu**:
   - Admins can view their profile, edit their profile, create and delete groups, and manage users (e.g., deactivate accounts).

4. **Member Menu**:
   - Members can view and edit their profiles, join or leave groups, post messages, and view messages from groups they belong to.

---

### **Sample Output**

**Login Screen:**
```text
WELCOME TO COMMUNITY MANAGEMENT SYSTEM
1. Register
2. Login
3. Exit
Choose an option: 2
Enter username: akshit
Enter password: ********   (password is hidden)
Select Role:
1. Admin
2. Member
Choose an option: 2

MEMBER MENU
1. View Profile
2. Edit Profile
3. Join Group
4. Leave Group
5. View Joined Groups
6. Post Message
7. View Group Messages
8. Logout
```

**Admin Menu:**
```text
ADMIN MENU
1. View Profile
2. Edit Profile
3. Create Group
4. Delete Group
5. Manage Users
6. Logout
Choose an option: 3
Enter group name: Java Developers
Enter group description: A group for Java enthusiasts.
```

---

### **Exception Handling and Validation**

1. **Invalid Login**:
   - If the user enters incorrect credentials, the system displays an error message:
   ```text
   Invalid username or password.
   ```

2. **Invalid Email**:
   - The system checks if the email follows a valid pattern during registration. If not, it throws an error:
   ```text
   Invalid email format.
   ```

3. **Empty Fields**:
   - When creating a user or during login, the system checks if any field is left empty and provides a helpful error message:
   ```text
   Error: Username cannot be empty.
   ```

4. **Invalid Option**:
   - If the user selects an invalid option from the menu, the system prompts them again:
   ```text
   Invalid option. Please try again.
   ```

---

### **How to Run the Project**

1. **Clone the repository**:
   - Clone this repository to your local machine using Git:
   ```bash
   git clone https://github.com/Aksharma127/Community-Management-System.git
   ```

2. **Compile the code**:
   - Open a terminal/command prompt and navigate to the project directory.
   - Run the following command to compile the Java files:
   ```bash
   javac *.java
   ```

3. **Run the project**:
   - After compiling, run the `Main` class to start the application:
   ```bash
   java Community.Main
   ```

---

### **Conclusion**

This **Community Management System** is a collaborative project designed to demonstrate the use of Java for building a console-based application with user authentication, group management, and exception handling. With contributions from all six team members, the project is a result of effective teamwork, leveraging various skills and tools for a successful implementation.

The system is flexible enough for extension, and future improvements could include:
- Adding **file persistence** to save user data and groups.
- Enhancing **security features**, such as encrypting passwords.
- Implementing a **Graphical User Interface (GUI)** for better user experience.

**Code is self-made with minor help from GPT, Gemini, Claude, Blackbox AIs** for debugging, ideas, and code completion. The project is a result of teamwork and collaboration among the six members of our group.
