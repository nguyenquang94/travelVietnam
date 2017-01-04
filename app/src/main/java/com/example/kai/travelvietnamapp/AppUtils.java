package com.example.kai.travelvietnamapp;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.example.kai.travelvietnamapp.data.DatabaseConstants;

import java.io.InputStream;

/**
 * Created by Kai on 1/3/2017.
 */

public class AppUtils {
    /**
     * Set image with categoryId
     *
     * @param categoryId
     * @return
     */
    public static int setImageId(int categoryId) {
        int resImageId = 0;
        switch (categoryId) {
            case 1:
                resImageId = R.mipmap.category1;
                break;
            case 2:
                resImageId = R.mipmap.category2;
                break;
            case 3:
                resImageId = R.mipmap.category3;
                break;
            case 4:
                resImageId = R.mipmap.category4;
                break;
            case 5:
                resImageId = R.mipmap.category5;
                break;
            case 6:
                resImageId = R.mipmap.category6;
                break;
            case 7:
                resImageId = R.mipmap.category7;
                break;
            case 8:
                resImageId = R.mipmap.category8;
                break;
            case 9:
                resImageId = R.mipmap.category9;
                break;
            case 10:
                resImageId = R.mipmap.category10;
                break;
            case 11:
                resImageId = R.mipmap.category11;
                break;
            case 12:
                resImageId = R.mipmap.category12;
                break;
            case 13:
                resImageId = R.mipmap.category13;
                break;
        }
        return resImageId;
    }

    /**
     * Set image Filter with categoryId
     *
     * @param categoryId
     * @return
     */
    public static int setImageFilterId(int categoryId) {
        int resImageId = 0;
        switch (categoryId) {
            case 1:
                resImageId = R.drawable.pagoda;
                break;
            case 2:
                resImageId = R.drawable.scene;
                break;
            case 3:
                resImageId = R.drawable.fuel;
                break;
            case 4:
                resImageId = R.drawable.restaurant;
                break;
            case 5:
                resImageId = R.drawable.atm;
                break;
        }
        return resImageId;
    }

    /**
     * Quangnv
     * Get Place name with place id
     *
     * @param cursor
     * @return Place name
     */
    public static String getPlaceName(Cursor cursor) {
        String placeName = new String();
        if (cursor != null && cursor.moveToFirst()) {
            placeName = cursor.getString(cursor.getColumnIndex(DatabaseConstants.NAME_PLACE_VI));
        }
        return placeName;
    }

    public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}
