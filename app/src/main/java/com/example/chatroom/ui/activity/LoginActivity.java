package com.example.chatroom.ui.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chatroom.R;
import com.example.chatroom.ui.fragment.LoginFragment;

public class LoginActivity extends AppCompatActivity {

    public LoginActivity() {
        super(R.layout.activity_single_fragment);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, LoginFragment.class, null)
                    .commit();
        }
        Log.d("LoginActivity", "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LoginActivity", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LoginActivity", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LoginActivity", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LoginActivity", "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d("LoginActivity", "OnRestart");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LoginActivity", "onDestroy");
    }
}