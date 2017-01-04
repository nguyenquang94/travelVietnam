package com.example.kai.travelvietnamapp.business;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import com.example.kai.travelvietnamapp.data.DatabaseConstants;
import com.example.kai.travelvietnamapp.data.TravelContract;
import com.example.kai.travelvietnamapp.entity.EnCategory;
import com.example.kai.travelvietnamapp.entity.EnImage;
import com.example.kai.travelvietnamapp.entity.EnPivot;
import com.example.kai.travelvietnamapp.entity.EnPlace;

import java.util.List;

/**
 * Created by Kai on 01/03/2017.
 */

public class BuData {
    private static final String LOG_TAG = BuData.class.getSimpleName();

    public static Uri saveCategoryData(Context context, List<EnCategory> listCategory) {
        ContentValues cv = new ContentValues();
        if (context == null || listCategory.size() == 0) {
            return null;
        }
        for (int i = 0; i < listCategory.size(); i++) {
            cv.put(DatabaseConstants._ID, listCategory.get(i).getId());
            cv.put(DatabaseConstants.NAME_VI, listCategory.get(i).getNameVi());
            cv.put(DatabaseConstants.NAME_EN, listCategory.get(i).getNameEn());
            cv.put(DatabaseConstants.ICON_ID, listCategory.get(i).getIconId());
            cv.put(DatabaseConstants.CREATE_AT, listCategory.get(i).getCreateAt());
            cv.put(DatabaseConstants.UPDATE_AT, listCategory.get(i).getUpdateAt());
            cv.put(DatabaseConstants.ICON_SMALL_ID, listCategory.get(i).getIconSmallId());
            cv.put(DatabaseConstants.ICON, listCategory.get(i).getIcon());
            cv.put(DatabaseConstants.ICON_SMALL, listCategory.get(i).getIconSmall());
            context.getContentResolver().insert(TravelContract.CategoryEntry.CONTENT_URI, cv);
        }
        return null;

    }

    public static boolean savePlaceData(Context context, List<EnPlace> listEnPlace) {
        ContentValues cv = new ContentValues();
        ContentValues cvImage = new ContentValues();
        ContentValues cvImageConver = new ContentValues();
        List<EnImage> listImage;
        List<EnImage> listConver;
        EnPivot enPivot = new EnPivot();
        if (context == null || listEnPlace.size() == 0) {
            return false;
        }
        for (int i = 0; i < listEnPlace.size(); i++) {
            cv.put(DatabaseConstants._ID, listEnPlace.get(i).getId());
            cv.put(DatabaseConstants.NAME_PLACE_VI, listEnPlace.get(i).getNameVi());
            cv.put(DatabaseConstants.NAME_PLACE_EN, listEnPlace.get(i).getNameEn());
            cv.put(DatabaseConstants.CATEGORY_ID, listEnPlace.get(i).getCategoryId());
            cv.put(DatabaseConstants.DESCRIPTION_VI, listEnPlace.get(i).getDescriptionVi());
            cv.put(DatabaseConstants.DESCRIPTION_EN, listEnPlace.get(i).getDescriptionEn());
            cv.put(DatabaseConstants.SHORT_DESCRIPTION_VI, listEnPlace.get(i).getShortDescriptionVi());
            cv.put(DatabaseConstants.SHORT_DESCRIPTION_EN, listEnPlace.get(i).getShortDescriptionEn());
            cv.put(DatabaseConstants.ENABLE_VI, listEnPlace.get(i).getEnableVi());
            cv.put(DatabaseConstants.ENABLE_EN, listEnPlace.get(i).getEnableEn());
            cv.put(DatabaseConstants.NAME_IN_URL, listEnPlace.get(i).getNameinUrl());
            cv.put(DatabaseConstants.ADDRESS_VI, listEnPlace.get(i).getAddressVi());
            cv.put(DatabaseConstants.ADDRESS_EN, listEnPlace.get(i).getAddressEn());
            cv.put(DatabaseConstants.LATITUDE, listEnPlace.get(i).getLatitude());
            cv.put(DatabaseConstants.LONGITUDE, listEnPlace.get(i).getLongitude());

            listImage = listEnPlace.get(i).getListImage();
            for (int j = 0; j < listImage.size(); j++) {
                enPivot = listImage.get(j).getEnPivot();
                cvImage.put(DatabaseConstants._ID, listImage.get(j).getId());
                cvImage.put(DatabaseConstants.URL, listImage.get(j).getUrl());
                cvImage.put(DatabaseConstants.CREATE_AT, listImage.get(j).getCreateAt());
                cvImage.put(DatabaseConstants.UPDATE_AT, listImage.get(j).getUpdateAt());
                cvImage.put(DatabaseConstants.O_HEIGHT, listImage.get(j).getoHeight());
                cvImage.put(DatabaseConstants.O_WEIGHT, listImage.get(j).getoWight());
                cvImage.put(DatabaseConstants.O_SIZE, listImage.get(j).getoSize());
                cvImage.put(DatabaseConstants.DOMINATION_COLOR, listImage.get(j).getDomination_color());
                cvImage.put(DatabaseConstants.PLACE_ID, enPivot.getPlaceId());
                context.getContentResolver().insert(TravelContract.ImageEntry.CONTENT_URI, cvImage);
            }
            listConver = listEnPlace.get(i).getCover();
            for (int k = 0; k < listConver.size(); k++) {
                enPivot = listConver.get(k).getEnPivot();
                cvImageConver.put(DatabaseConstants._ID, listConver.get(k).getId());
                cvImageConver.put(DatabaseConstants.URL, listConver.get(k).getUrl());
                cvImageConver.put(DatabaseConstants.CREATE_AT, listConver.get(k).getCreateAt());
                cvImageConver.put(DatabaseConstants.UPDATE_AT, listConver.get(k).getUpdateAt());
                cvImageConver.put(DatabaseConstants.O_HEIGHT, listConver.get(k).getoHeight());
                cvImageConver.put(DatabaseConstants.O_WEIGHT, listConver.get(k).getoWight());
                cvImageConver.put(DatabaseConstants.O_SIZE, listConver.get(k).getoSize());
                cvImageConver.put(DatabaseConstants.DOMINATION_COLOR, listConver.get(k).getDomination_color());
                cvImageConver.put(DatabaseConstants.PLACE_ID, DatabaseConstants.TYPE_IMAGE_CONSTANTS);
                cvImageConver.put(DatabaseConstants.PLACE_ID, enPivot.getPlaceId());
                context.getContentResolver().insert(TravelContract.CoverImageEntry.CONTENT_URI, cvImageConver);
            }
            context.getContentResolver().insert(TravelContract.PlaceEntry.CONTENT_URI, cv);
        }
        return true;

    }
}
