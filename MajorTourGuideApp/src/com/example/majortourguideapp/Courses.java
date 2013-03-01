package com.example.majortourguideapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Courses extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_courses);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_courses, menu);
		return true;
	}

}
