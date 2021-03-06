package com.example.minimental.fragments;

import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.minimental.CustomView.MilkDragView;
import com.example.minimental.R;
import com.example.minimental.Services.MediaPlayerService;
import com.example.minimental.Services.MediaPlayerServiceBinder;
import com.example.minimental.ViewModels.SharedViewModel;

public class SeventhQuestion extends Fragment implements MediaPlayerServiceBinder {

    //ImageView milk;
    private SharedViewModel sharedViewModel;
    private Boolean sevnthCurrectOrder = false;
    private MilkDragView.DrawableProxy milkPicture;
    private MilkDragView milkDragView;
    private Rect milkBorderRect;
    private Rect grapeBorderRect;
    private Rect chickenBorderRect;
    private Rect canBorderRect;
    private Drawable clickOnFridge;
    private Button listenBtn;
    private String link;
    private Integer Version;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.seventh_question,container,false);
        Button nxtBtn = rootView.findViewById(R.id.next_Btn);
        listenBtn = rootView.findViewById(R.id.seventh_question_instruction_speaker);
        milkDragView = rootView.findViewById(R.id.milk_drag_view);
        milkPicture = milkDragView.getMilkDrawable();
        milkBorderRect = milkDragView.getMilkBorderRect();
        grapeBorderRect = milkDragView.getGrapeBorderRect();
        chickenBorderRect = milkDragView.getChickenBorderRect();
        canBorderRect = milkDragView.getCanBorderRect();
        clickOnFridge = milkDragView.getFridgeDrawble();
        milkDragView.setOnTouchListener(new milkDragTouchListener());
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        Animation animation= AnimationUtils.loadAnimation(getContext(),R.anim.pulse);
        listenBtn.startAnimation(animation);
        //milk = rootView.findViewById(R.id.seventh_question_ImageView_milk);
        //milk.setOnTouchListener(new MyTouchListener());
        Version = sharedViewModel.getVersion().getValue();
        if (Version == null || Version == 0)
        {
            Version = 1;
        }
        if(Version == 1){
            //move milk
            link = "https://firebasestorage.googleapis.com/v0/b/minimental-hit.appspot.com/o/Three%20Phase%20Instruction%20Versions%2FMyRec_0526_1313%D7%94%D7%95%D7%A8%D7%90%D7%94%20%D7%AA%D7%9C%D7%AA%20%D7%A9%D7%9C%D7%91%D7%99%D7%AA.mp3?alt=media&token=3c76d7dc-b264-4bdb-83fe-4ce16eaa5e44";
        }
        if(Version == 2){
            //move grapes
            link = "https://firebasestorage.googleapis.com/v0/b/minimental-hit.appspot.com/o/Three%20Phase%20Instruction%20Versions%2FMyRec_0526_1328%D7%92%D7%A8%D7%A1%D7%94%20%D7%A9%D7%A0%D7%99%D7%94%20%D7%94%D7%95%D7%A8%D7%90%D7%94%20%D7%AA%D7%9C%D7%AA%20%D7%A9%D7%9C%D7%91%D7%99%D7%AA.mp3?alt=media&token=24ba10bd-e6e6-415b-9397-0317c4c0cc3e";
        }
        if(Version == 3){
            //move chicken
            link = "https://firebasestorage.googleapis.com/v0/b/minimental-hit.appspot.com/o/Three%20Phase%20Instruction%20Versions%2FMyRec_0526_1330%D7%92%D7%A8%D7%A1%D7%94%20%D7%A9%D7%9C%D7%99%D7%A9%D7%99%D7%AA%20%D7%94%D7%95%D7%A8%D7%90%D7%AA%20%D7%AA%D7%9C%D7%AA%20%D7%A9%D7%9C%D7%91%D7%99%D7%AA.mp3?alt=media&token=d5204567-d417-41b1-80f5-8739ef2e0cf8";
        }
        if(Version == 4){
            //move can
            link = "https://firebasestorage.googleapis.com/v0/b/minimental-hit.appspot.com/o/Three%20Phase%20Instruction%20Versions%2FMyRec_0526_1331%D7%92%D7%A8%D7%A1%D7%94%20%D7%A8%D7%91%D7%99%D7%A2%D7%99%D7%AA%20%D7%94%D7%95%D7%A8%D7%90%D7%94%20%D7%AA%D7%9C%D7%AA%20%D7%A9%D7%9C%D7%91%D7%99%D7%AA.mp3?alt=media&token=e706eeb4-647c-4910-8a24-6b1dc3eba139";
        }


        nxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation= AnimationUtils.loadAnimation(getContext(),R.anim.bounce);
                nxtBtn.startAnimation(animation);
                sharedViewModel.setCurrectOrderSeventh(sevnthCurrectOrder);
                Navigation.findNavController(view).navigate(R.id.action_SeventhQuestion_to_eighthQuestion);
            }
        });

        listenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenBtn.clearAnimation();
                startMediaService();
            }
        });

        return rootView;
    }

    private class milkDragTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            switch (motionEvent.getActionMasked()) {
                case MotionEvent.ACTION_MOVE:
                    if( (milkBorderRect.contains((int)x , (int)y) && milkDragView.isFrdigeOpen()) || (milkDragView.itemOnTable(milkBorderRect) && !milkDragView.isClickOnFridge((int)x , (int)y)) ) {
                        milkDragView.moveMilkBorderRect((int) x, (int) y);
                    }
                    else if( (grapeBorderRect.contains((int)x , (int)y) && milkDragView.isFrdigeOpen()) || (milkDragView.itemOnTable(grapeBorderRect) && !milkDragView.isClickOnFridge((int)x , (int)y)))
                    {
                        milkDragView.moveGrapeBorderRect((int)x , (int)y);
                    }
                    else if( (chickenBorderRect.contains((int)x , (int)y) && milkDragView.isFrdigeOpen()) || (milkDragView.itemOnTable(chickenBorderRect) && !milkDragView.isClickOnFridge((int)x , (int)y)))
                    {
                        milkDragView.moveChickenBorderRect((int)x , (int)y);
                    }
                    else if( (canBorderRect.contains((int)x , (int)y) && milkDragView.isFrdigeOpen()) || (milkDragView.itemOnTable(canBorderRect) && !milkDragView.isClickOnFridge((int)x , (int)y)))
                    {
                        milkDragView.moveCanBorederRect((int)x , (int)y);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    milkDragView.returnToPlaceIfItemIsOutOfBounds();
                    if(milkDragView.milkIsInPosition() && Version == 1)
                    {
                        sevnthCurrectOrder = true;
                    }
                    else if(milkDragView.grapeIsInPosition() && Version == 2)
                    {
                        sevnthCurrectOrder = true;
                    }
                    else if(milkDragView.chickenIsInPosition() && Version == 3)
                    {
                        sevnthCurrectOrder = true;

                    }
                    else if(milkDragView.canIsInPosition() && Version == 4)
                    {
                        sevnthCurrectOrder = true;
                        milkDragView.changeBorderColor();
                    }
                    break;
                case MotionEvent.ACTION_DOWN:
                    if((!milkBorderRect.contains((int)x , (int)y)) && (!grapeBorderRect.contains((int)x , (int)y)) && (!chickenBorderRect.contains((int)x , (int)y)) && (!canBorderRect.contains((int)x , (int)y)) )
                    {
                        if(milkDragView.isClickOnFridge((int) x, (int) y))
                        {
                            milkDragView.setFridgeState(true);
                        }
                    }
                    break;
            }
                return true;
        }
    }

    private void startMediaService()
    {
        Intent intent = new Intent(getContext() , MediaPlayerService.class);
        MediaPlayerService.currentFragment = this;
        listenBtn.setClickable(false);
        intent.putExtra("Link" , link);
        getContext().startService(intent);
    }

    @Override
    public void startSpeechButtonAnimation() {
        listenBtn.setClickable(true);
    }

}
