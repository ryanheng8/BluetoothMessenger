package com.example.chatroom.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.chatroom.R;
import com.example.chatroom.ui.DrawingBoardView;
import com.example.chatroom.ui.activity.ConnectivityActivity;
import com.example.chatroom.ui.activity.DrawingBoardActivity;
import com.example.chatroom.ui.activity.DrawingsActivity;
import com.example.chatroom.ui.activity.HomePageActivity;
import com.google.android.material.slider.Slider;

import data.model.viewmodel.DrawingBoardViewModel;

import java.io.ByteArrayOutputStream;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DrawingBoardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DrawingBoardFragment extends Fragment implements View.OnClickListener {

    DrawingBoardView drawingBoard;
    // Declare drawing buttons
    Button homeButton;
    Button saveButton;
    Button loadButton;
    Button deleteButton;

    // Declare color buttons
    Button colorBlack;
    Button colorWhite;
    Button colorRed;
    Button colorOrange;
    Button colorYellow;
    Button colorGreen;
    Button colorBlue;
    Button colorPurple;
    Slider thicknessSlider;
    DrawingBoardViewModel viewModel;

    public DrawingBoardFragment() {
        // Required empty public constructor
        super(R.layout.fragment_drawing_board);
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
    public static DrawingBoardFragment newInstance(String param1, String param2) {
        return new DrawingBoardFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("DrawingBoardFragment", "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_drawing_board, container, false);
        drawingBoard = (DrawingBoardView) v.findViewById(R.id.drawingBoardView);
        viewModel = new ViewModelProvider(this).get(DrawingBoardViewModel.class);

        this.homeButton = v.findViewById(R.id.homeButton);
        this.saveButton = v.findViewById(R.id.saveButton);
        this.loadButton = v.findViewById(R.id.loadButton);
        this.deleteButton = v.findViewById(R.id.deleteButton);
        this.thicknessSlider = v.findViewById(R.id.thicknessSlider);
        if (homeButton != null) {
            homeButton.setOnClickListener(this);
        }
        if (saveButton != null) {
            saveButton.setOnClickListener(this);
        }
        if (loadButton != null) {
            loadButton.setOnClickListener(this);
        }
        if (deleteButton != null) {
            deleteButton.setOnClickListener(this);
        }

        // Initialize color buttons
        this.colorBlack = v.findViewById(R.id.colorBlack);
        this.colorBlue = v.findViewById(R.id.colorBlue);
        this.colorGreen = v.findViewById(R.id.colorGreen);
        this.colorOrange = v.findViewById(R.id.colorOrange);
        this.colorPurple = v.findViewById(R.id.colorPurple);
        this.colorRed = v.findViewById(R.id.colorRed);
        this.colorWhite = v.findViewById(R.id.colorWhite);
        this.colorYellow = v.findViewById(R.id.colorYellow);
        if (colorBlack != null) {
            colorBlack.setOnClickListener(this);
        }
        if (colorBlue != null) {
            colorBlue.setOnClickListener(this);
        }
        if (colorGreen != null) {
            colorGreen.setOnClickListener(this);
        }
        if (colorOrange != null) {
            colorOrange.setOnClickListener(this);
        }
        if (colorPurple != null) {
            colorPurple.setOnClickListener(this);
        }
        if (colorRed != null) {
            colorRed.setOnClickListener(this);
        }
        if (colorWhite != null) {
            colorWhite.setOnClickListener(this);
        }
        if (colorYellow != null) {
            colorYellow.setOnClickListener(this);
        }

        // Initialize slider listener
        if (thicknessSlider != null) {
            thicknessSlider.addOnChangeListener(
                new Slider.OnChangeListener() {
                    @Override
                    public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                        drawingBoard.thicknessChanged(value);
                    }
                }
            );
        }

        Log.d("DrawingBoardFragment", "onCreateView");
        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("DrawingBoardFragment", "onViewCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("DrawingBoardFragment", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("DrawingBoardFragment", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("DrawingBoardFragment", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("DrawingBoardFragment", "onStop");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("DrawingBoardFragment", "onSaveInstanceState");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("DrawingBoardFragment", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("DrawingBoardFragment", "onDestroy");
    }

    /*
     * Implements onClick for the save button.
     */
    @Override
    public void onClick(View view) {
        final int viewId = view.getId();
        Activity activity = requireActivity();


        if (viewId == R.id.saveButton) {
            this.viewModel.saveDrawing(bitMapToString(drawingBoard.getBitmap()));
        } else if (viewId == R.id.loadButton) {
            this.viewModel.loadDrawing(this);
        } else if (viewId == R.id.deleteButton) {
            this.viewModel.deleteDrawing();
            startActivity(new Intent(activity, DrawingBoardActivity.class));
            activity.finish();
        } else if (viewId == R.id.colorBlack) {
            this.drawingBoard.setColorChange(ResourcesCompat.getColor(getResources(), R.color.black, null));
        } else if (viewId == R.id.colorBlue) {
            this.drawingBoard.setColorChange(ResourcesCompat.getColor(getResources(), R.color.blue, null));
        } else if (viewId == R.id.colorGreen) {
            this.drawingBoard.setColorChange(ResourcesCompat.getColor(getResources(), R.color.green, null));
        } else if (viewId == R.id.colorOrange) {
            this.drawingBoard.setColorChange(ResourcesCompat.getColor(getResources(), R.color.orange, null));
        } else if (viewId == R.id.colorPurple) {
            this.drawingBoard.setColorChange(ResourcesCompat.getColor(getResources(), R.color.purple, null));
        } else if (viewId == R.id.colorRed) {
            this.drawingBoard.setColorChange(ResourcesCompat.getColor(getResources(), R.color.red, null));
        } else if (viewId == R.id.colorWhite) {
            this.drawingBoard.setColorChange(ResourcesCompat.getColor(getResources(), R.color.white, null));
        } else if (viewId == R.id.colorYellow) {
            this.drawingBoard.setColorChange(ResourcesCompat.getColor(getResources(), R.color.yellow, null));
        } else if (viewId == R.id.homeButton) {
            startActivity(new Intent(activity, HomePageActivity.class));
            activity.finish();
        }
    }

    public void updateDrawingBoard(Bitmap bitmap) {
        this.drawingBoard.setmBitmap(bitmap);
    }

    /*
     * Convert a bitmap to a string to save to database.
     */
    public String bitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b= baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    /*
     * Convert the string in database back to a bitmap.
     */
    public Bitmap stringToBitMap(String encodedString){
        try {
            byte [] encodeByte=Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            Log.e("String to bitmap error", Objects.requireNonNull(e.getMessage()));
            return null;
        }
    }

}