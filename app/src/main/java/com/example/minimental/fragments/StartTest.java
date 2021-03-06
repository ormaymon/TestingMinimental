package com.example.minimental.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.minimental.R;

public class StartTest extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.start_test,container,false);
        Button startTestBtn = rootView.findViewById(R.id.start_btn);
        Animation animation= AnimationUtils.loadAnimation(getContext(),R.anim.pulse);
        startTestBtn.startAnimation(animation);

        startTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTestBtn.clearAnimation();
                Navigation.findNavController(view).navigate(R.id.action_startTest_to_informationFragment);
            }
        });

        return rootView;

    }

}
