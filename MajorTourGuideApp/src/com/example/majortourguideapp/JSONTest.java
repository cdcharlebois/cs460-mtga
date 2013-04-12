package com.example.majortourguideapp;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class JSONTest extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dbtest);
		getJSONDirections(new LatLng(42.390041,-71.222726), new LatLng(42.357086,-71.185737));
	}

	//get directions
	private PolylineOptions getJSONDirections(LatLng start, LatLng stop){
		//ArrayList<LatLng> points = new ArrayList<LatLng>();
		/* === not_good === */
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		/* --- /not_good --- */
		String pair1 = "" + start.latitude + "," + start.longitude;
		String pair2 = "" + stop.latitude + "," + stop.longitude;
		String url = "https://maps.googleapis.com/maps/api/directions/json?" +
				"origin=" + pair1 + 
				"&destination=" + pair2 +
				"&sensor=false" +
				"&mode=walking";
		Log.i("cdc",url);
		System.out.println(url);
		try {
			JSONObject dir = JSONReader.readJsonFromUrl(url);
			//Log.i("cdc", dir.toString());
			//extract each end location {{ routes[0].legs[0].steps[<0...n>].end_location.<lat|lng> }}
			JSONArray routes = dir.getJSONArray("routes");
			//Log.i("cdc-routes", routes.toString());
			JSONObject myRoute = routes.getJSONObject(0);
			//Log.i("cdc-myRoute", myRoute.toString());
			JSONArray legs = myRoute.getJSONArray("legs");
			//Log.i("cdc-legs",legs.toString());
			JSONObject myLeg = legs.getJSONObject(0);
			//Log.i("cdc-myLeg", myLeg.toString());
			JSONArray steps = myLeg.getJSONArray("steps");
			Log.i("cdc-steps", steps.toString());
			PolylineOptions path = new PolylineOptions() //declare the polyline
			.color(R.color.blue)
			.width(10);
			for (int i=0; i<steps.length(); i++){
				double lat = steps.getJSONObject(i).getJSONObject("end_location").getDouble("lat");		//get lat
				double lng = steps.getJSONObject(i).getJSONObject("end_location").getDouble("lng");		//get lng
				Log.i("cdc-point","Lat: " + lat + " Lng: " + lng);
				path.add(new LatLng(lat,lng));						//add to polyline
			}
			return path;		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
