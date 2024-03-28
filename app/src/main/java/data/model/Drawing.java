package data.model;

import com.google.firebase.database.PropertyName;

import java.util.UUID;

public class Drawing {
    private final String id;
    public String encoding;
    public Drawing() {
        this.id = UUID.randomUUID().toString();
    }
    public Drawing(String encodedDrawing) {
        this.encoding = encodedDrawing;
        this.id = UUID.randomUUID().toString();
    }
    @PropertyName("id")
    public String getId() {
        return this.id;
    }
}
