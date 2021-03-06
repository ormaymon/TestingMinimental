package com.example.minimental.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.minimental.FifthQuestion;
import com.example.minimental.R;
import com.example.minimental.Services.MediaPlayerService;
import com.example.minimental.Services.MediaPlayerServiceBinder;
import com.example.minimental.ViewModels.SharedViewModel;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class FifthFragment extends Fragment implements MediaPlayerServiceBinder {
    public SharedViewModel sharedViewModel;
    private ActivityResultLauncher<Intent> speechRecognizerLauncher;
    private TextView text;
    private EditText firstPicET;
    private EditText secondPicET;
    private ImageView firstImage;
    private Bitmap firstImageBitmap;
    private Bitmap secondImageBitmap;
    private FifthQuestion fifthQuestion = new FifthQuestion();
    private Thread imageOneProcessThread;
    private Thread imageTwoProcessThread;
    private EditText currentPictureDescribedEditText;
    private Button speakerButton;
    private String pic1;
    private String pic2;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        speechRecognizerLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult activityResult) {
                Intent data = activityResult.getData();
                StringBuffer speechResult = new StringBuffer();
                if (data != null) {
                    ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    for (String r : results) {
                        speechResult.append(r);
                    }
                    String result = speechResult.toString();
                    currentPictureDescribedEditText.setText(result);
                }
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fifth_question,container,false);

        //fifthQuestion = sharedViewModel.getFifthQuestionLiveData().getValue();
        firstPicET = rootView.findViewById(R.id.input_FirstPicET);
        secondPicET = rootView.findViewById(R.id.input_SecondPicET);
        firstImage = rootView.findViewById(R.id.first_Image);
        ImageView secondImage = rootView.findViewById(R.id.fifth_question_second_image);


        text = rootView.findViewById(R.id.input_FirstPicET);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        Integer Version = sharedViewModel.getVersion().getValue();
        if (Version == null || Version == 0) //only for now some users dosnt have version alredy asked to add
        {
            Version = 1;
        }
        if (Version == 1){ // for pic
            pic1 = "https://firebasestorage.googleapis.com/v0/b/minimental-hit.appspot.com/o/FifthQuestion%20Pictures%2F%D7%AA%D7%9E%D7%95%D7%A0%D7%941%20%D7%92%D7%A8%D7%A1%D7%90%20%D7%A8%D7%90%D7%A9%D7%95%D7%A0%D7%94.jpg?alt=media&token=1d064c87-9abf-4d45-926c-45a7829cd201";
            pic2 = "https://firebasestorage.googleapis.com/v0/b/minimental-hit.appspot.com/o/FifthQuestion%20Pictures%2F%D7%AA%D7%9E%D7%95%D7%A0%D7%94%202%20%D7%92%D7%A8%D7%A1%D7%90%20%D7%A8%D7%90%D7%A9%D7%95%D7%A0%D7%94.webp?alt=media&token=d9c89de8-94ad-4164-a396-3ce766d6e4c5";
        }
        if (Version == 2){
            pic1 = "https://firebasestorage.googleapis.com/v0/b/minimental-hit.appspot.com/o/FifthQuestion%20Pictures%2F%D7%AA%D7%9E%D7%95%D7%A0%D7%941%20%D7%92%D7%A8%D7%A1%D7%90%20%D7%A9%D7%A0%D7%99%D7%94.jpg?alt=media&token=7be08784-b28e-43f2-baf4-4afde2ce963c";
            pic2 = "https://firebasestorage.googleapis.com/v0/b/minimental-hit.appspot.com/o/FifthQuestion%20Pictures%2F%D7%AA%D7%9E%D7%95%D7%A0%D7%942%20%D7%92%D7%A8%D7%A1%D7%90%20%D7%A9%D7%A0%D7%99%D7%94.jpg?alt=media&token=77bb33eb-899c-4264-8561-9fcda5dc4d9a";
        }
        if (Version == 3){
            pic1 = "https://firebasestorage.googleapis.com/v0/b/minimental-hit.appspot.com/o/FifthQuestion%20Pictures%2F%D7%AA%D7%9E%D7%95%D7%A0%D7%941%20%D7%92%D7%A8%D7%A1%D7%90%20%D7%A9%D7%9C%D7%99%D7%A9%D7%99%D7%AA.png?alt=media&token=10979082-663b-4739-a549-188a372c9128";
            pic2 = "https://firebasestorage.googleapis.com/v0/b/minimental-hit.appspot.com/o/FifthQuestion%20Pictures%2F%D7%AA%D7%9E%D7%95%D7%A0%D7%942%20%D7%92%D7%A8%D7%A1%D7%90%20%D7%A9%D7%9C%D7%99%D7%A9%D7%99%D7%AA.jpg?alt=media&token=6d60dd4f-b49b-44e3-84b6-644dd2c6fee7";
        }
        if (Version == 4){
            pic1 = "https://firebasestorage.googleapis.com/v0/b/minimental-hit.appspot.com/o/FifthQuestion%20Pictures%2F%D7%AA%D7%9E%D7%95%D7%A0%D7%942%20%D7%92%D7%A8%D7%A1%D7%90%20%D7%A9%D7%9C%D7%99%D7%A9%D7%99%D7%AA.jpg?alt=media&token=6d60dd4f-b49b-44e3-84b6-644dd2c6fee7";
            pic2 = "https://firebasestorage.googleapis.com/v0/b/minimental-hit.appspot.com/o/FifthQuestion%20Pictures%2F%D7%AA%D7%9E%D7%95%D7%A0%D7%941%20%D7%92%D7%A8%D7%A1%D7%90%20%D7%A8%D7%90%D7%A9%D7%95%D7%A0%D7%94.jpg?alt=media&token=1d064c87-9abf-4d45-926c-45a7829cd201";
        }

        Log.d("pic1.1" , String.valueOf(pic1));

        imageOneProcessThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d("pic1" , String.valueOf(pic1));
                    URL url = new URL(pic1);
                    firstImageBitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                }catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        });
        imageOneProcessThread.start();

        imageTwoProcessThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(pic2);
                    secondImageBitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                }catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        });
        imageTwoProcessThread.start();

        try {
            imageOneProcessThread.join();
            imageTwoProcessThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        firstImage.setImageBitmap(firstImageBitmap);
        secondImage.setImageBitmap(secondImageBitmap);


        Button nxtBtn = rootView.findViewById(R.id.next_Btn);
        ImageButton recordBtnForImageOne = rootView.findViewById(R.id.pict1_mic_image_view);
        ImageButton recordButtonForImageTwo = rootView.findViewById(R.id.pict2_mic_image_view);
        speakerButton = rootView.findViewById(R.id.describe_instructions_speaker);
        Animation animation= AnimationUtils.loadAnimation(getContext(),R.anim.pulse);
        speakerButton.startAnimation(animation);
        nxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation= AnimationUtils.loadAnimation(getContext(),R.anim.bounce);
                nxtBtn.startAnimation(animation);
                fifthQuestion.setFirstpic(firstPicET.getText().toString());
                fifthQuestion.setSecoundpic(secondPicET.getText().toString());
                sharedViewModel.setFifthQuestionLiveData(fifthQuestion);
                Navigation.findNavController(view).navigate(R.id.action_fifthQuestion_to_sixth_question);
            }
        });
        recordBtnForImageOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPictureDescribedEditText = firstPicET;
                startSpeechRecognition();
            }
        });
        recordButtonForImageTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPictureDescribedEditText = secondPicET;
                startSpeechRecognition();
            }
        });

        speakerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speakerButton.clearAnimation();
                startMediaService();
            }
        });
        return rootView;
    }
    private void startSpeechRecognition()
    {
        Intent speechIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechIntent.putExtra(RecognizerIntent.EXTRA_RESULTS , 1);
        speechRecognizerLauncher.launch(speechIntent);
    }

    private void startMediaService()
    {
        Intent intent = new Intent(getContext() , MediaPlayerService.class);
        speakerButton.setClickable(false);
        MediaPlayerService.currentFragment = this;
        intent.putExtra("Link" , "https://firebasestorage.googleapis.com/v0/b/minimental-hit.appspot.com/o/Questions%20Instructions%2FMyRec_0525_0925%D7%94%D7%95%D7%A8%D7%90%D7%AA%20%D7%A9%D7%99%D7%95%D7%9D.mp3?alt=media&token=b9d3dd38-a837-4708-b00b-2125faad4548");
        getContext().startService(intent);
    }

    @Override
    public void startSpeechButtonAnimation() {
        speakerButton.setClickable(true);
    }
}
