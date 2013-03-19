/*
 * ===   Fork.java   ===
 * This Activity is launched from the Main App Screen, and presents the user with 3
 * buttons, each of which launches a new activity.
 * Author: Conner Charlebois
 * Date: 3/12/2013
 */
package com.example.majortourguideapp;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Fork extends Activity implements OnClickListener {
	
	//Declare Instance Variables
	private Button btnFind, btnFaculty, btnCore;
	private TextView lblMajor;
	
	private int major;	//stores the major_id from the previous screen
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fork);

		//inflate layout
		btnFind = (Button) findViewById(R.id.btnFind);
		btnFaculty = (Button) findViewById(R.id.btnFaculty);
		btnCore = (Button) findViewById(R.id.btnCore);
		lblMajor = (TextView) findViewById(R.id.lblMajor);
		
		
		//set listeners
		btnFind.setOnClickListener(this);
		btnFaculty.setOnClickListener(this);
		btnCore.setOnClickListener(this);
		
		//get major passed from WelcomeMenu
		major = getIntent().getExtras().getInt("major");
		Log.i("cdc", "major = "+major);
		
		// query db to get the major name
		DB_Helper db = new DB_Helper(this);
		Cursor c = db.selectFromXwhereY(DB_Contract.Major.TABLE_NAME, DB_Contract.Major.COLUMN_MAJOR_ID+" = "+major);
		c.moveToFirst();
		String selected_major = c.getString(c.getColumnIndexOrThrow(DB_Contract.Major.COLUMN_NAME));
		
		// display the text
		lblMajor.setText("You've selected "+selected_major);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_fork, menu);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		return true;
	}

	@Override
	public void onClick(View v) {
		String name = "";
		Intent i = new Intent();
		switch (v.getId()){
		case R.id.btnFind:
			i.setComponent(new ComponentName("com.example.majortourguideapp",
	       			"com.example.majortourguideapp.Find"));
			name="Find";
			break;
		case R.id.btnFaculty:
			//Do some different stuff
			name="Faculty";
			break;
		case R.id.btnCore:
			//Do some crazy stuff
			name="Core";
			break;
		}
       	// ========== vvv ==========
       	// this line sends the major selected to the next activity
       	i.putExtra("major", major);	//change to whatever major they select.
        // ========== ^^^ ==========
       	Toast.makeText(this, "You started this activity: "+name, Toast.LENGTH_LONG).show();
       	startActivity(i);
		
		
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
	            		"com.example.majortourguideapp.WelcomeMenu"));
	            parentActivityIntent.addFlags(
	                    Intent.FLAG_ACTIVITY_CLEAR_TOP |
	                    Intent.FLAG_ACTIVITY_NEW_TASK);
	            startActivity(parentActivityIntent);
	            finish();
	            return true;
	    }
	    return super.onOptionsItemSelected(item);
	}

}
