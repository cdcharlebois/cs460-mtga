/*
 * this class contains the schema for the DB
 * each table has its own class, implementing BaseColumms
 */
package com.example.majortourguideapp;

import android.provider.BaseColumns;

public abstract class DB_Contract {
	
	public DB_Contract() {}
	
	public static abstract class Professor implements BaseColumns{
		public static final String TABLE_NAME = "professor";
		public static final String COLUMN_NAME = "name";
		public static final String COLUMN_EMAIL = "email";
		public static final String COLUMN_PHONE = "phone";
		public static final String COLUMN_BUILDING = "building";
		public static final String COLUMN_ROOM = "room";
		public static final String COLUMN_DEPARTMENT = "department";
		
	}
	
	public static abstract class Major implements BaseColumns{
		public static final String TABLE_NAME = "major";
		public static final String COLUMN_MAJOR_ID = "major_id";
		public static final String COLUMN_NAME = "name";
	}
	
	public static abstract class Destination implements BaseColumns{
		public static final String TABLE_NAME = "destination";
		public static final String COLUMN_MAJOR = "major_id";
		public static final String COLUMN_LOCATION = "location_id";
		public static final String COLUMN_NAME = "name";
		public static final String COLUMN_ROOM = "room";
	}
	
	public static abstract class Entrance implements BaseColumns{
		public static final String TABLE_NAME = "entrance";
		public static final String COLUMN_LOCATION = "location_id";
		public static final String COLUMN_NAME = "name";
		public static final String COLUMN_LAT = "lattitude";
		public static final String COLUMN_LONG = "longitude";
	}

}
