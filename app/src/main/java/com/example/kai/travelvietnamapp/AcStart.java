package com.example.kai.travelvietnamapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.kai.travelvietnamapp.business.BuData;
import com.example.kai.travelvietnamapp.data.DatabaseConstants;
import com.example.kai.travelvietnamapp.data.TravelContract;
import com.example.kai.travelvietnamapp.entity.EnCategory;
import com.example.kai.travelvietnamapp.entity.EnCategoryResponse;
import com.example.kai.travelvietnamapp.entity.EnPlace;
import com.example.kai.travelvietnamapp.entity.EnPlaceResponse;
import com.example.kai.travelvietnamapp.rest.ApiClient;
import com.example.kai.travelvietnamapp.rest.ApiInterfaceCategory;
import com.example.kai.travelvietnamapp.rest.ApiInterfacePlace;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AcStart extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String TAG = AcStart.class.getSimpleName();
    private static final String[] CATEGORT_COLUMNS = {
            DatabaseConstants.CATEGORY_TABLE + "." + DatabaseConstants._ID,
            DatabaseConstants.NAME_VI,
    };

    private static final int GET_CATEGORY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_start);
        getSupportLoaderManager().initLoader(GET_CATEGORY, null, this);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case GET_CATEGORY:
                return new CursorLoader(this,
                        TravelContract.CategoryEntry.CONTENT_URI, CATEGORT_COLUMNS, null,
                        new String[]{null
                        }, null);
        }
        return null;

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        switch (loader.getId()) {
            case GET_CATEGORY:
                if (data.getCount() == 0) {
                    DowloadDataAsyncTask dowloadDataAsyncTask = new DowloadDataAsyncTask();
                    dowloadDataAsyncTask.execute();
                    Intent i = getBaseContext().getPackageManager()
                            .getLaunchIntentForPackage(getBaseContext().getPackageName() );

                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK );
                    startActivity(i);
                } else {
                    new Timer().schedule(new TimerTask() {
                        public void run() {

                            startActivity(new Intent(AcStart.this, AcMain.class));
                        }
                    }, 2000);
                }
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private class DowloadDataAsyncTask extends AsyncTask<Void, Boolean, Boolean> {
        boolean isUpdated;

        @Override
        protected Boolean doInBackground(Void... params) {

            ApiInterfaceCategory apiService = ApiClient.getClient().create(ApiInterfaceCategory.class);
            Call<EnCategoryResponse> call = apiService.getAll();
            call.enqueue(new Callback<EnCategoryResponse>() {
                @Override
                public void onResponse(Call<EnCategoryResponse> call, Response<EnCategoryResponse> response) {
                    List<EnCategory> categories = response.body().getData();
                    BuData.saveCategoryData(AcStart.this, categories);
                }

                @Override
                public void onFailure(Call<EnCategoryResponse> call, Throwable t) {
                    Log.e(TAG, t.toString());
                }
            });

            ApiInterfacePlace apiInterfaceCategory = ApiClient.getClient().create(ApiInterfacePlace.class);
            Call<EnPlaceResponse> enPlaceResponseCall = apiInterfaceCategory.getAll();
            enPlaceResponseCall.enqueue(new Callback<EnPlaceResponse>() {
                @Override
                public void onResponse(Call<EnPlaceResponse> call, Response<EnPlaceResponse> response) {
                    List<EnPlace> places = response.body().getData();
                    BuData.savePlaceData(AcStart.this, places);
                }

                @Override
                public void onFailure(Call<EnPlaceResponse> call, Throwable t) {
                    Log.e(TAG, t.toString());
                }
            });
            publishProgress(isUpdated);
            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            Intent intent = new Intent(AcStart.this, AcMain.class);
            startActivity(intent);
        }
    }
}
