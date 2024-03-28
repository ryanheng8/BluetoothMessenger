package com.example.chatroom.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.chatroom.R;
import com.example.chatroom.ui.fragment.DrawingBoardFragment;

public class DrawingBoardActivity extends AppCompatActivity {

    public DrawingBoardActivity() {
        super(R.layout.activity_single_fragment);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, DrawingBoardFragment.class, null)
                    .commit();
        }
        Log.d("DrawingActivity", "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("DrawingActivity", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("DrawingActivity", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("DrawingActivity", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("DrawingActivity", "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d("DrawingActivity", "OnRestart");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("DrawingActivity", "onDestroy");
    }
}