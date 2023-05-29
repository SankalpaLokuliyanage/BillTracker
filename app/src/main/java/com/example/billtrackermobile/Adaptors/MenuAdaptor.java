package com.example.billtrackermobile.Adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.billtrackermobile.R;
import com.example.billtrackermobile.models.MenuModel;
import com.example.billtrackermobile.models.PromoModel;

import java.util.List;

public class MenuAdaptor extends RecyclerView.Adapter<MenuAdaptor.ViewHolder>{

    private Context context;
    private List<MenuModel> menuModelList;


    public MenuAdaptor(Context context, List<MenuModel> menuModelList) {
        this.context = context;
        this.menuModelList = menuModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MenuAdaptor.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_cat, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(menuModelList.get(position).getImg_url()).into(holder.MenuImage);
        holder.name.setText(menuModelList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return menuModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView MenuImage;
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            MenuImage = itemView.findViewById(R.id.menu_cat_img);
            name = itemView.findViewById(R.id.menu_cat_name);
        }
    }
}
