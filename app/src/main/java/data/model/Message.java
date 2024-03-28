package data.model;

import com.google.firebase.database.PropertyName;

import java.time.Instant;
import java.util.Map;

public class Message {
    public String content;
    public String fromId;
    public String toId;
    public Map<String, Boolean> drawings;
    private final long time;
    public Message(){
        this.time = Instant.now().toEpochMilli();
    }
    public Message(String content, String fromId, String toId, long time) {
        this.content = content;
        this.fromId = fromId;
        this.toId = toId;
        this.time = time;
    }
    @PropertyName("time")
    public long getTime() {
        return this.time;
    }
}
