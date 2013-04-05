/**
 * Faculty.java
 * Android activity to show a list of professors
 * Date: 04/05/2013
 * @author CHARLEB_CONN
 */

package com.example.majortourguideapp;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;

public class Faculty extends ListActivity {

	private ArrayList<Faculty_model> faculty = new ArrayList<Faculty_model>();
	private int major;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
			String dept = c.getString(c.getColumnIndexOrThrow(DB_Contract.Professor.COLUMN_DEPARTMENT));
			String link = c.getString(c.getColumnIndexOrThrow(DB_Contract.Professor.COLUMN_LINK));
			String picture = c.getString(c.getColumnIndexOrThrow(DB_Contract.Professor.COLUMN_PICTURE));
			
			faculty.add(new Faculty_model(name, email, phone, building, room, dept, link, picture));
			/* --- TEST --- */
			if(faculty.get(faculty.size()-1).getDept().equals("1"))
				Log.i("cdc-prof", faculty.get(faculty.size()-1).getName());
			/* --- /test --- */
			c.moveToNext();
		}while(!c.isAfterLast()); //stop after last row
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

}
