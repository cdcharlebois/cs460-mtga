package com.example.majortourguideapp;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.view.Menu;
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
		
		
		courses.add("CS 150 Introduction Data & Information Management");
		courses.add("CS 180 Programming Fundamentals");
		courses.add("CS 240 Business Process & Communication Infrastructure");
		courses.add("CS 350 Database Management Systems");
		courses.add("CS 360 Business Systems Analysis & Design");
		courses.add("CS Elective x 3");
		
		
		arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, courses);
		setListAdapter(arrayAdapter);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_courses, menu);
		return true;
	}

}
