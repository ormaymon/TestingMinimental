package com.example.minimental.fragments;

import static android.app.Activity.RESULT_OK;

import static androidx.core.content.FileProvider.getUriForFile;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.SurfaceTexture;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.bumptech.glide.Glide;
import com.example.minimental.BuildConfig;
import com.example.minimental.R;
import com.google.firebase.database.core.Context;

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
    android.content.Context context;
    List<Pictures> pictures;



    public RecyclerViewAdapter(List<Pictures> pictures, android.content.Context context) {
        this.pictures = pictures;
        this.context = context;
    }

    public void updateList(List<Pictures> pictures){
        this.pictures = pictures;
        notifyDataSetChanged();
    }

    public interface PictureAdapterListener{
        public void onTakePhotoPress(int position, View v);
    }

    PictureAdapterListener listener;

    public void setListener(PictureAdapterListener listener){
        this.listener = listener;
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView imageViewTaken;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textView_picture);
            imageViewTaken = (ImageView) itemView.findViewById(R.id.photo_imageView);
            //takePhotoBtn =(Button) itemView.findViewById(R.id.take_photo_btn);
            imageViewTaken.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null)
                    {
                        listener.onTakePhotoPress(getAdapterPosition(),v);
                    }

                }
            });

        }
    }


    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.add_row_files,parent,false);
        RecyclerViewHolder holder = new RecyclerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.RecyclerViewHolder holder, int position) {

        Pictures listpicture = pictures.get(position);
        holder.name.setText(listpicture.getName());
        Bitmap myimg = BitmapFactory.decodeByteArray(listpicture.getPhotoPath(),0, listpicture.getPhotoPath().length);
        holder.imageViewTaken.setImageBitmap(myimg);
//        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
//        Bitmap myimg = BitmapFactory.decodeFile(listpicture.getPhotoPath(),bmOptions);
        holder.imageViewTaken.setImageBitmap(myimg);


    }
    @Override
    public int getItemCount() {
        return pictures.size();
    }
}