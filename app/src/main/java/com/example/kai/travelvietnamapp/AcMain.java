package com.example.kai.travelvietnamapp;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kai.travelvietnamapp.adapter.DrawerAdapter;
import com.example.kai.travelvietnamapp.data.DatabaseConstants;
import com.example.kai.travelvietnamapp.data.TravelContract;
import com.example.kai.travelvietnamapp.entity.EnMenuDrawer;
import com.example.kai.travelvietnamapp.entity.EnPlace;
import com.example.kai.travelvietnamapp.intents.IntentManager;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import static android.os.Build.VERSION_CODES.M;

public class AcMain extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, LoaderManager.LoaderCallbacks<Cursor>,
        OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener, GoogleMap.OnMarkerClickListener, DrawerAdapter.OnClickInAdapter {

    private GoogleMap mGoogleMap;

    private MapFragment mMapFragment;

    private static final String TAG = AcMain.class.getSimpleName();

    private DrawerLayout drawer;

    private List<EnMenuDrawer> listEnMenuDrawers;

    private ListView lvCategory;

    private List<Integer> listIdCategory;

    private ArrayList<EnPlace> listEnPlaces;

    private ArrayList<LatLng> listMarker;

    private Marker markerPlace;

    private Cursor cursorPlace;

    private com.github.clans.fab.FloatingActionButton floatingActionButton;

    private static final String[] CATEGORT_COLUMNS = {
            DatabaseConstants.CATEGORY_TABLE + "." + DatabaseConstants._ID,
            DatabaseConstants.NAME_VI,
    };

    private static final String[] PLACE_COLUMNS = {
            DatabaseConstants.PLACE_TABLE + "." + DatabaseConstants._ID,
            DatabaseConstants.PLACE_TABLE + " . " + DatabaseConstants.NAME_PLACE_VI,
            DatabaseConstants.CATEGORY_ID,
            DatabaseConstants.ADDRESS_VI,
            DatabaseConstants.LATITUDE,
            DatabaseConstants.LONGITUDE,
            DatabaseConstants.DESCRIPTION_VI,
            DatabaseConstants.CONVE_IMAGE_TABLE + " . " + DatabaseConstants.URL,
            DatabaseConstants.CATEGORY_TABLE + " . " + DatabaseConstants.NAME_VI
    };


    private static final int GET_CATEGORY = 1;
    private static final int GET_PLACE = 2;

    ArrayList<EnPlace> listdemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMapFragment = MapFragment.newInstance();
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.myMap);
        mapFragment.getMapAsync(this);

        listEnMenuDrawers = new ArrayList<EnMenuDrawer>();
        listIdCategory = new ArrayList<Integer>();
        listEnPlaces = new ArrayList<EnPlace>();
        listMarker = new ArrayList<LatLng>();
        listdemo = new ArrayList<EnPlace>();
        lvCategory = (ListView) findViewById(R.id.left_drawer);
        //onclick floatting button to show navigation drawer
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        floatingActionButton = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fabIn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(Gravity.LEFT);
                }
            }
        });


        lvCategory.setDivider(null);
        lvCategory.setDividerHeight(0);
        DrawerAdapter drawerAdapter = new DrawerAdapter(AcMain.this, R.layout.custom_listview_drawer, listEnMenuDrawers);
        lvCategory.setAdapter(drawerAdapter);

        getSupportLoaderManager().initLoader(GET_CATEGORY, null, this);
        getSupportLoaderManager().initLoader(GET_PLACE, null, this);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case GET_CATEGORY:
                return new CursorLoader(this,
                        TravelContract.CategoryEntry.CONTENT_URI, CATEGORT_COLUMNS, null,
                        new String[]{null
                        }, null);
            case GET_PLACE:
                return new CursorLoader(this,
                        TravelContract.PlaceEntry.CONTENT_URI, PLACE_COLUMNS, null,
                        new String[]{null
                        }, null);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        switch (loader.getId()) {
            case GET_CATEGORY:
                getCategoryData(data);
                break;
            case GET_PLACE:
                cursorPlace = data;
                GetPlaceData getPlaceData = new GetPlaceData();
                getPlaceData.execute(data);
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onItemSelected(int position, boolean imgActive) {
        markerPlace.remove();
        mGoogleMap.clear();

        if (imgActive == false) {
            for (int i = 0; i < listEnPlaces.size(); i++) {
                if (listEnPlaces.get(i).getCategoryId() == position) {
                    listdemo.add(listEnPlaces.get(i));
                    listEnPlaces.remove(i);
                }
            }
        } else {
            for (int i = 0; i < listdemo.size(); i++) {
                if (listdemo.get(i).getCategoryId() == position) {
                    listEnPlaces.add(listdemo.get(i));
                }
            }
        }
        UpdateMarker updateMarker = new UpdateMarker();
        updateMarker.execute(listEnPlaces);
    }

    public class UpdateMarker extends AsyncTask<ArrayList<EnPlace>, Void, ArrayList<EnPlace>> {

        @Override
        protected ArrayList<EnPlace> doInBackground(ArrayList<EnPlace>... params) {
            ArrayList<EnPlace> arrayList = params[0];
            return arrayList;
        }

        @Override
        protected void onPostExecute(ArrayList<EnPlace> list) {
            for (int i = 0; i < list.size(); i++) {
                EnPlace enPlace = list.get(i);
                int resIdMarker = AppUtils.setImageId(enPlace.getCategoryId());
                markerPlace = mGoogleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(enPlace.getLatitude(), enPlace.getLongitude()))
                        .icon(BitmapDescriptorFactory.fromResource(resIdMarker)));
                markerPlace.setTag(enPlace);
            }
        }
    }

    public class GetPlaceData extends AsyncTask<Cursor, Object, ArrayList<EnPlace>> {

        @Override
        protected ArrayList<EnPlace> doInBackground(Cursor... params) {
            Cursor cursor = params[0];
            if (cursor.moveToFirst()) {
                do {
                    String categoryName = cursor.getString(cursor.getColumnIndex(DatabaseConstants.NAME_VI));
                    String placeName = cursor.getString(cursor.getColumnIndex(DatabaseConstants.NAME_PLACE_VI));
                    int placeId = cursor.getInt(cursor.getColumnIndex(DatabaseConstants._ID));
                    int categoryId = cursor.getInt(cursor.getColumnIndex(DatabaseConstants.CATEGORY_ID));
                    String urlImage = cursor.getString(cursor.getColumnIndex(DatabaseConstants.URL));
                    String addressVi = cursor.getString(cursor.getColumnIndex(DatabaseConstants.ADDRESS_VI));
                    double lat = cursor.getDouble(cursor.getColumnIndex(DatabaseConstants.LATITUDE));
                    double lon = cursor.getDouble(cursor.getColumnIndex(DatabaseConstants.LONGITUDE));
                    String description = cursor.getString(cursor.getColumnIndex(DatabaseConstants.DESCRIPTION_VI));
                    EnPlace enPlace = new EnPlace(placeId, placeName, categoryId, categoryName, lat, lon, urlImage, addressVi, description);
                    listEnPlaces.add(enPlace);
                    listMarker.add(new LatLng(lat, lon));
                } while (cursor.moveToNext());
            }
            return listEnPlaces;
        }

        @Override
        protected void onPostExecute(ArrayList<EnPlace> enPlaces) {
            super.onPostExecute(enPlaces);
            for (int i = 0; i < listEnPlaces.size(); i++) {
                EnPlace enPlace = listEnPlaces.get(i);
                int resIdMarker = AppUtils.setImageId(enPlace.getCategoryId());
                markerPlace = mGoogleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(enPlace.getLatitude(), enPlace.getLongitude()))
                        .icon(BitmapDescriptorFactory.fromResource(resIdMarker)));
                markerPlace.setTag(enPlace);
            }
        }
    }

    public void getCategoryData(Cursor cursor) {
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(DatabaseConstants._ID));
                listIdCategory.add(id);
                listEnMenuDrawers.add(new EnMenuDrawer(AppUtils.setImageFilterId(id)));
            } while (cursor.moveToNext());
        }
    }


    //Working Map add Marker
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= M) {
            if (ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                mGoogleMap.setMyLocationEnabled(true);

            }
        } else {
            mGoogleMap.setMyLocationEnabled(true);
        }
        getCurrentLocation();
        mGoogleMap.setOnInfoWindowClickListener(this);
        mGoogleMap.setOnMarkerClickListener(this);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Object ob = marker.getTag();
        if (ob instanceof EnPlace) {
            sendToDetail((EnPlace) ob);
        }
    }

    public void sendToDetail(EnPlace enPlace) {
        Bundle bundle = new Bundle();
        bundle.putInt(DatabaseConstants.PLACE_ID, enPlace.getId());
        bundle.putString(DatabaseConstants.NAME_PLACE_VI, enPlace.getNameVi());
        bundle.putString(DatabaseConstants.ADDRESS_VI, enPlace.getAddressVi());
        bundle.putString(DatabaseConstants.DESCRIPTION_VI, enPlace.getDescriptionVi());
        IntentManager.startActivity(this, AcDetailPlace.class, bundle, null);
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        mGoogleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                Object ob = marker.getTag();
                String urlImage = ((EnPlace) ob).getUrlImage();
                String placeName = ((EnPlace) ob).getNameVi();
                int placeId = ((EnPlace) ob).getId();
                String categoryName = ((EnPlace) ob).getCategoryName();
                String address = ((EnPlace) ob).getAddressVi();
                View view = getLayoutInflater().inflate(R.layout.custom_info_window, null);
                view.setLayoutParams(new LinearLayout.LayoutParams(200, 300));
                ImageView imgMarker = (ImageView) view.findViewById(R.id.imgPlace);
                TextView tvPlaceName = (TextView) view.findViewById(R.id.tvNamePlace);
                TextView tvCategoryName = (TextView) view.findViewById(R.id.tvNameCategory);
                TextView tvAddress = (TextView) view.findViewById(R.id.tvAddress);
                String url = R.string.url_image_small + urlImage;
                Glide.with(getApplicationContext())
                        .load(url)
                        .error(R.drawable.description)
                        .fitCenter()
                        .into(imgMarker);
                tvPlaceName.setText(placeName);
                tvCategoryName.setText(categoryName);
                tvAddress.setText(address);

                return view;
            }

            @Override
            public View getInfoContents(Marker marker) {
                return null;
            }
        });
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


       /* DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);*/
        return true;
    }

    private void getCurrentLocation() {

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location lastLocation = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        if (lastLocation != null) {
            mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude()), 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(21.0305568, 105.85241675))      // Sets the center of the map to location user
                    .zoom(15)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
