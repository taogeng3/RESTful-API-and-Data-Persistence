package edu.sjsu.cmpe275.lab2.model;

import java.util.List;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
* <h1>Player</h1>
* 
* <p>
* The Player model class is mapped to the PLAYER and OPPONENTS 
* tables in database.The sponsor is mapped to sponsor_id column
* in PLAYER table based on "ManyToOne Mapping". The "opponents"
* is mapped to the joined table OPPONENTS based on "ManyToMany 
* Mapping".
*
* @author  Tao Geng
* @version 1.0
* @since   2017-11-07 
*/

@Entity
@Table (name = "PLAYER")
public class Player {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	
	@Column(name = "firstname", nullable = false)
	private String firstname;
	
	@Column(name = "lastname", nullable = false)
	private String lastname;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "description")
    private String description;
	
    @Embedded
    private Address address;
    
    @ManyToOne 
    @JoinColumn (name = "sponsor_id", nullable = true)
    private Sponsor sponsor;
    
    @ManyToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "OPPONENTS",
               joinColumns = {@JoinColumn(name = "player_id", referencedColumnName = "id")}, 
               inverseJoinColumns = {@JoinColumn(name = "opponent_id", referencedColumnName = "id")})
    private List<Player> opponents;
    
    public Player() {}
    
    // Getters and Setters.	
    
	public long getId() {
		return id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Sponsor getSponsor() {
		return sponsor;
	}

	public void setSponsor(Sponsor sponsor) {
		this.sponsor = sponsor;
	}

	public List<Player> getOpponents() {
		return opponents;
	}

	public void setOpponents(List<Player> opponents) {
		this.opponents = opponents;
	}
}
