package data;

import android.util.Log;

import androidx.annotation.NonNull;

import data.model.Chatroom;
import data.model.Drawing;
import data.model.Message;
import data.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class DataRepository implements FirebaseDbRepository {
    private static DataRepository instance;
    private final FirebaseDatabase db;
    private DataRepository() {
        this.db = FirebaseDatabase.getInstance();
    }

    // singleton pattern since we probably only want one of these running around
    public static DataRepository getInstance() {
        if (instance == null) {
            instance = new DataRepository();
        }
        return instance;
    }

    @Override
    public DatabaseReference getUser(String id) {
        return db.getReference().child("users").child(id);
    }

    @Override
    public boolean addUser(User user) {
        this.db.getReference().child("users").child(user.id).setValue(user);
        return true;
    }

    @Override
    public DatabaseReference getChatroom(String id) {
        return this.db.getReference().child("chatrooms").child(id);
    }

    @Override
    public boolean addChatroom(Chatroom chatroom) {
        String roomId = UUID.randomUUID().toString();
        this.db.getReference().child("chatrooms").child(roomId).setValue(chatroom);
        for (String userId : chatroom.users.keySet()) {
            User[] userWrapper = new User[1];

            this.getUser(userId).get().addOnCompleteListener(
                    task -> userWrapper[0] = task.getResult().getValue(User.class)
            );
            userWrapper[0].chatRooms.put(roomId, true);
            this.addUser(userWrapper[0]);
        }
        return true;
    }

    @Override
    public Message getMessage(String id) {
        Message[] messageArr = new Message[1];
        this.db.getReference().child("messages").child(id).get().addOnCompleteListener(
                new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        messageArr[0] = task.getResult().getValue(Message.class);
                    }
                }
        );
        return messageArr[0];
    }

    @Override
    public boolean createMessage(Message message) {
        String id = UUID.randomUUID().toString();
        this.db.getReference().child("messages").child(id).setValue(message);
        return true;
    }

    @Override
    public void getDrawing(String id, OnCompleteListener<DataSnapshot> onComplete) {
        this.db.getReference().child("drawings").child(id).get()
                .addOnCompleteListener(onComplete);
    }

    @Override
    public boolean createDrawing(Drawing drawing, User user) {
        String id = UUID.randomUUID().toString();
        this.db.getReference().child("drawings").child(id)
                .setValue(drawing.encoding);
        user.drawings.put(id, true);
        return this.addUser(user);
    }

    @Override
    public boolean updateDrawing(Drawing drawing, User user, String id) {
        this.db.getReference().child("drawings").child(id)
                .setValue(drawing.encoding);
        user.drawings.put(id, true);
        return this.addUser(user);
    }
}
