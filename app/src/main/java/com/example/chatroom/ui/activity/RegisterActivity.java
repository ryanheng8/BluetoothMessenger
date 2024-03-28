package com.example.chatroom.ui.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chatroom.R;
import com.example.chatroom.ui.fragment.RegisterFragment;

public class RegisterActivity extends AppCompatActivity {

    public RegisterActivity() {
        super(R.layout.activity_single_fragment);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, RegisterFragment.class, null)
                    .commit();
        }
        Log.d("RegisterActivity", "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("RegisterActivity", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("RegisterActivity", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("RegisterActivity", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("RegisterActivity", "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d("RegisterActivity", "OnRestart");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("RegisterActivity", "onDestroy");
    }
}