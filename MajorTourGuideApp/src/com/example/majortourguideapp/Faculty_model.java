package com.example.majortourguideapp;

public class Faculty_model {
	private String name;
	private String email;
	private String phone;
	private String building;
	private String room;
	private String dept;
	private String link;
	private String picture;

	public Faculty_model(String name, String email, String phone, 
			String building, String room, String dept, String link, String picture){
		setName(name);
		setEmail(email);
		setPhone(phone);
		setBuilding(building);
		setRoom(room);
		setDept(dept);
		setLink(link);
		setPicture(picture);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
}
