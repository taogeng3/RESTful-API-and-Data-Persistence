package edu.sjsu.cmpe275.lab2.model;

import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
* <h1>Sponsor</h1>
* 
* <p>
* The Sponsor model class is mapped to the SPONSOR table in database.
*
* @author  Tao Geng
* @version 1.0
* @since   2017-11-07 
*/

@Entity
@Table (name = "SPONSOR")
public class Sponsor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	
	@Column(name = "name", nullable = false)
    private String name;
	
	@Column(name = "description")
    private String description;
    
    @Embedded
    private Address address;
    
    public Sponsor() {}
    
    // Getters and Setters.
	
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}

