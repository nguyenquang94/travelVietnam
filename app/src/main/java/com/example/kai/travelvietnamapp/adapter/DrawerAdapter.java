package com.example.kai.travelvietnamapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.kai.travelvietnamapp.AcMain;
import com.example.kai.travelvietnamapp.R;
import com.example.kai.travelvietnamapp.entity.EnMenuDrawer;

import java.util.List;

/**
 * Created by Kai on 4/2/2017.
 */

public class DrawerAdapter extends ArrayAdapter<EnMenuDrawer> {
    private Context context;
    private int resource;
    private List<EnMenuDrawer> objects;
    private int positionImage;
    OnClickInAdapter mCallback;
    boolean imgActive;
    private int countClick = 0;

    public DrawerAdapter(Context context, int resource, List<EnMenuDrawer> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
        try {
            this.mCallback = ((AcMain) context);
        } catch (ClassCastException e) {
            throw new ClassCastException(e.toString());
        }
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final View view = View.inflate(context, resource, null);
        ImageView mImageView = (ImageView) view.findViewById(R.id.img_category);
        EnMenuDrawer item = objects.get(position);
        mImageView.setImageResource(item.getmImage());

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countClick += 1;
                if (countClick % 2 == 0) {
                    view.setAlpha(1f);
                    imgActive = true;
                    mCallback.onItemSelected(position + 1, imgActive);

                } else {
                    view.setAlpha(0.3f);
                    imgActive = false;
                    mCallback.onItemSelected(position + 1, imgActive);
                }
            }
        });
        return view;
    }


    public interface OnClickInAdapter {
        void onItemSelected(int position, boolean imgActive);
    }


}
