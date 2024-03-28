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
import com.example.chatroom.ui.activity.HomePageActivity;
import com.example.chatroom.ui.activity.RegisterActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConnectivityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConnectivityFragment extends Fragment implements View.OnClickListener{

    Button home;

    public ConnectivityFragment() {
        // Required empty public constructor
        super(R.layout.fragment_connectivity);
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
    public static ConnectivityFragment newInstance(String param1, String param2) {
        return new ConnectivityFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_connectivity, container, false);

        this.home = v.findViewById(R.id.homeButton);

        if (home != null) {
            home.setOnClickListener(this);
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

    public void onClick(View view) {
        final int viewId = view.getId();
        Activity activity = requireActivity();

        if (viewId == R.id.homeButton) {
            startActivity(new Intent(activity, HomePageActivity.class));
            activity.finish();
        }
    }

}