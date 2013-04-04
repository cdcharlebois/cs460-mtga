package com.example.majortourguideapp;

import java.util.ArrayList;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.CameraUpdateFactory;

public class Find extends Activity {
	private Spinner spinner;
	int major;
	private ImageView img;
	private ArrayList<destination_model> destinations = new ArrayList<destination_model>();
	private ArrayAdapter<String> aa;
	private GoogleMap map;
	private LatLng position = new LatLng(42.38781,-71.22008);
	private TextView blurb;

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
		Log.i("cdc", "map set to sattelite");
		map.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 17.0f));
		
		
		//populate the list of destinations from the major
		DB_Helper db = new DB_Helper(this);
		Cursor c = db.selectFromXwhereY(DB_Contract.Destination.TABLE_NAME, DB_Contract.Destination.COLUMN_MAJOR+" = "+major);
		c.moveToFirst();
		do{
			String destName = c.getString(c.getColumnIndexOrThrow(DB_Contract.Destination.COLUMN_NAME));
			String destId = c.getString(c.getColumnIndexOrThrow(DB_Contract.Destination.COLUMN_LOCATION));
			String destBlurb = "";
			try{
				destBlurb = c.getString(c.getColumnIndexOrThrow(DB_Contract.Destination.COLUMN_BLURB));
			}
			catch(Exception e){
				
			}
			destinations.add(new destination_model(destName,destId,destBlurb));
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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_find, menu);
		return true;
	}
	
	
	
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

}
