package com.fyp.diseasedetector.HelperClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.fyp.diseasedetector.R;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    int images[] = {
            R.drawable.need_a_doctor_onboarding,
            R.drawable.book_appointment_onboarding,
            R.drawable.meet_doctor_onboarding,
            R.drawable.chatbot_onboarding
    };

    int headings[] = {
            R.string.first_slider_title,
            R.string.sec_slider_title,
            R.string.third_slider_title,
            R.string.fourth_slider_title
    };
    int description[] = {
            R.string.first_slider_desc,
            R.string.sec_slider_desc,
            R.string.third_slider_desc,
            R.string.fourth_slider_desc
    };

    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slides_layout,container, false);

        ImageView imageView = view.findViewById(R.id.slider_image);
        TextView heading = view.findViewById(R.id.slider_heading);
        TextView desc = view.findViewById(R.id.slider_desc);

        imageView.setImageResource(images[position]);
        heading.setText(headings[position]);
        desc.setText(description[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
