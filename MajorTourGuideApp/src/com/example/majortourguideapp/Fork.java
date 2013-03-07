/*
 * This Activity is launched from the Main App Screen, and presents the user with 3
 * buttons, each of which launches a new activity.
 */
package com.example.majortourguideapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Fork extends Activity implements OnClickListener {
	
	//Declare Instance Variables
	private Button btnFind, btnFaculty, btnCore;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fork);
		btnFind = (Button) findViewById(R.id.btnFind);
		btnFaculty = (Button) findViewById(R.id.btnFaculty);
		btnCore = (Button) findViewById(R.id.btnCore);
		
		btnFind.setOnClickListener(this);
		btnFaculty.setOnClickListener(this);
		btnCore.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_fork, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		String name = "";
		switch (v.getId()){
		case R.id.btnFind:
			//Do some stuff
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
		Toast.makeText(this, "You started this activity: "+name, Toast.LENGTH_LONG).show();
		
	}

}
