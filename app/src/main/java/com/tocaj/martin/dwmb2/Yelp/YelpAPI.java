package com.tocaj.martin.dwmb2.Yelp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import com.google.android.gms.maps.model.LatLng;
import com.tocaj.martin.dwmb2.Yelp.Models.Business;

import java.util.ArrayList;

/**
 * Created by Hiplobbe on 2015-10-20.
 */
public class YelpAPI {

    private static final String API_HOST = "api.yelp.com";
    private static final String DEFAULT_TERM = "beer";
    private static final String RADIUS= "1000";
    private static final String SEARCH_PATH = "/v2/search";
    private static final String BUSINESS_PATH = "/v2/business";

    /*
     * Update OAuth credentials below from the Yelp Developers API site:
     * http://www.yelp.com/developers/getting_started/api_access
     */
    private static final String CONSUMER_KEY = "VtAUMzlJ7nNZE98QtJRfCA";
    private static final String CONSUMER_SECRET = "bTuBemPWLFuXvtE2gKoPQjbA7_E";
    private static final String TOKEN = "0IPVHZWC3LDelwj6jBUNW5Vr29o3lFD5";
    private static final String TOKEN_SECRET = "KZ7sIYzeqLhhLA9qqaAHEu3zEng";

    OAuthService service;
    Token accessToken;

    /**
     * Setup the Yelp API OAuth credentials.
     *
     * @param consumerKey Consumer key
     * @param consumerSecret Consumer secret
     * @param token Token
     * @param tokenSecret Token secret
     */
    public YelpAPI(String consumerKey, String consumerSecret, String token, String tokenSecret) {
        this.service =
                new ServiceBuilder().provider(Auth.class).apiKey(consumerKey)
                        .apiSecret(consumerSecret).build();
        this.accessToken = new Token(token, tokenSecret);
    }

    /**
     * Creates and sends a request to the Search API by term and location.
     *
     * @param term <tt>String</tt> of the search term to be queried
     * @param location <tt>String</tt> of the location
     * @return <tt>String</tt> JSON Response
     */
    public String searchForBusinessesByLocation(String term, LatLng location) {
        OAuthRequest request = createOAuthRequest(SEARCH_PATH);
        request.addQuerystringParameter("term", term);
        request.addQuerystringParameter("radius_filter",RADIUS);
        request.addQuerystringParameter("sort","2");
        request.addQuerystringParameter("ll", location.latitude+","+location.longitude);
        return sendRequestAndGetResponse(request);
    }

    /**
     * Creates and sends a request to the Business API by business ID.
     * <p>
     * See <a href="http://www.yelp.com/developers/documentation/v2/business">Yelp Business API V2</a>
     * for more info.
     *
     * @param businessID <tt>String</tt> business ID of the requested business
     * @return <tt>String</tt> JSON Response
     */
    public String searchByBusinessId(String businessID) {
        OAuthRequest request = createOAuthRequest(BUSINESS_PATH + "/" + businessID);
        return sendRequestAndGetResponse(request);
    }

    /**
     * Creates and returns an {@link OAuthRequest} based on the API endpoint specified.
     *
     * @param path API endpoint to be queried
     * @return <tt>OAuthRequest</tt>
     */
    private OAuthRequest createOAuthRequest(String path) {
        OAuthRequest request = new OAuthRequest(Verb.GET, "https://" + API_HOST + path);
        return request;
    }

    /**
     * Sends an {@link OAuthRequest} and returns the {@link Response} body.
     *
     * @param request {@link OAuthRequest} corresponding to the API request
     * @return <tt>String</tt> body of API response
     */
    private String sendRequestAndGetResponse(OAuthRequest request) {
        System.out.println("Querying " + request.getCompleteUrl() + " ...");
        this.service.signRequest(this.accessToken, request);
        Response response = request.send();
        return response.getBody();
    }

    /**
     * Queries the Search API based on the command line arguments and takes the first result to query
     * the Business API.
     *
     * @param yelpApi <tt>YelpAPI</tt> service instance
     */
    private static ArrayList<Business> queryAPI(YelpAPI yelpApi,LatLng loc) {
        String searchResponseJSON =
                yelpApi.searchForBusinessesByLocation(DEFAULT_TERM, loc);

        JSONParser parser = new JSONParser();
        JSONObject response = null;
        try {
            response = (JSONObject) parser.parse(searchResponseJSON);
        } catch (ParseException pe) {
            System.out.println("Error: could not parse JSON response:");
            System.out.println(searchResponseJSON);
            System.exit(1);
        }

        JSONArray businesses = (JSONArray) response.get("businesses");

        if(businesses.size() > 0) {
            ArrayList<Business> list = new ArrayList<>();

            for (Object obj : businesses) {
                Business business = new Business((JSONObject) obj);
                list.add(business);
            }

            return list;
        }
        else
        {
            return null;
        }


//        String firstBusinessID = firstBusiness.get("id").toString();
//        System.out.println(String.format(
//                "%s businesses found, querying business info for the top result \"%s\" ...",
//                businesses.size(), firstBusinessID));
//
//        // Select the first business and display business details
//        String businessResponseJSON = yelpApi.searchByBusinessId(firstBusinessID.toString());
//        System.out.println(String.format("Result for business \"%s\" found:", firstBusinessID));
//        System.out.println(businessResponseJSON);
    }

    /**
     * Main entry for sample Yelp API requests.
     * <p>
     * After entering your OAuth credentials, execute <tt><b>run.sh</b></tt> to run this example.
     */
    public static void main(String[] args) {
        YelpAPI yelpApi = new YelpAPI(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET);
        queryAPI(yelpApi, new LatLng(55.7248935,13.1769561));
    }
}
