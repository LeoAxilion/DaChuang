package com.example.xinyuxin.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.TextView;

import com.example.xinyuxin.FunctionActivity;
import com.example.xinyuxin.R;
import com.example.xinyuxin.WelcomeActivity;

public class DataFragment extends Fragment {

    public FunctionActivity activity = null;
    View view = null;
    TextView name = null;
    ImageButton face = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_data, container, false);
        initialize();
        return view;
    }

    private void initialize() {
        face = view.findViewById(R.id.data_face);
        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WelcomeActivity.class);
                activity.finishAll();
                startActivity(intent);
            }
        });
        name = view.findViewById(R.id.data_name);
        if(FunctionActivity.name != null){
            name.setText(FunctionActivity.name+",欢迎您!");
        }
    }
}
