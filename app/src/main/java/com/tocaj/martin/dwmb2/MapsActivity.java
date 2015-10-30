package com.tocaj.martin.dwmb2;

import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.Marker;
import com.tocaj.martin.dwmb2.Yelp.Models.Business;
import com.tocaj.martin.dwmb2.Yelp.YelpAPI;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private LocationManager mLocationManager;
    private Location location;
//    private GoogleMap.InfoWindowAdapter IWA = new GoogleMap.InfoWindowAdapter() {
//        @Override
//        public View getInfoWindow(Marker marker) {
//            return null;
//        }
//
//        @Override
//        public View getInfoContents(Marker marker) {
//            return null;
//        }
//    };

    public LocationListener mLocationListener = new LocationListener() {

        @Override
        public void onLocationChanged(final Location location) {
            MapsActivity.this.location = location;

            //TODO Not do this step when the user has chosen a target destination.
            setUpMap();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        setupUserLocation();

        setUpMapIfNeeded();
    }

    private void setupUserLocation() {
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);



        // Creating a criteria object to retrieve provider
        Criteria criteria = new Criteria();

        // Getting the name of the best provider
        String provider = mLocationManager.getBestProvider(criteria, true);

        mLocationManager.requestLocationUpdates(provider, 1000, 10, mLocationListener);

        location = mLocationManager.getLastKnownLocation(provider);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).title("Vi"));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 13));

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                YelpAPI api = new YelpAPI();
                final ArrayList<Business> list = api.queryAPI(api,new LatLng(location.getLatitude(), location.getLongitude()));

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        for(Business b : list)
                        {
                            final Business business = b;
                            GoogleMap.InfoWindowAdapter IWA = new GoogleMap.InfoWindowAdapter() {
                                @Override
                                public View getInfoWindow(Marker marker) {
                                    TextView tv = new TextView(getApplicationContext());
                                    tv.setText("Namn: " + business.name + "\n" + "Rating: " + business.rating);
                                    tv.setBackgroundColor(Color.BLACK);
                                    tv.setAlpha(1);

                                    return tv;
                                }

                                @Override
                                public View getInfoContents(Marker marker) {
//                                    TextView tv = new TextView(getApplicationContext());
//                                    tv.setText("Namn: " + business.name + "\n" + "Rating: " + business.rating);
//                                    return tv;
                                    return null;
                                }
                            };

                            MarkerOptions m = new MarkerOptions().position(new LatLng(b.location.latitude, b.location.longitude)).title(b.name);
                            mMap.setInfoWindowAdapter(IWA);

                            mMap.addMarker(m);
                        }
                    }
                });
            }
        });

        t.start();
    }
}
