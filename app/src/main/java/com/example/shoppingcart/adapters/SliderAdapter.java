package com.example.shoppingcart.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import com.example.shoppingcart.R;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context=context;
    }

    public int[] slideImages = {
            R.drawable.food1,
            R.drawable.food2,
            R.drawable.food3
    };

    public String[] slideDescriptions ={
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin nibh justo, lobortis vitae lacinia et, scelerisque eu mauris. Morbi euismod scelerisque orci sit amet iaculis.Fusce mollis turpis at aliquam dapibus. In convallis malesuada arcu non consequat. Proin mattis luctus arcu, et vulputate orci lobortis sed.",
            "Fusce mollis turpis at aliquam dapibus. In convallis malesuada arcu non consequat. Proin mattis luctus arcu, et vulputate orci lobortis sed.In hac habitasse platea dictumst. Duis laoreet metus et dui iaculis aliquam. Vestibulum sit amet lorem eget turpis sollicitudin volutpat.",
            "In hac habitasse platea dictumst. Duis laoreet metus et dui iaculis aliquam. Vestibulum sit amet lorem eget turpis sollicitudin volutpat. In hac habitasse platea dictumst. Duis laoreet metus et dui iaculis aliquam. Vestibulum sit amet lorem eget turpis sollicitudin volutpat."
    };

    public String[] slideHeads ={
            "Lorem ipsum dolor sit amet,",
            "Fusce mollis turpis at aliquam ",
            "In hac habitasse platea dictumst. "
    };


    @Override
    public int getCount() {
        return slideImages.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (LinearLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.iv_image_icon);
        TextView slideDescription = (TextView) view.findViewById(R.id.tv_description);
        TextView sliderHead = (TextView) view.findViewById(R.id.tv_head);

        slideImageView.setImageResource(slideImages[position]);
        slideDescription.setText(slideDescriptions[position]);
        sliderHead.setText(slideHeads[position]);

        container.addView(view);

        return view;

    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);  //todo: RelativeLayout??
    }
}
