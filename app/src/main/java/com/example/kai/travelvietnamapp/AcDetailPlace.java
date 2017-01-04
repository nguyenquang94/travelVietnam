package com.example.kai.travelvietnamapp;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kai.travelvietnamapp.adapter.SlideImageAdapter;
import com.example.kai.travelvietnamapp.data.DatabaseConstants;
import com.example.kai.travelvietnamapp.data.TravelContract;

import java.util.ArrayList;

public class AcDetailPlace extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int LOADER_IMAGE = 1;
    private int placeId;
    private String placeName, addressVi, descriptionVi;
    /**
     * Textview show placename in toolbar activity
     */
    private TextView tvNamePlaceToolbar;
    private TextView tvNamePlace;
    private TextView tvDescription;
    private TextView tvAddress;
    private ViewPager viewPager;
    private ArrayList<String> listUrlImage;
    private SlideImageAdapter slideImageAdapter;
    private ImageView imgBack;

    private static final String[] IMAGE_DETAIL_COLUMNS = new String[]{
            DatabaseConstants.URL,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac_detail_place);
        init();
        getValuesFromBundle();
        getSupportLoaderManager().initLoader(LOADER_IMAGE, null, this);

        listUrlImage = new ArrayList<String>();
    }

    public void getValuesFromBundle() {
        Bundle bundle = getBundle();
        placeId = bundle.getInt(DatabaseConstants.PLACE_ID);
        placeName = bundle.getString(DatabaseConstants.NAME_PLACE_VI);
        tvNamePlace.setText(placeName);
        tvNamePlaceToolbar.setText(placeName);
        addressVi = bundle.getString(DatabaseConstants.ADDRESS_VI);
        tvAddress.setText(addressVi);
        descriptionVi = bundle.getString(DatabaseConstants.DESCRIPTION_VI);
        tvDescription.setText(descriptionVi);
    }

    public void init() {
        tvNamePlaceToolbar = (TextView) findViewById(R.id.namePlaceTB);
        tvNamePlace = (TextView) findViewById(R.id.namePlace);
        tvDescription = (TextView) findViewById(R.id.descriptionVi);
        tvAddress = (TextView) findViewById(R.id.addressPlace);
        viewPager = (ViewPager) findViewById(R.id.view_pager_detail);
        imgBack = (ImageView) findViewById(R.id.imgBack);
        tvDescription.setMovementMethod(new ScrollingMovementMethod());
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case LOADER_IMAGE:
                return new CursorLoader(this,
                        TravelContract.ImageEntry.CONTENT_URI, IMAGE_DETAIL_COLUMNS, DatabaseConstants.PLACE_ID_CRITERIA,
                        new String[]{String.valueOf(placeId)
                        }, null);
        }

        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        switch (loader.getId()) {
            case LOADER_IMAGE:
                GetImageDetail getImageDetail = new GetImageDetail();
                getImageDetail.execute(data);
                break;
        }
    }

    public class GetImageDetail extends AsyncTask<Cursor, Void, Void> {


        @Override
        protected Void doInBackground(Cursor... params) {
            Cursor cursor = params[0];
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    String urlImage = cursor.getString(cursor.getColumnIndex(DatabaseConstants.URL));
                    listUrlImage.add(urlImage);
                } while (cursor.moveToNext());
            }
            cursor.close();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            slideImageAdapter = new SlideImageAdapter(AcDetailPlace.this, listUrlImage);
            viewPager.setAdapter(slideImageAdapter);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    public Bundle getBundle() {
        Bundle bundle = this.getIntent().getExtras();
        if (bundle == null)
            bundle = new Bundle();
        return bundle;
    }
}
