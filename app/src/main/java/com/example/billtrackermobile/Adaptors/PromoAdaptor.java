package com.example.billtrackermobile.Adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.billtrackermobile.R;
import com.example.billtrackermobile.models.PromoModel;

import java.util.List;

public class PromoAdaptor extends RecyclerView.Adapter<PromoAdaptor.ViewHolder> {

    private Context context;
    private List<PromoModel> promoModelList;

    public PromoAdaptor(Context context, List<PromoModel> promoModelList) {
        this.context = context;
        this.promoModelList = promoModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.company_promos, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(promoModelList.get(position).getImg_url()).into(holder.promoImage);
    }

    @Override
    public int getItemCount() {
        return promoModelList.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{
        ImageView promoImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            promoImage = itemView.findViewById(R.id.promos);
        }
    }
}
