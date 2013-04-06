/**
 * Faculty.java
 * Android activity to show a list of professors
 * Date: 04/05/2013
 * @author CHARLEB_CONN
 */

package com.example.majortourguideapp;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class Faculty extends ListActivity {

	private ArrayList<Faculty_model> faculty = new ArrayList<Faculty_model>();
	private int major;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.activity_faculty);

		//inflate layout

		//set listeners
		// => none for now

		//get extras
		getIntent().getExtras().getInt("major");
		major = getIntent().getExtras().getInt("major");
		Log.i("cdc", "major = "+major);

		//query db
		DB_Helper db = new DB_Helper(this);
		Cursor c = db.selectFromXwhereY(DB_Contract.Professor.TABLE_NAME, DB_Contract.Professor.COLUMN_DEPARTMENT+" = "+major);
		c.moveToFirst();
		do{ //for each row
			String name = c.getString(c.getColumnIndexOrThrow(DB_Contract.Professor.COLUMN_NAME));
			String email = c.getString(c.getColumnIndexOrThrow(DB_Contract.Professor.COLUMN_EMAIL));
			String phone = c.getString(c.getColumnIndexOrThrow(DB_Contract.Professor.COLUMN_PHONE));
			String building = c.getString(c.getColumnIndexOrThrow(DB_Contract.Professor.COLUMN_BUILDING));
			String room = c.getString(c.getColumnIndexOrThrow(DB_Contract.Professor.COLUMN_ROOM));
			String dept_code = c.getString(c.getColumnIndexOrThrow(DB_Contract.Professor.COLUMN_DEPARTMENT));
			String link = c.getString(c.getColumnIndexOrThrow(DB_Contract.Professor.COLUMN_LINK));
			String picture_url = c.getString(c.getColumnIndexOrThrow(DB_Contract.Professor.COLUMN_PICTURE));
			Drawable picture = getImg(picture_url);
			/* -v- replace -v- */
			//query db
			String dept_name="";
			Cursor inner = db.selectFromXwhereY(DB_Contract.Major.TABLE_NAME, DB_Contract.Major.COLUMN_MAJOR_ID+" = "+dept_code);
			inner.moveToFirst();
			do{ //for each row
				dept_name = inner.getString(inner.getColumnIndexOrThrow(DB_Contract.Major.COLUMN_NAME));
				Log.i("cdc-fac",dept_name);
				inner.moveToNext();
			}while(!inner.isAfterLast()); //stop after last row
			/* -^- /replace -^- */
			faculty.add(new Faculty_model(name, email, phone, building, room, dept_code, link, picture, dept_name));
			/* --- TEST --- */
			if(faculty.get(faculty.size()-1).getDept().equals("1"))
				Log.i("cdc-prof", faculty.get(faculty.size()-1).getName());
			/* --- /test --- */
			c.moveToNext();
		}while(!c.isAfterLast()); //stop after last row
		//new query

		db.close();



		// setup the data adapter
		CustomAdapter adapter = new CustomAdapter(this, R.layout.list_item, this.faculty);

		//specify the list adapter
		setListAdapter(adapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_faculty, menu);
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

	private Drawable getImg(String imageUrl){
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		try {
			Drawable d = Drawable.createFromStream(((InputStream)new URL(imageUrl).openStream()),"test");
			return d; 
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

}
