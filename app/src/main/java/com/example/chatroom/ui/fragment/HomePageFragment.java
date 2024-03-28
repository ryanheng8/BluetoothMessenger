package com.example.chatroom.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.chatroom.R;
import com.example.chatroom.ui.activity.ChatRoomActivity;
import com.example.chatroom.ui.activity.ConnectivityActivity;
import com.example.chatroom.ui.activity.DrawingsActivity;
import com.example.chatroom.ui.activity.DrawingBoardActivity;
import com.example.chatroom.ui.activity.EditProfileActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomePageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomePageFragment extends Fragment implements View.OnClickListener{

    Button edit;
    Button connect;
    Button drawings;
    Button drawingBoard;
    Button chat;
    Button viewChat;

    public HomePageFragment() {
        // Required empty public constructor
        super(R.layout.fragment_home_page);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DrawingBoardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomePageFragment newInstance(String param1, String param2) {
        return new HomePageFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_page, container, false);

        this.edit = v.findViewById(R.id.editButton);
        this.connect = v.findViewById(R.id.connectionButton);
        this.drawings = v.findViewById(R.id.drawingsButton);
        this.drawingBoard = v.findViewById(R.id.drawingBoardButton);
        this.chat = v.findViewById(R.id.createChatButton);
        this.viewChat = v.findViewById(R.id.viewButton);

        if (edit != null) {
            edit.setOnClickListener(this);
        }

        if (connect != null) {
            connect.setOnClickListener(this);
        }

        if (drawings != null) {
            drawings.setOnClickListener(this);
        }

        if (drawingBoard != null) {
            drawingBoard.setOnClickListener(this);
        }

        if (chat != null) {
            chat.setOnClickListener(this);
        }

        if (viewChat != null) {
            viewChat.setOnClickListener(this);
        }
        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        final int viewId = view.getId();
        Activity activity = requireActivity();

        if (viewId == R.id.editButton) {
            startActivity(new Intent(activity, EditProfileActivity.class));
            activity.finish();
        } else if (viewId == R.id.connectionButton) {
            startActivity(new Intent(activity, ConnectivityActivity.class));
            activity.finish();
        } else if (viewId == R.id.drawingsButton) {
            startActivity(new Intent(activity, DrawingsActivity.class));
            activity.finish();
        } else if (viewId == R.id.drawingBoardButton) {
            startActivity(new Intent(activity, DrawingBoardActivity.class));
            activity.finish();
        } else if (viewId == R.id.createChatButton) {
            startActivity(new Intent(activity, ChatRoomActivity.class));
            activity.finish();
        } else if (viewId == R.id.viewButton) {
            startActivity(new Intent(activity, ChatRoomActivity.class));
            activity.finish();
        }
    }

}