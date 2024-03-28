package com.example.chatroom.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.chatroom.R;
import com.example.chatroom.ui.activity.HomePageActivity;
import com.example.chatroom.ui.activity.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditProfileFragment extends Fragment implements View.OnClickListener {

    private FirebaseUser mUser;
    private FirebaseAuth mAuth;

    private EditText mDisplayNameChoose;
    private EditText mTextPasswordChoose;
    private EditText mTextPasswordConfirm;

    Button home;
    Button logout;
    Button submit;
    Button cancel;
    Button delete;

    public EditProfileFragment() {
        // Required empty public constructor
        super(R.layout.fragment_edit_profile);
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
    public static EditProfileFragment newInstance(String param1, String param2) {
        return new EditProfileFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        this.submit = v.findViewById(R.id.editProfileSubmit);
        this.cancel = v.findViewById(R.id.editProfileCancel);
        this.delete = v.findViewById(R.id.deleteAccountButton);
        this.home = v.findViewById(R.id.homeButton);
        this.logout = v.findViewById(R.id.logoutButton);

        mDisplayNameChoose = v.findViewById(R.id.editTextTextChangeDisplayName);
        mTextPasswordChoose = v.findViewById(R.id.editTextTextChangePassword);
        mTextPasswordConfirm = v.findViewById(R.id.editTextTextConfirmPasswordChange);

        if (submit != null) {
            submit.setOnClickListener(this);
        }

        if (cancel != null) {
            cancel.setOnClickListener(this);
        }

        if (delete != null) {
            delete.setOnClickListener(this);
        }

        if (home != null) {
            home.setOnClickListener(this);
        }

        if (logout != null) {
            logout.setOnClickListener(this);
        }

        mDisplayNameChoose.setText(mUser.getDisplayName());

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

        if (viewId == R.id.editProfileSubmit) {

            // Update display name
            final String displayName = mDisplayNameChoose.getText().toString();
            if (!TextUtils.isEmpty(displayName) && !displayName.equals(mUser.getDisplayName())) {
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(displayName)
                        .build();

                mUser.updateProfile(profileUpdates)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(requireContext(), "Updated display name successful",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(requireContext(), "Failed to update display name",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
            // Update password
            final String password = mTextPasswordChoose.getText().toString();
            final String passwordConfirm = mTextPasswordConfirm.getText().toString();

            if (!TextUtils.isEmpty(password) && password.equals(passwordConfirm)) {
                updatePassword(password);
            }
        } else if (viewId == R.id.editProfileCancel) {
            startActivity(new Intent(activity, HomePageActivity.class));
            activity.finish();
        } else if (viewId == R.id.deleteAccountButton) {
            mUser.delete()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(requireContext(), "Deleted account successful",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(requireContext(), "Failed to delete account",
                                        Toast.LENGTH_SHORT).show();
                            }
                            startActivity(new Intent(activity, LoginActivity.class));
                            activity.finish();
                        }
                    });
        } else if (viewId == R.id.homeButton) {
            startActivity(new Intent(activity, HomePageActivity.class));
            activity.finish();
        } else if (viewId == R.id.logoutButton) {
            mAuth.signOut();
            startActivity(new Intent(activity, LoginActivity.class));
            activity.finish();
        }
    }

    private void updatePassword(String password) {
        mUser.updatePassword(password)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(requireContext(), "Updated password successful",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(requireContext(), "Failed to update password",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}