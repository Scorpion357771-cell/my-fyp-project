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

public class BestDoctorAdapter extends RecyclerView.Adapter<BestDoctorAdapter.BestDoctorViewHolder> {

    ArrayList<BestDoctorHelperClass> bestDoctorLocations;

    public BestDoctorAdapter(ArrayList<BestDoctorHelperClass> bestDoctorLocations) {
        this.bestDoctorLocations = bestDoctorLocations;
    }

    @NonNull
    @Override
    public BestDoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.best_card_design,parent,false);
        BestDoctorViewHolder bestDoctorViewHolder = new BestDoctorViewHolder(view);
        return bestDoctorViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BestDoctorViewHolder holder, int position) {

        BestDoctorHelperClass bestDoctorHelperClass = bestDoctorLocations.get(position);
        holder.image.setImageResource(bestDoctorHelperClass.getImage());
        holder.title.setText(bestDoctorHelperClass.getTitle());
        holder.desc.setText(bestDoctorHelperClass.getDescription());

    }

    @Override
    public int getItemCount() {
        return bestDoctorLocations.size();
    }


    public static class BestDoctorViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title, desc;

        public BestDoctorViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            image = itemView.findViewById(R.id.best_doctor_img);
            title = itemView.findViewById(R.id.best_doctor_title);
            desc = itemView.findViewById(R.id.best_doctor_desc);

        }
    }


}
