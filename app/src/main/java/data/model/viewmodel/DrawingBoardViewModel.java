package data.model.viewmodel;

import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.example.chatroom.ui.DrawingBoardView;
import com.example.chatroom.ui.fragment.DrawingBoardFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

import data.DataRepository;
import data.model.Drawing;
import data.model.User;

public class DrawingBoardViewModel extends ViewModel {
    private User user;
    private String loadedDrawingId;
    public DrawingBoardViewModel() {
        // this is just dummy code to make this work for now
        // in the future this would use firebase auth to get the email then retrieve user from
        // DataRepository
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            DataRepository.getInstance().getUser(firebaseUser.getUid()).addValueEventListener(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            user = snapshot.getValue(User.class);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.e(DrawingBoardViewModel.class.getName(),
                                    error.getMessage());
                        }
                    }
            );
        }
    }
    public void saveDrawing(String data) {
        boolean newDrawing = true;

        Drawing drawing = new Drawing();
        drawing.encoding = data;

        for (String id : this.user.drawings.keySet()) {
            if (id.equals(loadedDrawingId)) {
                DataRepository.getInstance().updateDrawing(drawing, this.user, loadedDrawingId);
                newDrawing = false;
            }
        }
        if (newDrawing) {
            DataRepository.getInstance().createDrawing(drawing, this.user);
        }
    }

    public void loadDrawing(DrawingBoardFragment fragment) {
        this.arbitraryDrawingGet(
                task -> {
                    String encoding = task.getResult().getValue(String.class);
                    fragment.updateDrawingBoard(fragment.stringToBitMap(encoding));
                }
        );
    }

    public void deleteDrawing() {
        this.arbitraryDrawingGet(task -> {
            String id = task.getResult().getKey();
            task.getResult().getRef().removeValue();
            assert id != null;
            task.getResult().getRef().getRoot().child("users").child(this.user.id)
                    .child("drawings").child(id).removeValue();
        });
    }

    /** Gets an arbitrary drawing from @this.user and performs @callback
     *
     * @param callback the callback to be performed upon retrieval of the drawing
     */
    private void arbitraryDrawingGet(OnCompleteListener<DataSnapshot> callback) {
        Iterator<String> ids = this.user.drawings.keySet().iterator();
        if (ids.hasNext()) {
            this.loadedDrawingId = ids.next();
            DataRepository.getInstance().getDrawing(loadedDrawingId, callback);
        }
    }
}
