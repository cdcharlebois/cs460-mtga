package com.example.majortourguideapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_Helper extends SQLiteOpenHelper {
	/* ----------
		Variables for helper
		---------- */
	public static final String DATABASE_NAME = "mtga.db";
	public static final int DATABASE_VERSION = 1;	//if you change the schema, you need to update this.
	
	/* ----------
		Variables for SQL
		---------- */
	public static final String TEXT_TYPE = " TEXT";
	public static final String COMMA_SEP = ", ";
	public static final String UNIQUE = " UNIQUE";
	//create major table
	public static final String SQL_CREATE_MAJORS = 			 
			"CREATE TABLE " + DB_Contract.Major.TABLE_NAME + " (" + 
			DB_Contract.Major._ID + "INTEGER PRIMARY KEY," + 
			DB_Contract.Major.COLUMN_MAJOR_ID + TEXT_TYPE + UNIQUE + COMMA_SEP +
			DB_Contract.Major.COLUMN_NAME + TEXT_TYPE + UNIQUE +
			") ";
	public static final String SQL_DELETE_MAJORS = 
			"DROP TABLE IF EXISTS " + DB_Contract.Major.TABLE_NAME;
	
	/* ----------
		Constructor
	  	---------- */
	public DB_Helper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_MAJORS);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL(SQL_DELETE_MAJORS);
        onCreate(db);
	}
	
	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
	
	/*@Override
	public void onOpen(SQLiteDatabase db){
		
	}*/

}
