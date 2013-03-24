package com.example.majortourguideapp;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Courses extends ListActivity {
	
	private ListView list;
	private  ArrayList<String> courses = new ArrayList<String>();
	private ArrayAdapter<String> arrayAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_courses);
		getActionBar().setDisplayHomeAsUpEnabled(true);		//android design compliant nav
		
		//here's where we'll pull the live values from the array
		courses.add("CS 150 Introduction Data & Information Management");
		courses.add("CS 180 Programming Fundamentals");
		courses.add("CS 240 Business Process & Communication Infrastructure");
		courses.add("CS 350 Database Management Systems");
		courses.add("CS 360 Business Systems Analysis & Design");
		courses.add("CS Elective x 3");
		
		//the array populates the list
		arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, courses);
		setListAdapter(arrayAdapter);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_courses, menu);
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
	            parentActivityIntent.putExtra("major", 1 /*pass back the major */);	//since Fork requires a major, give it back
	            startActivity(parentActivityIntent);
	            finish();
	            return true;
	    }
	    return super.onOptionsItemSelected(item);
	}

}
