package com.example.genesiscinemas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater inflater;

    public SliderAdapter(Context context) {
        this.context = context;

    }

    int images [] = {
            R.drawable.catpng,
            R.drawable.queue,
            R.drawable.movienp,
            R.drawable.cinemapng
    };

    int headings[] = {

            R.string.first_slid_tittle,
            R.string.second_slid_tittle,
            R.string.third_slid_tittle,
            R.string.fourth_slid_tittle


    };
    int descriptions[] = {

            R.string.first_slid_desc,
            R.string.second_slid_desc,
            R.string.third_slid_desc,
            R.string.fourth_slid_desc,

    };

    @Override
    public int getCount() {
        return headings.length ;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==(RelativeLayout)object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
       container.removeView((RelativeLayout)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater .inflate(R.layout.slides_layout,container,false);
        ImageView  imageView= view.findViewById(R.id.slider_img);
        TextView heading= view.findViewById(R.id.txtheading);
        TextView description = view.findViewById(R.id.txt_desc);
            imageView.setImageResource(images[position]);
            heading.setText(headings[position]);
            description.setText(descriptions[position]);

            container.addView(view);
        return view;


    }

}