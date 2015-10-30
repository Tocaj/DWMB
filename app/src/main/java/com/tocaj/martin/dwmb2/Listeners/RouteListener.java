package com.tocaj.martin.dwmb2.Listeners;

import android.graphics.Color;

import com.directions.route.Route;
import com.directions.route.RoutingListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.tocaj.martin.dwmb2.R;

import java.util.ArrayList;

/**
 * Created by Hiplobbe on 2015-10-30.
 */
public class RouteListener implements RoutingListener
{
    ArrayList<Polyline> polylines = new ArrayList<>();
    GoogleMap map;

    public RouteListener(GoogleMap mMap)
    {
        map = mMap;
    }

    @Override
    public void onRoutingFailure() {

    }

    @Override
    public void onRoutingStart() {

    }

    @Override
    public void onRoutingSuccess(ArrayList<Route> route, int bestRouteIndex) {
        if(polylines.size()>0) {
            for (Polyline poly : polylines) {
                poly.remove();
            }
        }

        polylines = new ArrayList<>();

        //add route(s) to the map.
        for (int i = 0; i <route.size(); i++) {
            PolylineOptions polyOptions = new PolylineOptions();
            polyOptions.color(Color.BLUE);
            polyOptions.width(10 + i * 3);
            polyOptions.addAll(route.get(i).getPoints());
            Polyline polyline = map.addPolyline(polyOptions);
            polylines.add(polyline);
        }
    }

    @Override
    public void onRoutingCancelled() {

    }
}
