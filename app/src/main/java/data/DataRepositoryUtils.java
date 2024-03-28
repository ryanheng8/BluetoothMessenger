package data;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

import data.model.Chatroom;
import data.model.Drawing;
import data.model.Message;
import data.model.User;

public class DataRepositoryUtils {
    private DataRepositoryUtils() {}
    public List<Chatroom> getChatrooms(User user) {
        List<Chatroom> chatrooms = new ArrayList<>();
        for (String id : user.chatRooms.keySet()) {
            DatabaseReference data = DataRepository.getInstance().getChatroom(id);
            data.get().addOnCompleteListener(
                    task -> chatrooms.add(task.getResult().getValue(Chatroom.class))
            );
        }
        return chatrooms;
    }
    public List<Message> getMessages(Chatroom room) {
        List<Message> messages = new ArrayList<>();
        for (String id : room.messages.keySet()) {
            messages.add(DataRepository.getInstance().getMessage(id));
        }
        return messages;
    }
    public List<Drawing> getDrawings(User user) {
        List<Drawing> drawings = new ArrayList<>();
        for (String id : user.drawings.keySet()) {
            DataRepository.getInstance().getDrawing(id,
                    task -> {
                        drawings.add(task.getResult().getValue(Drawing.class));
                    });
        }
        return drawings;
    }
}
