package com.example.chatroom.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chatroom.R;
import com.example.chatroom.ui.activity.HomePageActivity;
import com.example.chatroom.ui.activity.RegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import data.model.viewmodel.LoginViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    private FirebaseAuth mAuth;

    private LoginViewModel viewModel;

    Button signIn;
    Button guest;
    Button register;

    private EditText mUsernameEditText;
    private EditText mPasswordEditText;

    public LoginFragment() {
        // Required empty public constructor
        super(R.layout.fragment_login);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        return new LoginFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);


        this.signIn = v.findViewById(R.id.signInButton);
        this.guest = v.findViewById(R.id.guestButton);
        this.register = v.findViewById(R.id.registerButton);

        this.mUsernameEditText = v.findViewById(R.id.editTextTextEmailAddress);
        this.mPasswordEditText = v.findViewById(R.id.editTextTextPassword);

        if (signIn != null) {
            signIn.setOnClickListener(this);
        }
        if (guest != null) {
            guest.setOnClickListener(this);
        }
        if (register != null) {
            register.setOnClickListener(this);
        }

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("LoginFragment", "onViewCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Activity activity = requireActivity();
        if (mAuth.getCurrentUser() != null) {
            // User is signed in
            startActivity(new Intent(activity, HomePageActivity.class));
            activity.finish();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("LoginFragment", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("LoginFragment", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("LoginFragment", "onStop");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("LoginFragment", "onSaveInstanceState");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("LoginFragment", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("LoginFragment", "onDestroy");
    }

    @Override
    public void onClick(View view) {
        final int viewId = view.getId();
        Activity activity = requireActivity();

        if (viewId == R.id.signInButton) {
            signIn();
        } else if (viewId == R.id.guestButton) {
            signInAnonymously();
        } else if (viewId == R.id.registerButton) {
            startActivity(new Intent(activity, RegisterActivity.class));
            activity.finish();
        }
    }

    private void signIn() {
        Activity activity = requireActivity();
        final String email = mUsernameEditText.getText().toString();
        final String password = mPasswordEditText.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Activity activity = requireActivity();
                    startActivity(new Intent(activity, HomePageActivity.class));
                    activity.finish();
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(requireContext(), "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void signInAnonymously() {
        Activity activity = requireActivity();

        mAuth.signInAnonymously()
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(activity, "Anonymous mode enabled",
                                    Toast.LENGTH_SHORT).show();

                            assert user != null;
                            viewModel.createAnonymousUser(user.getUid());

                            startActivity(new Intent(activity, HomePageActivity.class));
                            activity.finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(activity, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}