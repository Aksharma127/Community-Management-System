package Community;

import java.time.LocalDateTime;

public class Message {
    private String messageId;
    private UserH author;
    private String content;
    private LocalDateTime timestamp;

    public Message(String messageId, UserH author, String content) {
        this.messageId = messageId;
        this.author = author;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }

    public String getMessageId() {
        return messageId;
    }

    public UserH getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void displayMessages() {
        System.out.println("Message ID: " + messageId);
        System.out.println("Author: " + author.getUsername());
        System.out.println("Content: " + content);
        System.out.println("Timestamp: " + timestamp);
    }
}
