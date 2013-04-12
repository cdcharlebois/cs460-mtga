/**
 * ====== ========= ======
 * ------ FIND.JAVA ------
 * ====== ========= ======
 * Extends: Activity
 * Implements: OnItemSelectedListner
 * Methods:
 * 	=== onCreate ===
 * 	-> Android lifecycle method to inflate the layout and set listeners
 * 	=== onCreateOptionsMenu ===
 * 	-> used to modify navigation buttons
 * 	=== onOptionsItemSelected ===
 * 	-> sets the snippet at the bottom of the screen to match the selected destination
 * 	=== onItemsSelected ===
 * 	=== onNothingSelected ===
 * 
 * @author CS480/460 Team A
 * 	=== MODIFICATIONS ===
 * 	04/04/2013 -> added code to match snippet to destination
 * 	04/12/2013 -> added polyline support, organized code to be more modular 
 * 
 */

package com.example.majortourguideapp;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.majortourguideapp.MyLocation.LocationResult;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.CameraUpdateFactory;


public class Find extends Activity implements OnItemSelectedListener {
	private Spinner spinner;
	int major;
	private ImageView img;
	private ArrayList<destination_model> destinations = new ArrayList<destination_model>();
	private ArrayAdapter<String> aa;
	private GoogleMap map;
	private LatLng position = new LatLng(42.38781,-71.22008);
	private TextView blurb;
	private int sel;	//currently selected item from the spinner
	private LatLng curr_location;	//user's location


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.activity_find);


		/* just a test of navigation
		String locationUrl = "google.navigation:q=42.38753,-71.2204";
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(locationUrl));
		startActivity(intent); */

		//inflate layout
		spinner = (Spinner) findViewById(R.id.spinner1);
		blurb = (TextView) findViewById(R.id.map_blurb);
		map = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();


		//initialize
		major = getIntent().getExtras().getInt("major");
		map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
		//map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		Log.i("cdc", "map set to sattelite");
		map.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 17.0f));


		//draw the current position
		//--see below
		// Use the LocationManager class to obtain GPS locations 
		LocationManager locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		LocationListener locListener = new MyLocationListener();

		//Register for location updates using the named provider, and a pending intent.
		//3 second minimum interval between updates, 0 meters minimum distance between updates
		locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 0,
				locListener);



		//populate the list of destinations from the major
		//--would be good to thread this
		DB_Helper db = new DB_Helper(this);
		Cursor c = db.selectFromXwhereY(DB_Contract.Destination.TABLE_NAME, DB_Contract.Destination.COLUMN_MAJOR+" = "+major);
		c.moveToFirst();
		do{
			String destName = c.getString(c.getColumnIndexOrThrow(DB_Contract.Destination.COLUMN_NAME));
			String destId = c.getString(c.getColumnIndexOrThrow(DB_Contract.Destination.COLUMN_LOCATION));
			String destBlurb = c.getString(c.getColumnIndexOrThrow(DB_Contract.Destination.COLUMN_BLURB));
			destinations.add(new destination_model(destName,destId,destBlurb,this));
			c.moveToNext();
		}while(!c.isAfterLast());
		db.close();

		//assign the arrayList to the adapter.
		ArrayAdapter<destination_model> aa = new ArrayAdapter<destination_model>(
				this,
				android.R.layout.simple_list_item_1,
				destinations);
		aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(aa);
		spinner.setOnItemSelectedListener(this);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_find, menu);
		return true;
	}



	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	/**
	 * NavBar Up navigation
	 */
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This is called when the Home (Up) button is pressed
			// in the Action Bar.
			Intent parentActivityIntent = new Intent();
			parentActivityIntent.setComponent(new ComponentName("com.example.majortourguideapp",
					"com.example.majortourguideapp.Fork"));
			parentActivityIntent.addFlags(
					Intent.FLAG_ACTIVITY_CLEAR_TOP |
					Intent.FLAG_ACTIVITY_NEW_TASK);
			parentActivityIntent.putExtra("major", major);	//since Fork requires a major, give it back
			startActivity(parentActivityIntent);
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/* (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemSelectedListener#onItemSelected(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemSelected(AdapterView<?> arg0, View v, int pos,
			long arg3) {
		map.clear(); 	//clear current markers
		Log.i("cdc", "View = " + v + " pos = " + pos);
		String selBlurb = destinations.get(pos).getBlurb();
		Log.i("cdc", "selected = "+destinations.get(pos)+ ", selBlurb = "+selBlurb);
		blurb.setText(selBlurb);
		map.addMarker(getEntranceMarker(pos));
		map.addMarker(getStartMarker(curr_location));
		map.addPolyline(getPolyline(curr_location,destinations.get(pos).getEntrance()));


	}

	/* (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemSelectedListener#onNothingSelected(android.widget.AdapterView)
	 */
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}
	private MarkerOptions getStartMarker(LatLng here){
		double lat = 42.390041, lng = -71.222726;
		if(here != null){
			lat = here.latitude;
			lng = here.longitude;
			Log.i("cdc", "adding current marker");
		}
		curr_location = new LatLng(lat,lng);
		MarkerOptions m = new MarkerOptions()
		.position(new LatLng(curr_location.latitude, curr_location.longitude))
		.title("You")
		.snippet("are here")
		.icon(BitmapDescriptorFactory.fromResource(R.drawable.green_pin));
		return m;
	}

	private MarkerOptions getEntranceMarker(int posInList){
		/* find a better way to do this */


		MarkerOptions m = new MarkerOptions()
		.position(destinations.get(posInList).getEntrance())
		.title(destinations.get(posInList).getDestination_name())
		.snippet("enter here")
		.icon(BitmapDescriptorFactory.fromResource(R.drawable.red_pin));
		return m;
	}



	public class MyLocationListener implements LocationListener {

		@Override
		public void onLocationChanged(Location loc) {

			loc.getLatitude();
			loc.getLongitude();

			//			curr_location.latitude = loc.getLatitude();
			//			curr_location.longitude = loc.getLongitude();
			curr_location = new LatLng(loc.getLatitude(),loc.getLongitude());
		}

		@Override
		public void onProviderDisabled(String provider) {
			Toast.makeText(getApplicationContext(), "Gps Disabled",
					Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onProviderEnabled(String provider) {
			Toast.makeText(getApplicationContext(),
					"Gps Enabled", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {

		}

	}// End MyLocationListener

	//get directions
	/**
	 * FUNC: getDirections
	 * => places a call to the google maps directions API to retreive a JSON object with directions from a 
	 * 		start address to an end address 
	 * @param start LatLng of start
	 * @param stop LatLng of destination
	 * @return PolylineOptions to draw the path from start to stop
	 */
	private PolylineOptions getPolyline(LatLng start, LatLng stop){
		//ArrayList<LatLng> points = new ArrayList<LatLng>();
		/* === not_good === */
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();	//allow all http traffic
			StrictMode.setThreadPolicy(policy);
		}
		/* --- /not_good --- */

		//build the URL for the API call
		String pair1 = "" + start.latitude + "," + start.longitude;
		String pair2 = "" + stop.latitude + "," + stop.longitude;
		String url = "https://maps.googleapis.com/maps/api/directions/json?" +
				"origin=" + pair1 + 
				"&destination=" + pair2 +
				"&sensor=true" +
				"&mode=walking";
		//Log.i("cdc",url);
		//System.out.println(url);
		try {
			JSONObject dir = JSONReader.readJsonFromUrl(url);	//read the JSONObject
			//Log.i("cdc", dir.toString());
			//extract each end location {{ routes[0].legs[0].steps[<0...n>].end_location.<lat|lng> }}
			JSONArray routes = dir.getJSONArray("routes");	//routes
			//Log.i("cdc-routes", routes.toString());
			JSONObject myRoute = routes.getJSONObject(0);	//routes[0]
			//Log.i("cdc-myRoute", myRoute.toString());
			JSONArray legs = myRoute.getJSONArray("legs");	//routes[0].legs
			//Log.i("cdc-legs",legs.toString());
			JSONObject myLeg = legs.getJSONObject(0);		//routes[0].legs[0]
			//Log.i("cdc-myLeg", myLeg.toString());
			JSONArray steps = myLeg.getJSONArray("steps");	//routes[0].legs[0].steps
			//Log.i("cdc-steps", steps.toString());
			PolylineOptions path = new PolylineOptions() //declare the polyline
			.color(getResources().getColor(R.color.blue))
			.width(10);
			for (int i=0; i<steps.length(); i++){
				double lat = steps.getJSONObject(i).getJSONObject("end_location").getDouble("lat");		//get lat
				double lng = steps.getJSONObject(i).getJSONObject("end_location").getDouble("lng");		//get lng
				Log.i("cdc-point","Lat: " + lat + " Lng: " + lng);
				path.add(new LatLng(lat,lng));	//add to polyline
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
	} // end getDirections

}
