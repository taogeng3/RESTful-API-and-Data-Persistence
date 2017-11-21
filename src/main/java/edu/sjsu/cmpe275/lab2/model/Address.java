package edu.sjsu.cmpe275.lab2.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
* <h1>Address</h1>
* 
* <p>
* The Address model class is mapped to partial columns 
* in the PLAYER and SPONSOR tables in database based on
* "Embedded Mapping".
*
* @author  Tao Geng
* @version 1.0
* @since   2017-11-07 
*/

@Embeddable
public class Address {
	
	@Column(name = "street")
	private String street;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "state")
    private String state;
	
	@Column(name = "zip")
    private String zip;
	
	public Address() {}
    
	// Getters and Setters.
    
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}    
}
