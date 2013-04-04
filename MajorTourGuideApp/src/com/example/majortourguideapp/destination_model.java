package com.example.majortourguideapp;

public class destination_model {

	private String destination_name;
	private String destination_id;
	private String blurb;
	
	public destination_model(String destination_name, String destination_id,
			String blurb) {
		super();
		this.destination_name = destination_name;
		this.destination_id = destination_id;
		this.blurb = blurb;
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
