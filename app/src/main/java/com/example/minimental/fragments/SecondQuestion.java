package com.example.minimental.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.minimental.R;

public class SecondQuestion extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.second_question,container,false);
        //return super.onCreateView(inflater, container, savedInstanceState);
        Button nxtBtn = rootView.findViewById(R.id.next_Btn);
        nxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return rootView;
    }

    public static SecondQuestion newInstance(String username){
       SecondQuestion secondQuestion = new SecondQuestion();
       Bundle bundle = new Bundle();
       bundle.putString("username",username);
       secondQuestion.setArguments(bundle);
       return secondQuestion;
    }

}
