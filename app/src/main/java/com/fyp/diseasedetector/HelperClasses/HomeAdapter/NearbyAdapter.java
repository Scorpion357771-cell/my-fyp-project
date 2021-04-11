package com.fyp.diseasedetector.HelperClasses.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.diseasedetector.R;

import java.util.ArrayList;

public class NearbyAdapter extends RecyclerView.Adapter<NearbyAdapter.NearbyViewHolder> {

    ArrayList<NearbyHelperClass> nearbyDoctors;

    //Constructor
    public NearbyAdapter(ArrayList<NearbyHelperClass> nearbyDoctors) {
        this.nearbyDoctors = nearbyDoctors;
    }

    @NonNull
    @Override
    public NearbyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.near_by_card_design, parent,false);
        NearbyViewHolder nearbyViewHolder = new NearbyViewHolder(view);
        return nearbyViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NearbyViewHolder holder, int position) {

        NearbyHelperClass nearbyHelperClass = nearbyDoctors.get(position);
        holder.image.setImageResource(nearbyHelperClass.getImage());
        holder.title.setText(nearbyHelperClass.getTitle());
        holder.desc.setText(nearbyHelperClass.getDescription());

    }

    @Override
    public int getItemCount() {
        return nearbyDoctors.size();
    }


    public static class NearbyViewHolder extends RecyclerView.ViewHolder {


        ImageView image;
        TextView title, desc;

        public NearbyViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks

            image = itemView.findViewById(R.id.nearby_image);
            title = itemView.findViewById(R.id.nearby_title);
            desc = itemView.findViewById(R.id.nearby_desc);



        }
    }


}
