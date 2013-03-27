package com.example.majortourguideapp;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class Find extends Activity {
	private Spinner spinner;
	int major;
	private ImageView img;

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
		img = (ImageView)findViewById(R.id.image);
        
		
		//initialize
		major = getIntent().getExtras().getInt("major");
		img.setImageResource(R.drawable.nav);
		
		//populate the list of destinations from the major
		ArrayAdapter<CharSequence> aa = null;
		switch(major){
		case 1:
			aa = ArrayAdapter.createFromResource(this, R.array.CIS_destinations, android.R.layout.simple_spinner_item);
			break;
		}
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
