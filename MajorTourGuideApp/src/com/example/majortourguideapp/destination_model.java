package com.example.majortourguideapp;

import android.content.Context;
import android.database.Cursor;

import com.google.android.gms.maps.model.LatLng;

public class Destination_model {

	private String destination_name;
	private String destination_id;
	private String blurb;
	private LatLng entrance;
	
	public Destination_model(String destination_name, String destination_id,
			String blurb, Context ctx) {
		super();
		this.destination_name = destination_name;
		this.destination_id = destination_id;
		this.blurb = blurb;
		this.entrance = findEntrance(ctx);
		
	}
	
	public LatLng getEntrance(){
		return this.entrance;
	}
	
	private LatLng findEntrance(Context ctx){
		DB_Helper db = new DB_Helper(ctx);
		Cursor c = db.selectFromXwhereY(DB_Contract.Entrance.TABLE_NAME, DB_Contract.Entrance.COLUMN_LOCATION+" = "+destination_id);
		c.moveToFirst();
		double entLat = Double.parseDouble(c.getString(c.getColumnIndexOrThrow(DB_Contract.Entrance.COLUMN_LAT)));
		double entLng = Double.parseDouble(c.getString(c.getColumnIndexOrThrow(DB_Contract.Entrance.COLUMN_LONG)));
		db.close();
		return new LatLng(entLat, entLng);
	}
	
	public String getDestination_name() {
		return destination_name;
	}
	public void setDestination_name(String destination_name) {
		this.destination_name = destination_name;
	}
	public String getDestination_id() {
		return destination_id;
	}
	public void setDestination_id(String destination_id) {
		this.destination_id = destination_id;
	}
	public String getBlurb() {
		return blurb;
	}
	public void setBlurb(String blurb) {
		this.blurb = blurb;
	}

	@Override
	public String toString() {
		return destination_name;
	}
	

	
}
