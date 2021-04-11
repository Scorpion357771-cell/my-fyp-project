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

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    ArrayList<CategoryHelperClass> doctorsCategory;


    //Constructor
    public CategoryAdapter(ArrayList<CategoryHelperClass> doctorsCategory) {
        this.doctorsCategory = doctorsCategory;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_card_design,parent,false);
        CategoryViewHolder categoryViewHolder = new CategoryViewHolder(view);
        return categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {

        CategoryHelperClass categoryHelperClass = doctorsCategory.get(position);
        holder.image.setImageResource(categoryHelperClass.getImage());
        holder.title.setText(categoryHelperClass.getTitle());

    }

    @Override
    public int getItemCount() {
        return doctorsCategory.size();
    }


    public static class CategoryViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);


            //Hooks
            image = itemView.findViewById(R.id.cate_img);
            title = itemView.findViewById(R.id.cate_title);

        }
    }

}
