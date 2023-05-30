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
import com.example.billtrackermobile.models.DeviceModel;
import com.example.billtrackermobile.models.PromoModel;

import java.util.List;

public class DeviceAdaptor extends RecyclerView.Adapter<DeviceAdaptor.ViewHolder> {


    private Context context;

    private List<DeviceModel> deviceModelList;

    public DeviceAdaptor(Context context, List<DeviceModel> deviceModelList) {
        this.context = context;
        this.deviceModelList = deviceModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DeviceAdaptor.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.devices_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(deviceModelList.get(position).getImg_url()).into(holder.device_img);
        holder.name.setText(deviceModelList.get(position).getName());
        holder.description.setText(deviceModelList.get(position).getDescription());
        holder.price.setText(deviceModelList.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return deviceModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView device_img;
        TextView name, description, price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            device_img = itemView.findViewById(R.id.device_img);
            name = itemView.findViewById(R.id.device_name);
            description = itemView.findViewById(R.id.device_desc);
            price = itemView.findViewById(R.id.device_price);
        }
    }
}
