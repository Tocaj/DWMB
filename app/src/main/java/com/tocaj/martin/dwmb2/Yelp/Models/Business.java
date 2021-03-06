package com.tocaj.martin.dwmb2.Yelp.Models;

import com.google.android.gms.maps.model.LatLng;

import org.json.simple.JSONObject;

/**
 * Class to represent the businesses
 *
 * Created by Hiplobbe on 2015-10-20.
 */
public class Business
{
    public String name;
    public double rating;
    public LatLng location;

    public Business(JSONObject obj)
    {
        name = (String)obj.get("name");
        rating = Double.parseDouble(obj.get("rating").toString());

        setLocation((JSONObject)obj.get("location"));
    }

    public void setLocation(JSONObject obj)
    {
        obj = (JSONObject)obj.get("coordinate");

        double latitude = Double.parseDouble(obj.get("latitude").toString());
        double longitude = Double.parseDouble(obj.get("longitude").toString());

        location = new LatLng(latitude,longitude);
    }
}
