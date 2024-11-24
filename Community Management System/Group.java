package Community;

import java.util.ArrayList;
import java.util.UUID;

public class Group {
    private String groupId;
    private String groupName;
    private String description;
    private ArrayList<Member> members = new ArrayList<>();
    private ArrayList<Message> messages = new ArrayList<>();

    public Group(String groupName, String description) {
        this.groupId = generateGroupId();
        this.groupName = groupName;
        this.description = description;
    }

    private String generateGroupId() {
        return "GRP-" + UUID.randomUUID().toString();
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public void removeMember(Member member) {
        members.remove(member);
    }

    public void addMessage(Message message) {
        messages.add(message);
        System.out.println("Message added successfully: " + message);
    }

    public void displayGroupDetails() {
        System.out.println("Group: " + groupName + " | Description: " + description);
        System.out.println("Members:");
        for (Member member : members) {
            System.out.println(" - " + member.getUsername());
        }
    }

    public void displayMessages() {
        System.out.println("Messages in " + groupName + ":");
        for (Message message : messages) {
            message.displayMessages();
        }
    }

    public String getGroupName() {
        return groupName;
    }
}
