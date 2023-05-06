package com.example.billtrackermobile.Adaptors;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.billtrackermobile.MainActivity;
import com.example.billtrackermobile.R;
import com.example.billtrackermobile.models.TitleCategories;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TitleAdaptor extends RecyclerView.Adapter<TitleAdaptor.ViewHolder> {


    private static final String TAG = "TitleAdaptor";

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private Context context;

    public TitleAdaptor(Context context, ArrayList<String> mNames, ArrayList<String> mImageUrls) {
        this.mNames = mNames;
        this.mImageUrls = mImageUrls;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.title_cat_items, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");
        Glide.with(context)
                .asBitmap()
                .load(mImageUrls.get(position))
                .into(holder.titleImage);
    }

    @Override
    public int getItemCount() {
        return mImageUrls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView titleImage;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleImage = itemView.findViewById(R.id.title_cat_img);
            name = itemView.findViewById(R.id.cat_title_name);
        }
    }


}
