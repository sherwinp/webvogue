package com.niksoft.dao;

import javax.xml.bind.annotation.*;

@XmlRootElement(name="user")
public class User {
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public User(){
		
	}
	public User(int id, String name){
		
	}

	@XmlElement(name="id") private int id;
	@XmlElement(name="name") private String username;
	@XmlElement(name="city") private String city;
	@XmlElement(name="zip") private String zip;
	@XmlElement(name="email") private String email;
}

