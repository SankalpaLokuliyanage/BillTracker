package com.example.billtrackermobile.semipages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.billtrackermobile.Adaptors.DeviceAdaptor;
import com.example.billtrackermobile.R;
import com.example.billtrackermobile.models.DeviceModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DevicesActivity extends AppCompatActivity {

    FirebaseFirestore firestore;
    RecyclerView recyclerView;
    DeviceAdaptor deviceAdaptor;
    List<DeviceModel> deviceModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices);

        firestore = FirebaseFirestore.getInstance();
        String type = getIntent().getStringExtra("type");
        recyclerView = findViewById(R.id.devices_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        deviceModelList = new ArrayList<>();
        deviceAdaptor = new DeviceAdaptor(this, deviceModelList);

        if (type != null && type.equalsIgnoreCase("device")){
            firestore.collection("Devices").whereEqualTo("type", "device").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()) {
                        DeviceModel deviceModel = documentSnapshot.toObject(DeviceModel.class);
                        deviceModelList.add(deviceModel);
                        deviceAdaptor.notifyDataSetChanged();
                    }
                }
            });
        }
    }
}