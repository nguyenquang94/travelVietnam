package com.example.kai.travelvietnamapp.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.kai.travelvietnamapp.R;

import java.util.List;


public class SlideImageAdapter extends PagerAdapter {

    Context mContext;

    private ImageView imageView;

    private List<String> listUrlImage;

    private LayoutInflater layoutInflater;


    public SlideImageAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.listUrlImage = list;
    }

    @Override
    public int getCount() {
        return listUrlImage.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int i) {
        layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.ac_swipe_image_layout, container, false);
        imageView = (ImageView) itemView.findViewById(R.id.swip_image_view);
        if (listUrlImage.size() == 0) {
            imageView.setImageResource(R.drawable.description);
        } else {
            String url = R.string.url_image_big + listUrlImage.get(i);
            Glide.with(mContext)
                    .load(url)
                    .crossFade()
                    .into(imageView);
        }

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int i, Object obj) {
        container.removeView((RelativeLayout) obj);
    }


}
