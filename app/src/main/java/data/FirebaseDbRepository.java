package data;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import data.model.Chatroom;
import data.model.Drawing;
import data.model.Message;
import data.model.User;

public interface FirebaseDbRepository {
    DatabaseReference getUser(String email);
    boolean addUser(User user);
    DatabaseReference getChatroom(String id);
    boolean addChatroom(Chatroom chatroom);
    Message getMessage(String id);
    boolean createMessage(Message message);
    void getDrawing(String id, OnCompleteListener<DataSnapshot> onComplete);
    boolean createDrawing(Drawing drawing, User user);

    boolean updateDrawing(Drawing drawing, User user, String id);
}
