package data.model;

import java.util.HashMap;
import java.util.Map;

public class User {
    public String id;
    public Map<String, Boolean> messages = new HashMap<>();
    public Map<String, Boolean> chatRooms = new HashMap<>();
    public Map<String, Boolean> drawings = new HashMap<>();
    public User() {}
    public User(String id, Map<String, Boolean> messages, Map<String, Boolean> chatRooms,
                Map<String, Boolean> drawings) {
        this.id = id;
        this.messages = messages;
        this.chatRooms = chatRooms;
        this.drawings = drawings;
    }
}
