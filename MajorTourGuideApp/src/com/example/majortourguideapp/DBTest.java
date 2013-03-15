package com.example.majortourguideapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;

public class DBTest extends Activity {
	DB_Helper myDB;
	SQLiteDatabase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dbtest);
	/* ----------
		TEST ENVIRONMENT
		---------- */
		myDB = new DB_Helper(this); //create the helper, DB
		populateTables();
		
		
		db = myDB.getReadableDatabase();		
		//define a projection that specs out which columns from the DB you will 
		//actually use
		String[] projection = {
				DB_Contract.Major.COLUMN_MAJOR_ID,
				DB_Contract.Major.COLUMN_NAME
		};
		
		//spec sort order
		String sort_order = DB_Contract.Major.COLUMN_MAJOR_ID+ " ASC";
		
		//run query
		Cursor c = db.query(
				DB_Contract.Major.TABLE_NAME,		// table to query
				projection,							// cols to return
				null,								// columns for WHERE --> null means all
				null,								// values for WHERE --> null means all
				null,								// GROUP BY --> null means don't group
				null,								// HAVING --> null means don't filter
				sort_order							// sort					
				);
		
		c.moveToFirst();
		do{ //for each row
			Log.i("cdc-db", c.getString(c.getColumnIndexOrThrow(DB_Contract.Major.COLUMN_MAJOR_ID))); //returns MAJOR_ID at the current cursor position
			Log.i("cdc-db", c.getString(c.getColumnIndexOrThrow(DB_Contract.Major.COLUMN_NAME)));
			c.moveToNext();
		}while(!c.isAfterLast()); //stop after last row
		
		
		
		
		
	}

	private void populateTables() {
		// TODO Auto-generated method stub
		db = myDB.getWritableDatabase();
		String[] MAJOR__NAME = {
				"CIS", "ACCOUNTING", "FINANCE", "MARKETING"
				};
		String[] MAJOR__MAJOR_ID = {
				"1", "2", "3", "4"
				};
		ContentValues v = new ContentValues();
		for (int i=0; i<MAJOR__NAME.length; i++){	//loop through values here -- easier than running insert queries
			v.put(DB_Contract.Major.COLUMN_MAJOR_ID, MAJOR__MAJOR_ID[i]); //map table attribute to its value
			v.put(DB_Contract.Major.COLUMN_NAME, MAJOR__NAME[i]);
			db.insert( //creates the new row
					DB_Contract.Major.TABLE_NAME,
					null,
					v);
		}
		
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dbtest, menu);
		return true;
	}

}
