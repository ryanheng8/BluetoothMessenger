package com.example.chatroom.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.chatroom.R;
import com.example.chatroom.ui.activity.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import data.model.viewmodel.RegisterViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener {

    private FirebaseAuth mAuth;

    private EditText mEmailAddressChoose;
    private EditText mTextPasswordChoose;
    private EditText mTextPasswordConfirm;

    Button register;
    Button cancel;

    RegisterViewModel viewModel;

    public RegisterFragment() {
        // Required empty public constructor
        super(R.layout.fragment_register);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        return new RegisterFragment();
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
        View v = inflater.inflate(R.layout.fragment_register, container, false);

        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);


        this.register = v.findViewById(R.id.registerConfirmButton);
        this.cancel = v.findViewById(R.id.cancelRegisterButton);

        mEmailAddressChoose = v.findViewById(R.id.editTextTextEmailAddressChoose);
        mTextPasswordChoose = v.findViewById(R.id.editTextTextPasswordChoose);
        mTextPasswordConfirm = v.findViewById(R.id.editTextTextPasswordConfirm);

        if (register != null) {
            register.setOnClickListener(this);
        }

        if (cancel != null) {
            cancel.setOnClickListener(this);
        }

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("RegisterFragment", "onViewCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("RegisterFragment", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("RegisterFragment", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("RegisterFragment", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("RegisterFragment", "onStop");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("RegisterFragment", "onSaveInstanceState");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mEmailAddressChoose = null;
        mTextPasswordChoose = null;
        mTextPasswordConfirm = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("RegisterFragment", "onDestroy");
    }

    @Override
    public void onClick(View view) {
        final int viewId = view.getId();
        Activity activity = requireActivity();

        if (viewId == R.id.registerConfirmButton) {
            createAccount();
        } else if (viewId == R.id.cancelRegisterButton) {
            startActivity(new Intent(activity, LoginActivity.class));
            activity.finish();
        }
    }

    private void createAccount() {
        Activity activity = requireActivity();
        final String email = mEmailAddressChoose.getText().toString();
        final String password = mTextPasswordChoose.getText().toString();
        final String confirmPassword = mTextPasswordConfirm.getText().toString();

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && password.equals(confirmPassword)) {

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();

                                // If sign in fails, display a message to the user.
                                assert user != null;
                                Toast.makeText(requireContext(),    "Successful user creation: " + user.getEmail(),
                                        Toast.LENGTH_SHORT).show();

                                viewModel.createUser(user.getUid());

                                startActivity(new Intent(activity, LoginActivity.class));
                                activity.finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(requireContext(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            Toast.makeText(requireContext(), "Invalid login information. Please fill in all fields carefully.",
                    Toast.LENGTH_LONG).show();
        }
    }

}